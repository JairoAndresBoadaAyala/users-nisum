package co.com.users.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Builder
@Value
public class DataUserResponse {


    @JsonProperty("user_id")
    UUID userId;
    String name;
    String email;
    String password;
    List<DataPhoneResponse> phones;

    @Builder
    @Value
    public static class DataPhoneResponse {

        @JsonProperty("phone_id")
        UUID phoneId;
        String number;
        @JsonProperty("citycode")
        String cityCode;
        @JsonProperty("countrycode")
        String countryCode;
    }

}
