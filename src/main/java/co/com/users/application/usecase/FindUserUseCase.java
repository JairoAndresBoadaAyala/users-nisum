package co.com.users.application.usecase;

import co.com.users.adapter.controller.model.DataUserResponse;
import co.com.users.adapter.jdbc.model.PhoneEntity;
import co.com.users.adapter.jdbc.model.UserEntity;
import co.com.users.application.port.in.UserQuery;
import co.com.users.application.port.out.PhoneRepository;
import co.com.users.application.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindUserUseCase implements UserQuery {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    public FindUserUseCase(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public DataUserResponse execute(UUID userId) {

        UserEntity userEntity = userRepository.find(userId);
        List<PhoneEntity> phones = phoneRepository.findByUserId(userEntity.getUserId());
        return DataUserResponse.builder()
                .userId(UUID.fromString(userEntity.getUserId()))
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(phones.stream().map(p -> DataUserResponse.DataPhoneResponse.builder()
                        .phoneId(UUID.fromString(p.getPhoneId()))
                        .number(p.getNumber())
                        .cityCode(p.getCityCode())
                        .countryCode(p.getCountryCode())
                        .build()
                ).collect(Collectors.toList()))
                .build();
    }
}
