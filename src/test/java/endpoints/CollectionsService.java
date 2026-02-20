
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
    Call<RootCollectionResponse> createCollection(@Body CollectionCreateRequest payload);

    @GET("api/collections")
    Call<RootListCollectionsResponse> listCollection();

    @GET("api/collections/{slug}/")
    Call<RootCollectionResponse> getCollectionById(@Path("slug") String collectionSlug);

    @PUT("api/collections/{slug}")
    Call<RootCollectionResponse> updateCollection(@Path("slug") String collectionSlug,
                                                  @Body UpdateCollectionRequest collectionRequest);

    @GET("api/collections/{slug}/records")
    Call<RootListRecordsResponse> listRecords(@Path("slug") String slug,
                                              @Query("project_id") String id,
                                              @Query("limit") int limit);

    @POST("api/collections/{slug}/records")
    Call<RootGetRecordResponse> createRecord(@Path("slug") String slug,
                                             @Body CreateRecordRequest recordRequest);

    @GET("api/collections/{slug}/records/{recordId}")
    Call<RootGetRecordResponse> getRecordById(@Path("slug") String slug,
                                              @Path("recordId") String id);

    @PUT("api/collections/{slug}/records/{recordId}")
    Call<RootGetRecordResponse> updateRecord(@Path("slug") String slug,
                                             @Path("recordId") String id,
                                             @Body CreateRecordRequest record);

    @DELETE("api/collections/{slug}/records/{recordId}")
    Call<Void> deleteRecord(@Path("slug") String slug,
                            @Path("recordId") String id);

    @DELETE("api/collections/{slug}")
    Call<Void> deleteCollection(@Path("slug") String collectionSlug);
}