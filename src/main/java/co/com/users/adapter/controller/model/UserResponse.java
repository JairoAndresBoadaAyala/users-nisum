package co.com.users.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    UUID id;
    LocalDateTime created;
    LocalDateTime modified;
    @JsonProperty("last_login")
    LocalDateTime lastLogin;
    String token;
    @JsonProperty("isactive")
    Boolean isActive;

}
