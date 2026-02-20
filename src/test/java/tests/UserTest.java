package tests;


import models.legacy.userModels.response.*;
import models.legacy.userModels.request.LoginUserRequest;
import models.legacy.userModels.request.RegisterUserRequest;
import models.legacy.userModels.request.UpdateUserRequest;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retrofit2.Response;

import tests.steps.UserSteps;

import java.io.IOException;
import java.util.ArrayList;


public class UserTest {
    private final UserSteps userSteps = new UserSteps();

    @Test
    public void getDefaultUserListTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalUser = 12;

        Response<RootUserListResponse> response = userSteps.getDefaultUserListStep();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

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

        Response<RootUserListResponse> response = userSteps.getUserListStep(page, perPage);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
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

        Response<RootUserByIdResponse> response = userSteps.getUserByIdStep(id);
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

        Response<RegisterUserResponse> registerResponse = userSteps.registerUserStep(registerUserRequest);
        Assert.assertTrue(registerResponse.isSuccessful(), "Пришел не тот код " + registerResponse.code());
        Assertions.assertThat(registerResponse.body()).isNotNull();

        int id = registerResponse.body().getId();

        Response<RootUserByIdResponse> response = userSteps.getUserByIdStep(id);
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

        Response<LoginUserResponse> response = userSteps.loginUserStep(loginUserRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getToken()).isNotEmpty();
    }

    @Test
    public void updateUserTest() throws IOException {
        String name = "morpheus";
        String job = "zion resident";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(name, job);

        Response<UpdateUserResponse> response = userSteps.updateUserStep(updateUserRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getName()).isEqualTo(name);
        Assertions.assertThat(response.body().getJob()).isEqualTo(job);
    }

    @Test
    public void deleteUserTest() throws IOException {
        int id = 2;
        Response<Void> response = userSteps.deleteUserStep(id);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}

