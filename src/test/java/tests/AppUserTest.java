package tests;

import io.qameta.allure.Allure;
import models.appUser.MetaData;
import models.appUser.Profile;
import models.appUser.request.AddAppUserRequest;
import models.appUser.request.LoginRequest;
import models.appUser.request.UpdateAppUserRequest;
import models.appUser.request.VerifyLoginRequest;
import models.appUser.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Response;
import tests.steps.AppUserSteps;

import java.io.IOException;

public class AppUserTest extends BaseTest {
    private final AppUserSteps appUserSteps = new AppUserSteps();
    private String appUserId;
    private String accessToken;


    @BeforeClass
    public void verifyUser() throws IOException {

        LoginRequest loginRequest = new LoginRequest(email, projectId);

        Response<RootLoginResponse> response = appUserSteps.loginStep(loginRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        VerifyLoginRequest verifyLoginRequest = new VerifyLoginRequest(response.body().getData().getToken());

        Response<RootVerifyResponse> verifyResponse = appUserSteps.verifyStep(verifyLoginRequest);
        Assert.assertTrue(verifyResponse.isSuccessful(), "Пришел не тот код " + verifyResponse.code());
        Assertions.assertThat(verifyResponse.body()).isNotNull();

        accessToken = "Bearer " + verifyResponse.body().getData().getSessionToken();

        Allure.step("Get userId");
        Response<RootCurrentResponse> appUserResponse = appUserSteps.userIdStep(accessToken);
        Assert.assertTrue(appUserResponse.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(appUserResponse.body()).isNotNull();

        appUserId = appUserResponse.body().getData().getId();
    }

    @Test
    public void addAppUser() throws IOException {
        String emailTest = "TestEmail.gmail.com";
        AddAppUserRequest appUserRequest = new AddAppUserRequest(emailTest);
        Response<RootCreateAppUserResponse> response = appUserSteps.addAppUser(appUserRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        String id = response.body().getData().getId();

        Response<RootCurrentResponse> responseUser = appUserSteps.userByIdStep(id);
        Assert.assertTrue(responseUser.isSuccessful(), "Пришел не тот код " + responseUser.code());
        Assertions.assertThat(responseUser.body()).isNotNull();
        Assertions.assertThat(responseUser.body().getData().getEmail()).isEqualTo(email);

    }

    @Test
    public void getCurrentAppUserTest() throws IOException {
        String status = "active";

        Allure.step("Получение иннформации профиля");
        Response<RootCurrentResponse> response = appUserSteps.getCurrentUserStep(accessToken);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
        Assertions.assertThat(response.body().getData().getStatus()).isEqualTo(status);
    }

    @Test
    public void getListAppUserTest() throws IOException {

        Response<RootListUserResponse> response = appUserSteps.getListUserStep(projectId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Response<RootCountUserResponse> Countresponse = appUserSteps.getCountUserStep(projectId);
        Assert.assertTrue(Countresponse.isSuccessful(), "Пришел не тот код " + Countresponse.code());
        Assertions.assertThat(Countresponse.body()).isNotNull();

        int count = response.body().getData().size();

        Assertions.assertThat(Countresponse.body().getTotal()).isEqualTo(count);
    }

    @Test
    public void getCountUserTest() throws IOException {

        Response<RootCountUserResponse> response = appUserSteps.getCountUserStep(projectId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Response<RootListUserResponse> appUserListresponse = appUserSteps.getListUserStep(projectId);

        Assert.assertTrue(appUserListresponse.isSuccessful(), "Пришел не тот код " + appUserListresponse.code());
        Assertions.assertThat(appUserListresponse.body()).isNotNull();

        int count = appUserListresponse.body().getData().size();

        Assertions.assertThat(response.body().getTotal()).isEqualTo(count);
    }

    @Test
    public void getAppUserByIdTest() throws IOException {
        Response<RootCurrentResponse> response = appUserSteps.userByIdStep(appUserId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getId()).isEqualTo(appUserId);
        Assertions.assertThat(response.body().getData().getEmail()).isEqualTo(email);
    }

    @Test
    public void updateAppUserTest() throws IOException {
        String emailNew = "testnew@gmail.com";
        String status = "active";
        String role = "beta";
        String locale = "en-GB";
        String avatar = "https://example.com/avatar.png";

        MetaData metaData = new MetaData(role);
        Profile profile = new Profile(locale, avatar);

        UpdateAppUserRequest userRequest = new UpdateAppUserRequest(emailNew, status, metaData, profile);

        Response<RootUpdateAppUserResponse> response = appUserSteps.updateUserStep(appUserId, userRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        UpdateAppUserResponse data = response.body().getData();

        Assertions.assertThat(data.getEmail()).isEqualTo(emailNew);
        Assertions.assertThat(data.getStatus()).isEqualTo(status);

        Assertions.assertThat(data.getId()).isEqualTo(appUserId);
    }

    @AfterClass
    public void deleteAppUser() throws IOException {
        Response<Void> response = appUserSteps.deleteAppUserStep(appUserId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}
