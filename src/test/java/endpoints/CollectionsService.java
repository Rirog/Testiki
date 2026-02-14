package endpoints;

import models.collectionModels.request.CollectionCreateRequest;
import models.collectionModels.response.RootCreateResponse;
import models.collectionModels.response.RootListCollectionsResponse;
import models.collectionModels.response.RootListRecordsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CollectionsService {

    @POST("api/collections")
    Call<RootCreateResponse> createCollection(@Header("x-api-key") String projectKey,
                                              @Body CollectionCreateRequest payload);


    @GET("api/collections")
    Call<RootListCollectionsResponse> listCollection(@Header("x-api-key") String projectKey);

    @GET("api/collections/{slug}/records")
    Call<RootListRecordsResponse> listRecords(@Header("x-api-key") String projectKey,
                                              @Path("slug") String slug,
                                              @Query("project_id") String id,
                                              @Query("limit") int limit);


    @DELETE("api/collections/{slug}")
    Call<Void> deleteCollection(@Header("x-api-key") String projectKey,
                                @Path("slug") String collectionSlug);
}
