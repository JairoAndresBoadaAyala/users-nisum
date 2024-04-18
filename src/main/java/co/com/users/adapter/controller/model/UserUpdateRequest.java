package co.com.users.adapter.controller.model;

import co.com.users.domain.UpdateUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {

    String name;
    @JsonProperty("isactive")
    Boolean isActive;

    public UpdateUser toDomain() {
        return UpdateUser.builder()
                .name(name)
                .isActive(isActive)
                .build();
    }
}
