package co.com.users.adapter.controller.model;

import co.com.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    String name;
    String email;
    String password;
    List<PhonesRequest> phones;

    @Builder
    @Value
    public static class PhonesRequest {

        String number;
        @JsonProperty("citycode")
        String cityCode;
        @JsonProperty("countrycode")
        String countryCode;
    }


    public User toDomain() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones
                        .stream()
                        .map(p ->
                                User.Phone.builder()
                                        .number(p.number)
                                        .cityCode(p.cityCode)
                                        .countryCode(p.countryCode)
                                        .build()
                        ).collect(Collectors.toList())

                )
                .build();
    }


}
