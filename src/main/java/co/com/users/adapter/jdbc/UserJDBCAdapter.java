package co.com.users.adapter.jdbc;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.adapter.jdbc.jpa.UserJPARepository;
import co.com.users.adapter.jdbc.model.UserEntity;
import co.com.users.application.port.out.UserRepository;
import co.com.users.domain.UpdateUser;
import co.com.users.domain.User;
import co.com.users.exceptions.UserException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;


@Repository
public class UserJDBCAdapter implements UserRepository {

    private final UserJPARepository userJPARepository;

    public UserJDBCAdapter(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public UserResponse create(User userRequest, String token) {
        UserEntity entity = UserEntity.toEntity(userRequest, token);
        userJPARepository.save(entity);
        return entity.toResponse();
    }

    @Override
    public UserResponse update(String userId, UpdateUser updateUser) {
        UserEntity userEntity = userJPARepository.findById(userId).orElseThrow(() -> new UserException("user not found"));

        userEntity.setName(updateUser.getName());
        userEntity.setState(updateUser.getIsActive());
        userEntity.setLastUpdateDate(LocalDateTime.now());

        return userJPARepository.save(userEntity).toResponse();
    }

    @Override
    public UserEntity find(UUID userId) {
        return userJPARepository.findById(userId.toString()).orElseThrow(() -> new UserException("user not found"));
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userJPARepository.findByEmail(email);
    }
}
