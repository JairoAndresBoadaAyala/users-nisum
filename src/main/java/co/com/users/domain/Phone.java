package co.com.users.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Phone {

    String number;
    String cityCode;
    String countryCode;
}