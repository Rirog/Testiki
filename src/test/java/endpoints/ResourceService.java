package endpoints;

import models.legacy.resourceModels.RootResourceByIdResponse;
import models.legacy.resourceModels.RootResourceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResourceService {

    @GET("api/unknown")
    Call<RootResourceResponse> defaultResourceList(@Header("x-api-key") String token);

    @GET("api/unknown")
    Call<RootResourceResponse> resourceList(@Header("x-api-key") String token,
                                            @Query("page") int page,
                                            @Query("per_page") int perPage);

    @GET("api/unknown/{id}")
    Call<RootResourceByIdResponse> resourceByIdList(@Header("x-api-key") String token,
                                                    @Path("id") int id);
}
