package co.com.users.application.usecase;

import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.adapter.jdbc.model.UserEntity;
import co.com.users.application.port.in.UserCreateCommand;
import co.com.users.application.port.out.PhoneRepository;
import co.com.users.application.port.out.UserRepository;
import co.com.users.config.utility.JwtUtil;
import co.com.users.config.utility.RegularExpressionUtil;
import co.com.users.domain.User;
import co.com.users.exceptions.UserException;
import co.com.users.exceptions.GenericExcepcion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class CreateUserUseCase implements UserCreateCommand {

    private static final String REGEX_MAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Value("${user.password.regexp}")
    private String REGEX_PASSWORD;


    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final JwtUtil jwtUtil;

    public CreateUserUseCase(UserRepository userRepository, PhoneRepository phoneRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public UserResponse execute(User userRequest) {

        try {
            log.info("start with in flow to create a new user");
            validateFormat(userRequest.getEmail(), REGEX_MAIL, "format emails is invalid");
            validateFormat(userRequest.getPassword(), REGEX_PASSWORD, "format password is invalid");
            validateIfMailExist(userRequest.getEmail());
            String token = jwtUtil.generateToken(userRequest.getName());
            UserResponse response = userRepository.create(userRequest, token);
            log.info("user was created success");
            phoneRepository.create(response.getId(), userRequest.getPhones());
            log.info("phone was created succes");
            return response;
        } catch (GenericExcepcion ex) {
            log.error("a controlled exception has occurred", ex);
            throw ex;
        }
    }


    private void validateFormat(String phrase, String regex, String message) {
        if (!RegularExpressionUtil.isValid(phrase, regex)) {
            throw new UserException(message);
        }
    }

    private void validateIfMailExist(String email) {
        UserEntity entity = userRepository.findByEmail(email);

        if (Objects.nonNull(entity)) {
            throw new UserException("this email already exist");
        }
    }
}
