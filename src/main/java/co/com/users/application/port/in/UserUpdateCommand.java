package co.com.users.application.port.in;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.domain.UpdateUser;

public interface UserUpdateCommand {
    UserResponse execute(String userId, UpdateUser updateUser);
}
