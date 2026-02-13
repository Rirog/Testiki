package tests;

import endpoints.AppUserService;
import io.qameta.allure.Allure;
import models.appUser.MetaData;
import models.appUser.Profile;
import models.appUser.request.LoginRequest;
import models.appUser.request.UpdateAppUserRequest;
import models.appUser.request.VerifyLoginRequest;
import models.appUser.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class AppUserTest {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final AppUserService appUserService = retrofit.create(AppUserService.class);

    private final String tokenProjectAdmin = System.getProperty("TOKEN_ADMIN");
    private final String tokenProjectPublic = System.getProperty("TOKEN_PUBLIC");
    private final String projectId = "3297";

    private String appUserId;
    private String accessToken;

    @BeforeClass
    public void verifyUser() throws IOException {
        String email = "Duplingha@gmail.com";
        String type = "application/json";

        LoginRequest loginRequest = new LoginRequest(email, projectId);

        Allure.step("Login user");
        Response<RootLoginResponse> response = appUserService
                .sendLoginCode(type, tokenProjectPublic, loginRequest)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();


        VerifyLoginRequest verifyLoginRequest = new VerifyLoginRequest(response.body().getData().getToken());

        Allure.step("Verify user");
        Response<RootVerifyResponse> verifyResponse = appUserService
                .verifyLogin(type, verifyLoginRequest)
                .execute();
        Assert.assertTrue(verifyResponse.isSuccessful(), "Пришел не тот код " + verifyResponse.code());
        Assertions.assertThat(verifyResponse.body()).isNotNull();

        accessToken = "Bearer " + verifyResponse.body().getData().getSessionToken();

        Allure.step("Get userId");
        Response<RootCurrentResponse> appUserResponse = appUserService
                .currentUser(accessToken)
                .execute();
        Assert.assertTrue(appUserResponse.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(appUserResponse.body()).isNotNull();

        appUserId = appUserResponse.body().getData().getId();
    }

    @Test
    public void getCurrentAppUserTest() throws IOException {
        String email = "duplingha@gmail.com";
        String status = "active";

        Allure.step("Получение иннформации профиля");
        Response<RootCurrentResponse> response = appUserService
                .currentUser(accessToken)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
        Assertions.assertThat(response.body().getData().getStatus()).isEqualTo(status);
    }

    @Test
    public void getListAppUserTest() throws IOException {
        Allure.step("Получение списка пользователей");
        Response<RootListUserResponse> response = appUserService
                .listAppUser(tokenProjectAdmin, projectId)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Allure.step("Получение количество пользователей");
        Response<RootCountUserResponse> Countresponse = appUserService
                .countAppUser(tokenProjectPublic, projectId)
                .execute();
        Assert.assertTrue(Countresponse.isSuccessful(), "Пришел не тот код " + Countresponse.code());
        Assertions.assertThat(Countresponse.body()).isNotNull();

        int count = response.body().getData().size();

        Assertions.assertThat(Countresponse.body().getTotal()).isEqualTo(count);
    }

    @Test
    public void getCountUserTest() throws IOException {

        Allure.step("Получение количество пользователей");
        Response<RootCountUserResponse> response = appUserService
                .countAppUser(tokenProjectPublic, projectId)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Allure.step("Получение списка пользователей");
        Response<RootListUserResponse> appUserListresponse = appUserService
                .listAppUser(tokenProjectAdmin, projectId)
                .execute();
        Assert.assertTrue(appUserListresponse.isSuccessful(), "Пришел не тот код " + appUserListresponse.code());
        Assertions.assertThat(appUserListresponse.body()).isNotNull();

        int count = appUserListresponse.body().getData().size();

        Assertions.assertThat(response.body().getTotal()).isEqualTo(count);
    }

    @Test
    public void getAppUserByIdTest() throws IOException {
        String email = "duplingha@gmail.com";
        Allure.step("Получение пользователя по айди");
        Response<RootCurrentResponse> response = appUserService
                .appUserById(tokenProjectAdmin, appUserId)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getId()).isEqualTo(appUserId);
        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
    }
// я этот тест в рот ебал чтор суука не так то далбооеб

    @Test
    public void updateAppUserTest() throws IOException {
        String type = "application/json";
        String email = "testsuka@gmail.com";
        String status = "active";
        String role = "beta";
        String locale = "en-GB";
        String avatar = "https://example.com/avatar.png";

        MetaData metaData = new MetaData(role);
        Profile profile = new Profile(locale, avatar);

        UpdateAppUserRequest userRequest = new UpdateAppUserRequest(email, status, metaData, profile);
        Allure.step("Изменение информации пользователя");
        Response<RootUpdateAppUserResponse> response = appUserService
                .updateAppUser(tokenProjectAdmin, type, appUserId, userRequest)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        UpdateAppUserResponse data = response.body().getData();

        Assertions.assertThat(data.getEmail()).isEqualTo(email);
        Assertions.assertThat(data.getStatus()).isEqualTo(status);

        Assertions.assertThat(data.getId()).isEqualTo(appUserId);
    }

    @AfterClass
    public void deleteAppUser() throws IOException {
        Response<Void> response = appUserService
                .deleteAppUser(tokenProjectAdmin, appUserId)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}
