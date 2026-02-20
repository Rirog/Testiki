package tests.steps;

import endpoints.AppUserService;

import io.qameta.allure.Step;
import models.appUser.request.AddAppUserRequest;
import models.appUser.request.LoginRequest;
import models.appUser.request.UpdateAppUserRequest;
import models.appUser.request.VerifyLoginRequest;
import models.appUser.response.*;
import retrofit2.Response;

import java.io.IOException;

public class AppUserSteps extends BaseSteps {
    private final AppUserService appUserAdminService = createRetrofit(AppUserService.class, tokenAdmin);
    private final AppUserService appUserService = createRetrofit(AppUserService.class, tokenPublic);

    @Step("Login user")
    public Response<RootLoginResponse> loginStep(LoginRequest loginRequest) throws IOException {
        return appUserService.sendLoginCode(loginRequest).execute();
    }

    @Step("Verify user")
    public Response<RootVerifyResponse> verifyStep(VerifyLoginRequest verifyLoginRequest) throws IOException {
        return appUserService.verifyLogin(verifyLoginRequest).execute();
    }

    @Step("Get userId")
    public Response<RootCurrentResponse> userIdStep(String accessToken) throws IOException {
        return appUserService.currentUser(accessToken).execute();
    }
    @Step("Создание нового пользователя")
    public  Response<RootCreateAppUserResponse> addAppUser(AddAppUserRequest addAppUserRequest) throws IOException {
        return appUserAdminService.createApUserProject(addAppUserRequest).execute();
    }

    @Step("Получение иннформации профиля")
    public Response<RootCurrentResponse> getCurrentUserStep(String accessToken) throws IOException {
        return appUserService.currentUser(accessToken).execute();
    }

    @Step("Получение списка пользователей")
    public Response<RootListUserResponse> getListUserStep(String projectId, String status) throws IOException {
        return appUserAdminService.listAppUser(projectId, status).execute();
    }

    @Step("Получение количество пользователей")
    public Response<RootCountUserResponse> getCountUserStep(String projectId) throws IOException {
        return appUserService.countAppUser(projectId).execute();
    }

    @Step("Получение пользователя по айди")
    public Response<RootCurrentResponse> userByIdStep(String appUserId) throws IOException {
        return appUserAdminService.appUserById(appUserId).execute();
    }


    @Step("Изменение информации пользователя")
    public Response<RootUpdateAppUserResponse> updateUserStep(String appUserId, UpdateAppUserRequest userRequest) throws IOException {
        return appUserAdminService.updateAppUser(appUserId, userRequest).execute();
    }

    @Step("Удаление пользователя")
    public Response<Void> deleteAppUserStep(String appUserId) throws IOException {
        return appUserAdminService.deleteAppUser(appUserId).execute();
    }
}
