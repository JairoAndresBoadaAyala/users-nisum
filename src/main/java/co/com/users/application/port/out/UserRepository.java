package co.com.users.application.port.out;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.adapter.jdbc.model.UserEntity;
import co.com.users.domain.User;

import java.util.UUID;

public interface UserRepository {
    UserResponse create(User userRequest, String token);
    UserEntity find(UUID userId);
    UserEntity findByEmail(String email);
}
