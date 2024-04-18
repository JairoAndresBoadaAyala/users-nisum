package co.com.users.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
public class User {

    String name;
    String email;
    String password;
    List<Phone> phones;


    @Builder
    @Value
    public static class Phone {

        String number;
        String cityCode;
        String countryCode;
    }
}
