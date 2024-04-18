package co.com.users.adapter.jdbc.model;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    String userId;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @Column(name = "last_update_date")
    LocalDateTime lastUpdateDate;
    @Column(name = "last_login")
    LocalDateTime lastLogin;
    @Column(name = "state")
    Boolean state;
    @Column(name = "token")
    String token;

    public static UserEntity toEntity(User user, String token) {

        LocalDateTime date = LocalDateTime.now();

        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdDate(date)
                .lastUpdateDate(date)
                .lastLogin(date)
                .state(true)
                .token(token)
                .build();
    }

    public UserResponse toResponse() {
        return UserResponse.builder()
                .id(UUID.fromString(userId))
                .created(createdDate)
                .modified(lastUpdateDate)
                .lastLogin(lastLogin)
                .isActive(state)
                .token(token)
                .build();
    }
}
