package co.com.users.application.port.out;

import co.com.users.adapter.jdbc.model.PhoneEntity;
import co.com.users.domain.User;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository {
    void create(UUID userId, List<User.Phone> phone);
    List<PhoneEntity> findByUserId(String userId);

}
