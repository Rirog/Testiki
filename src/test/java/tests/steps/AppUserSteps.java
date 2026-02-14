package tests.steps;

import endpoints.AppUserService;

import io.qameta.allure.Step;
import models.appUser.request.LoginRequest;
import models.appUser.request.UpdateAppUserRequest;
import models.appUser.request.VerifyLoginRequest;
import models.appUser.response.*;
import retrofit2.Response;

import java.io.IOException;

public class AppUserSteps extends BaseSteps {
    private final AppUserService appUserService = retrofit.create(AppUserService.class);

    @Step("Login user")
    public Response<RootLoginResponse> loginStep(LoginRequest loginRequest) throws IOException {
        return appUserService.sendLoginCode(tokenPublic, loginRequest).execute();
    }

    @Step("Verify user")
    public Response<RootVerifyResponse> verifyStep(VerifyLoginRequest verifyLoginRequest) throws IOException {
        return appUserService.verifyLogin(verifyLoginRequest).execute();
    }

    @Step("Get userId")
    public Response<RootCurrentResponse> userIdStep(String accessToken) throws IOException {
        return appUserService.currentUser(accessToken).execute();
    }

    @Step("Получение иннформации профиля")
    public Response<RootCurrentResponse> getCurrentUserStep(String accessToken) throws IOException {
        return appUserService.currentUser(accessToken).execute();
    }

    @Step("Получение списка пользователей")
    public Response<RootListUserResponse> getListUserStep(String projectId) throws IOException {
        return appUserService.listAppUser(tokenAdmin, projectId).execute();
    }

    @Step("Получение количество пользователей")
    public Response<RootCountUserResponse> getCountUserStep(String projectId) throws IOException {
        return appUserService.countAppUser(tokenPublic, projectId).execute();
    }

    @Step("Получение пользователя по айди")
    public Response<RootCurrentResponse> userByIdStep(String appUserId) throws IOException {
        return appUserService.appUserById(tokenAdmin, appUserId).execute();
    }


    @Step("Изменение информации пользователя")
    public Response<RootUpdateAppUserResponse> updateUserStep(String appUserId, UpdateAppUserRequest userRequest) throws IOException {
        return appUserService.updateAppUser(tokenAdmin, appUserId, userRequest).execute();
    }

    @Step("Удаление пользователя")
    public Response<Void> deleteAppUserStep(String appUserId) throws IOException {
        return appUserService.deleteAppUser(tokenAdmin, appUserId).execute();
    }
}
