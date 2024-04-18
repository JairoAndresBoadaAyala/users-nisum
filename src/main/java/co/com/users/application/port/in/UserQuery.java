package co.com.users.application.port.in;

import co.com.users.adapter.controller.model.DataUserResponse;

import java.util.UUID;

public interface UserQuery {
    DataUserResponse execute(UUID userId);
}
