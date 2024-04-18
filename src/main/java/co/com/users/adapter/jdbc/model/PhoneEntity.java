package co.com.users.adapter.jdbc.model;

import co.com.users.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phones")
public class PhoneEntity {

    @Id
    @Column(name = "phone_id")
    private String phoneId;
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "user_id")
    private String userId;

    public static List<PhoneEntity> toEntity(String userId, List<Phone> phone) {

        return phone.stream().map(p ->
                PhoneEntity.builder()
                        .phoneId(UUID.randomUUID().toString())
                        .number(p.getNumber())
                        .cityCode(p.getCityCode())
                        .countryCode(p.getCountryCode())
                        .userId(userId)
                        .build()
        ).collect(Collectors.toList());

    }

}
