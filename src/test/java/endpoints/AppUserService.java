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
    Call<RootLoginResponse> sendLoginCode(@Body LoginRequest LoginRequest);

    @POST("api/app-users/verify")
    Call<RootVerifyResponse> verifyLogin(@Body VerifyLoginRequest verifyRequest);

    @GET("api/app-users/me")
    Call<RootCurrentResponse> currentUser(@Header("Authorization") String sessionToken);

    @GET("api/app-users")
    Call<RootListUserResponse> listAppUser(@Query("project_id") String projectId,
                                           @Query("statuses") String status);

    @POST("api/app-users")
    Call<RootCreateAppUserResponse> createApUserProject(@Body AddAppUserRequest appUser);

    @GET("api/projects/{project_id}/app-users/total")
    Call<RootCountUserResponse> countAppUser(@Path("project_id") String projectId);

    @GET("api/app-users/{app_user_id}")
    Call<RootCurrentResponse> appUserById(@Path("app_user_id") String appUserId);

    @PUT("api/app-users/{app_user_id}")
    Call<RootUpdateAppUserResponse> updateAppUser(@Path("app_user_id") String appUserId,
                                                  @Body UpdateAppUserRequest updateAppUserRequest);


    @GET("api/project/{projectId}/app-users")
    Call<RootListUserResponse> getUserProject(@Path("projectId") int projectId,
                                              @Query("statuses") String status);


    @DELETE("api/app-users/{app_user_id}")
    Call<Void> deleteAppUser(@Path("app_user_id") String appUserId);
}
