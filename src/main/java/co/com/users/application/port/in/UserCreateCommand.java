package co.com.users.application.port.in;

import co.com.users.domain.User;
import co.com.users.adapter.controller.model.UserResponse;

public interface UserCreateCommand {
    UserResponse execute(User user);
}
