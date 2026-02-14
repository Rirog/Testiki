package tests;

import endpoints.CollectionsService;
import models.collectionModels.request.CollectionCreateRequest;
import models.collectionModels.request.SchemaRequest;
import models.collectionModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class CollectionsTest {

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final CollectionsService collectionsService = retrofit.create(CollectionsService.class);

    private final String projectToken = System.getProperty("TOKEN_ADMIN");
    private String collectionId;
    private String slugCollection;


    @BeforeClass
    public void createCollections() throws IOException {
        String name = "newCollection";
        String slug = "duplingha-neon-cloud";
        String id = "3297";
        String visibility = "public";
        String typeScheme = "object";
        String typeTotal = "number";

        TotalResponse totalResponse = new TotalResponse(typeTotal);
        Properties properties = new Properties(totalResponse);
        SchemaRequest schemaRequest = new SchemaRequest(typeScheme, properties);
        CollectionCreateRequest createRequest = new CollectionCreateRequest(name, slug, id, visibility, schemaRequest);

        Response<RootCreateResponse> response = collectionsService
                .createCollection(projectToken, createRequest)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        slugCollection = response.body().getData().getSlug();
        collectionId = response.body().getData().getId();

    }

    @Test
    public void getListCollectionsTest() throws IOException {
        Response<RootListCollectionsResponse> response = collectionsService
                .listCollection(projectToken)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
    }

    @Test
    public void getRecordsListTest() throws IOException {
        String slug = "duplingha-neon-cloud";
        int limit = 20;
        String id = "3297";
        Response<RootListRecordsResponse> response = collectionsService
                .listRecords(projectToken, slug, id, limit)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
    }


    @AfterClass
    public void deleteCollections() throws IOException {
        Response<Void> response = collectionsService
                .deleteCollection(projectToken, slugCollection)
                .execute();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNull();
    }
}
