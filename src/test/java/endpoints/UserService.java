package endpoints;

import models.legacy.userModels.response.*;
import models.legacy.userModels.request.LoginUserRequest;
import models.legacy.userModels.request.RegisterUserRequest;
import models.legacy.userModels.request.UpdateUserRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

    @GET("api/users")
    Call<RootUserListResponse> getDefaultUserList(@Header("x-api-key") String token);

    @GET("api/users")
    Call<RootUserListResponse> getUserList(@Header("x-api-key") String token,
                                           @Query("page") int page,
                                           @Query("per_page") int perPage);

    @GET("api/users/{id}")
    Call<RootUserByIdResponse> getUser(@Header("x-api-key") String token,
                                       @Path("id") int id);

    @POST("api/register")
    Call<RegisterUserResponse> registerUser(@Header("x-api-key") String token,
                                            @Body RegisterUserRequest registerUserRequest);

    @POST("api/login")
    Call<LoginUserResponse> loginUser(@Header("x-api-key") String token,
                                      @Body LoginUserRequest loginUserRequest);

    @PUT("api/users/{id}")
    Call<UpdateUserResponse> updateUser(@Header("x-api-key") String token,
                                        @Body UpdateUserRequest updateUserRequest);

    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Header("x-api-key") String token,
                          @Path("id") int id);
}
