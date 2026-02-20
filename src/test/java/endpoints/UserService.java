package endpoints;

import models.legacy.userModels.response.*;
import models.legacy.userModels.request.LoginUserRequest;
import models.legacy.userModels.request.RegisterUserRequest;
import models.legacy.userModels.request.UpdateUserRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

    @GET("api/users")
    Call<RootUserListResponse> getDefaultUserList();

    @GET("api/users")
    Call<RootUserListResponse> getUserList(@Query("page") int page,
                                           @Query("per_page") int perPage);

    @GET("api/users/{id}")
    Call<RootUserByIdResponse> getUser(@Path("id") int id);

    @POST("api/register")
    Call<RegisterUserResponse> registerUser(@Body RegisterUserRequest registerUserRequest);

    @POST("api/login")
    Call<LoginUserResponse> loginUser(@Body LoginUserRequest loginUserRequest);

    @PUT("api/users/{id}")
    Call<UpdateUserResponse> updateUser(@Body UpdateUserRequest updateUserRequest);

    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
