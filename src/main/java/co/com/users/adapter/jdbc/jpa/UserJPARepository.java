package co.com.users.adapter.jdbc.jpa;

import co.com.users.adapter.jdbc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
}
