package tests;

import endpoints.UserService;

import io.qameta.allure.Allure;
import models.legacy.userModels.response.*;
import models.legacy.userModels.request.LoginUserRequest;
import models.legacy.userModels.request.RegisterUserRequest;
import models.legacy.userModels.request.UpdateUserRequest;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;


public class UserTest {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final UserService userService = retrofit.create(UserService.class);

    private final String token = System.getProperty("API_KEY");

    @Test
    public void getDefaultUserListTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalUser = 12;

        Allure.step("Проверка списка пользователей");
        Response<RootUserListResponse> response = userService
                .getDefaultUserList(token)
                .execute();
        Assertions.assertThat(response.isSuccessful());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getPage()).isEqualTo(page);
        Assertions.assertThat(response.body().getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(response.body().getTotal()).isEqualTo(totalUser);

        ArrayList<UserResponse> userList = response.body().getData();
        Assertions.assertThat(userList).isNotEmpty();
        Assertions.assertThat(userList.size()).isEqualTo(perPage);
    }

    @Test
    public void getUserListTest() throws IOException {
        int page = 3;
        int perPage = 2;
        int totalPage = 6;

        Allure.step("Проверка списка пользователей с параметрами page и per_page");
        Response<RootUserListResponse> response = userService
                .getUserList(token, page, perPage)
                .execute();
        Assertions.assertThat(response.isSuccessful());
        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getPage()).isEqualTo(page);
        Assertions.assertThat(response.body().getTotalPages()).isEqualTo(totalPage);

        ArrayList<UserResponse> userList = response.body().getData();
        Assertions.assertThat(userList).isNotEmpty();
        Assertions.assertThat(userList.size()).isEqualTo(perPage);
    }


    @Test
    public void getUserByIdTest() throws IOException {
        int id = 2;

        Allure.step("Проверка пользователя по айди");
        Response<RootUserByIdResponse> response = userService.
                getUser(token, id)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        int actualId = response.body().getData().getId();
        Assertions.assertThat(actualId).isEqualTo(id);
    }


    @Test
    public void registerUserTest() throws IOException {
        String email = "eve.holt@reqres.in";
        String password = "pistol";
        String firstName = "Eve";
        String lastName = "Holt";
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(email, firstName, lastName, password);

        Allure.step("Регистрация пользователя");
        Response<RegisterUserResponse> registerResponse = userService
                .registerUser(token, registerUserRequest)
                .execute();
        Assert.assertTrue(registerResponse.isSuccessful(), "Пришел не тот код " + registerResponse.code());
        Assertions.assertThat(registerResponse.body()).isNotNull();

        int id = registerResponse.body().getId();

        Allure.step("Получение пользователя по вернувшему айди");
        Response<RootUserByIdResponse> response = userService.
                getUser(token, id)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
        Assertions.assertThat(response.body().getData().getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(response.body().getData().getLastName()).isEqualTo(lastName);

    }

    @Test
    public void loginUserTest() throws IOException {
        String email = "eve.holt@reqres.in";
        String password = "pistol";

        LoginUserRequest loginUserRequest = new LoginUserRequest(email, password);
        Allure.step("Авторизация пользователя");
        Response<LoginUserResponse> response = userService
                .loginUser(token, loginUserRequest)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getToken()).isNotEmpty();
    }

    @Test
    public void updateUserTest() throws IOException {
        String name = "morpheus";
        String job = "zion resident";

        Allure.step("Обновление инфромации о пользователе");
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(name, job);
        Response<UpdateUserResponse> response = userService
                .updateUser(token, updateUserRequest)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getName()).isEqualTo(name);
        Assertions.assertThat(response.body().getJob()).isEqualTo(job);
    }

    @Test
    public void deleteUserTest() throws IOException {
        int id = 2;
        Allure.step("Удаление пользователя");
        Response<Void> response = userService.deleteUser(token, id).execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}

