package co.com.users.application.usecase;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.application.port.in.UserUpdateCommand;
import co.com.users.application.port.out.UserRepository;
import co.com.users.domain.UpdateUser;
import co.com.users.exceptions.GenericExcepcion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateUserUseCase implements UserUpdateCommand {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse execute(String userId, UpdateUser updateUser) {
        try {
            log.info("start with in flow to update a new user");
            UserResponse response = userRepository.update(userId, updateUser);
            log.info("user was updated success");
            return response;
        } catch (GenericExcepcion ex) {
            log.error("a controlled exception has occurred", ex);
            throw ex;
        }
    }
}
