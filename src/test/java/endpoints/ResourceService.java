package endpoints;

import models.legacy.resourceModels.RootResourceByIdResponse;
import models.legacy.resourceModels.RootResourceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResourceService {

    @GET("api/unknown")
    Call<RootResourceResponse> defaultResourceList();

    @GET("api/unknown")
    Call<RootResourceResponse> resourceList(@Query("page") int page,
                                            @Query("per_page") int perPage);

    @GET("api/unknown/{id}")
    Call<RootResourceByIdResponse> resourceByIdList(@Path("id") int id);
}
