package co.com.users.adapter.controller;


import co.com.users.adapter.controller.model.DataUserResponse;
import co.com.users.adapter.controller.model.UserRequest;
import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.adapter.controller.model.UserUpdateRequest;
import co.com.users.application.port.in.UserCreateCommand;
import co.com.users.application.port.in.UserQuery;
import co.com.users.application.port.in.UserUpdateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserControllerAdapter {

    private final UserCreateCommand userCreateCommand;
    private final UserUpdateCommand userUpdateCommand;
    private final UserQuery userQuery;

    public UserControllerAdapter(UserCreateCommand userCreateCommand, UserUpdateCommand userUpdateCommand, UserQuery userQuery) {
        this.userCreateCommand = userCreateCommand;
        this.userUpdateCommand = userUpdateCommand;
        this.userQuery = userQuery;
    }

    @PostMapping()
    public final UserResponse create(@RequestBody UserRequest userRequest) {
        log.info("start to create a new user");
        return userCreateCommand.execute(userRequest.toDomain());
    }

    @PutMapping("/{user_id}")
    public final UserResponse update(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable("user_id") String userId) {
        log.info("start to update a new user");
        return userUpdateCommand.execute(userId, userUpdateRequest.toDomain());
    }

    @GetMapping("/{user_id}")
    public final DataUserResponse find(@PathVariable("user_id") String userId) {
        log.info("start to find an user with user_id {}", userId);
        return userQuery.execute(UUID.fromString(userId));
    }
}

