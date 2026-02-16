package endpoints;

import models.appUser.request.AddAppUserRequest;
import models.appUser.request.LoginRequest;
import models.appUser.request.UpdateAppUserRequest;
import models.appUser.request.VerifyLoginRequest;
import models.appUser.response.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface AppUserService {
    @POST("api/app-users/login")
    Call<RootLoginResponse> sendLoginCode(@Header("x-api-key") String token,
                                          @Body LoginRequest LoginRequest);

    @POST("api/app-users/verify")
    Call<RootVerifyResponse> verifyLogin(@Body VerifyLoginRequest verifyRequest);

    @GET("api/app-users/me")
    Call<RootCurrentResponse> currentUser(@Header("Authorization") String sessionToken);

    @GET("api/app-users")
    Call<RootListUserResponse> listAppUser(@Header("x-api-key") String adminToken,
                                           @Query("project_id") String projectId,
                                           @Query("statuses") String status);

    @POST("api/app-users")
    Call<RootCreateAppUserResponse> createApUserProject(@Header("x-api-key") String adminToken,
                                                        @Body AddAppUserRequest appUser);

    @GET("api/projects/{project_id}/app-users/total")
    Call<RootCountUserResponse> countAppUser(@Header("x-api-key") String publicToken,
                                             @Path("project_id") String projectId);

    @GET("api/app-users/{app_user_id}")
    Call<RootCurrentResponse> appUserById(@Header("x-api-key") String adminToken,
                                          @Path("app_user_id") String appUserId);

    @PUT("api/app-users/{app_user_id}")
    Call<RootUpdateAppUserResponse> updateAppUser(@Header("x-api-key") String adminToken,
                                                  @Path("app_user_id") String appUserId,
                                                  @Body UpdateAppUserRequest updateAppUserRequest);

    @DELETE("api/app-users/{app_user_id}")
    Call<Void> deleteAppUser(@Header("x-api-key") String adminToken,
                             @Path("app_user_id") String appUserId);
}
