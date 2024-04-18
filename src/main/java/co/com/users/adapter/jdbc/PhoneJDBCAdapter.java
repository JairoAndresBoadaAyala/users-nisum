package co.com.users.adapter.jdbc;

import co.com.users.adapter.jdbc.jpa.PhoneJPARepository;
import co.com.users.adapter.jdbc.model.PhoneEntity;
import co.com.users.application.port.out.PhoneRepository;
import co.com.users.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class PhoneJDBCAdapter implements PhoneRepository {

    private final PhoneJPARepository phoneJPARepository;

    public PhoneJDBCAdapter(PhoneJPARepository phoneJPARepository) {
        this.phoneJPARepository = phoneJPARepository;
    }

    @Override
    public void create(UUID userId, List<User.Phone> phone) {

        List<PhoneEntity> phoneEntity = PhoneEntity.toEntity(userId.toString(), phone);

        phoneJPARepository.saveAll(phoneEntity);
    }

    @Override
    public List<PhoneEntity> findByUserId(String userId) {
        return phoneJPARepository.findByUserId(userId);
    }
}
