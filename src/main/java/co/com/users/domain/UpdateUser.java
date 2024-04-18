package co.com.users.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUser {
    String name;
    Boolean isActive;
}
