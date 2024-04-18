package co.com.users.adapter.jdbc.jpa;

import co.com.users.adapter.jdbc.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneJPARepository extends JpaRepository<PhoneEntity, String> {

    List<PhoneEntity> findByUserId(String userId);
}
