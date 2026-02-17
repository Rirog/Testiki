
package endpoints;

import models.collectionModels.request.CollectionCreateRequest;
import models.collectionModels.request.CreateRecordRequest;
import models.collectionModels.request.UpdateCollectionRequest;
import models.collectionModels.response.RootCollectionResponse;
import models.collectionModels.response.RootGetRecordResponse;
import models.collectionModels.response.RootListCollectionsResponse;
import models.collectionModels.response.RootListRecordsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CollectionsService {

    @POST("api/collections")
    Call<RootCollectionResponse> createCollection(@Header("x-api-key") String projectKey,
                                                  @Body CollectionCreateRequest payload);

    @GET("api/collections")
    Call<RootListCollectionsResponse> listCollection(@Header("x-api-key") String projectKey);

    @GET("api/collections/{slug}/")
    Call<RootCollectionResponse> getCollectionById(@Header("x-api-key") String projectKey,
                                                   @Path("slug") String collectionSlug);

    @PUT("api/collections/{slug}")
    Call<RootCollectionResponse> updateCollection(@Header("x-api-key") String projectKey,
                                                  @Path("slug") String collectionSlug,
                                                  @Body UpdateCollectionRequest collectionRequest);

    @GET("api/collections/{slug}/records")
    Call<RootListRecordsResponse> listRecords(@Header("x-api-key") String projectKey,
                                              @Path("slug") String slug,
                                              @Query("project_id") String id,
                                              @Query("limit") int limit);

    @POST("api/collections/{slug}/records")
    Call<RootGetRecordResponse> createRecord(@Header("x-api-key") String projectKey,
                                             @Path("slug") String slug,
                                             @Body CreateRecordRequest recordRequest);

    @GET("api/collections/{slug}/records/{recordId}")
    Call<RootGetRecordResponse> getRecordById(@Header("x-api-key") String projectKey,
                                              @Path("slug") String slug,
                                              @Path("recordId") String id);

    @PUT("api/collections/{slug}/records/{recordId}")
    Call<RootGetRecordResponse> updateRecord(@Header("x-api-key") String projectKey,
                                             @Path("slug") String slug,
                                             @Path("recordId") String id,
                                             @Body CreateRecordRequest record);

    @DELETE("api/collections/{slug}/records/{recordId}")
    Call<Void> deleteRecord(@Header("x-api-key") String projectKey,
                            @Path("slug") String slug,
                            @Path("recordId") String id);

    @DELETE("api/collections/{slug}")
    Call<Void> deleteCollection(@Header("x-api-key") String projectKey,
                                @Path("slug") String collectionSlug);
}