package tests.steps;

import endpoints.UserService;
import io.qameta.allure.Step;
import models.legacy.userModels.request.LoginUserRequest;
import models.legacy.userModels.request.RegisterUserRequest;
import models.legacy.userModels.request.UpdateUserRequest;
import models.legacy.userModels.response.*;
import retrofit2.Response;

import java.io.IOException;

public class UserSteps extends BaseSteps {
    private final UserService userService = createRetrofit(UserService.class, token);

    @Step("Получение списка пользователей")
    public Response<RootUserListResponse> getDefaultUserListStep() throws IOException {
        return userService.getDefaultUserList().execute();
    }

    @Step("Получение списка пользователей с параметрами page и per_page")
    public Response<RootUserListResponse> getUserListStep(int page, int perPage) throws IOException {
        return userService.getUserList(page, perPage).execute();
    }

    @Step("Получение пользователя по айди")
    public Response<RootUserByIdResponse> getUserByIdStep(int id) throws IOException {
        return userService.getUser(id).execute();
    }

    @Step("Регистрация пользователя")
    public Response<RegisterUserResponse> registerUserStep(RegisterUserRequest registerUserRequest) throws IOException {
        return userService.registerUser(registerUserRequest).execute();
    }

    @Step("Авторизация пользователя")
    public Response<LoginUserResponse> loginUserStep(LoginUserRequest loginUserRequest) throws IOException {
        return userService.loginUser(loginUserRequest).execute();
    }

    @Step("Обновление инфромации о пользователе")
    public Response<UpdateUserResponse> updateUserStep(UpdateUserRequest updateUserRequest) throws IOException {
        return userService.updateUser(updateUserRequest).execute();
    }

    @Step("Удаление пользователя")
    public Response<Void> deleteUserStep(int id) throws IOException {
        return userService.deleteUser(id).execute();
    }
}
