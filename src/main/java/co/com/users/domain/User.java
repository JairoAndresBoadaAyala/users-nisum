package co.com.users.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {

    String name;
    String email;
    String password;
    List<Phone> phones;

}
