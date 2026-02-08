import endpoints.UserService;

import models.userModels.request.LoginUserRequest;
import models.userModels.request.RegisterUserRequest;
import models.userModels.request.UpdateUserRequest;
import models.userModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class UserTest {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final UserService userService = retrofit.create(UserService.class);

    private final String token = "reqres_c20842925bb149f1995ed473f138cd98";

    @Test
    public void getDefaultUserList() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalUser = 12;
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
    public void getUserList() throws IOException {
        int page = 3;
        int perPage = 2;
        int totalPage = 6;
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
    public void getUserById() throws IOException {
        int id = 2;
        Response<RootUserByIdResponse> response = userService.
                getUser(token, id)
                .execute();
        Assertions.assertThat(response.isSuccessful());
        Assertions.assertThat(response.body()).isNotNull();

        int actualId = response.body().getData().getId();
        Assertions.assertThat(actualId).isEqualTo(id);
    }

    @Test
    public void registerUser() throws IOException {
        String email = "eve.holt@reqres.in";
        String password = "pistol";
        String firstName = "Eve";
        String lastName = "Holt";
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(email, firstName, lastName, password);

        Response<RegisterUserResponse> registerResponse = userService
                .registerUser(token, registerUserRequest)
                .execute();
        Assertions.assertThat(registerResponse.isSuccessful());
        Assertions.assertThat(registerResponse.body()).isNotNull();

        int id = registerResponse.body().getId();
        Response<RootUserByIdResponse> response = userService.
                getUser(token, id)
                .execute();
        Assertions.assertThat(registerResponse.isSuccessful());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
        Assertions.assertThat(response.body().getData().getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(response.body().getData().getLastName()).isEqualTo(lastName);

    }

    @Test
    public void loginUser() throws IOException {
        String email = "eve.holt@reqres.in";
        String password = "pistol";

        LoginUserRequest loginUserRequest = new LoginUserRequest(email, password);
        Response<LoginUserResponse> response = userService
                .loginUser(token, loginUserRequest)
                .execute();
        Assertions.assertThat(response.isSuccessful());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getToken()).isNotEmpty();
    }

    @Test
    public void updateUser() throws IOException {
        String name = "morpheus";
        String job = "zion resident";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(name, job);
        Response<UpdateUserResponse> response = userService
                .updateUser(token, updateUserRequest)
                .execute();
        Assertions.assertThat(response.isSuccessful());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getName()).isEqualTo(name);
        Assertions.assertThat(response.body().getJob()).isEqualTo(job);
        Date date = response.body().getUpdatedAt();
    }

    @Test
    public void deleteUser() throws IOException {
        int id = 2;
        Response<Void> response = userService.deleteUser(token, id).execute();
        Assertions.assertThat(response.isSuccessful());
    }
}

