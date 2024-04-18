package co.com.users;

import co.com.users.adapter.controller.model.UserRequest;
import co.com.users.adapter.controller.model.UserResponse;
import co.com.users.adapter.controller.model.UserUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class UserControllerAdapterTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createNewUserSuccess() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest request = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();
    }

    @Test
    public void createTwoUsersWithDifferentMailSuccess() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest firstRequest = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        UserRequest secondRequest = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9200@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();
    }

    @Test
    public void createTwoUsersWithMailEqualsError() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest firstRequest = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        UserRequest secondRequest = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();


        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secondRequest)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("this email already exist")));
    }

    @Test
    public void createNewUserInvalidFormatEmailError() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest request = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("format emails is invalid")));
    }

    @Test
    public void createNewUserInvalidFormatPasswordError() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest request = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("invalidFormatWithoutNumbers")
                .phones(phones)
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("format password is invalid")));
    }

    @Test
    public void createNewUserAndFindAgainSuccess() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest request = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        MvcResult userResponseJson = mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(userResponseJson.getResponse().getContentAsString(), UserResponse.class);

        mvc.perform(MockMvcRequestBuilders
                .get("/user/" + userResponse.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id", is(userResponse.getId().toString())));

    }

    @Test
    public void findAnUserInvalidNotFoundError() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/user/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.mensaje", is("user not found")));

    }

    @Test
    public void findAnUserInvalidInternalError() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/user/" + null)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.mensaje", is("Invalid UUID string: null")));

    }

    @Test
    public void createNewUserAfterUpdateUserAndFindNewChangesSuccess() throws Exception {

        List<UserRequest.PhonesRequest> phones = new ArrayList<>();

        phones.add(UserRequest.PhonesRequest.builder()
                .number("315897563214")
                .cityCode("59")
                .countryCode("10")
                .build());

        UserRequest requestCreateUser = UserRequest.builder()
                .name("Jairo Boada")
                .email("jairo9100@gmail.com")
                .password("MyNewPassword91")
                .phones(phones)
                .build();

        UserUpdateRequest requestUpdateUser = UserUpdateRequest.builder()
                .name("Jairo Ayala")
                .isActive(false)
                .build();

        MvcResult userResponseJson = mvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestCreateUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(true)))
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(userResponseJson.getResponse().getContentAsString(), UserResponse.class);

        mvc.perform(
                MockMvcRequestBuilders.put("/user/" + userResponse.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUpdateUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isactive", is(false)))
                .andReturn();

    }
}
