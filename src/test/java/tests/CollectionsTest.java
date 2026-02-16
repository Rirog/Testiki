package tests;

import models.collectionModels.request.*;
import models.collectionModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Response;

import java.io.IOException;

public class CollectionsTest extends BaseTest {
    @BeforeSuite
    public void createCollections() throws IOException {
        String name = "newCollection";
        String visibility = "public";
        String slug = "collection";
        String typeScheme = "object";
        String typeTotal = "number";

        TotalResponse totalResponse = new TotalResponse(typeTotal);
        Properties properties = new Properties(totalResponse);
        SchemaRequest schemaRequest = new SchemaRequest(typeScheme, properties);
        CollectionCreateRequest createRequest = new CollectionCreateRequest(name, slug, projectId, visibility, schemaRequest);

        Response<RootCollectionResponse> response = collectionsStep.createCollectionStep(createRequest);
        Assertions.assertThat(response.body()).isNotNull();

        setCollectionSlug(response.body().getData().getSlug());

    }

    @BeforeClass
    public void CreateRecord() throws IOException {
        RecordsRequest recordsRequest = new RecordsRequest(record);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);
        Response<RootGetRecordResponse> response = collectionsStep.createRecordStep(collectionSlug, createRecordRequest);
        Assertions.assertThat(response.body()).isNotNull();
//        recordId  = response.body().getData().getId()
        setRecordId(response.body().getData().getId());
    }

    @Test
    public void getListCollectionsTest() throws IOException {
        int count = 1;
        Response<RootListCollectionsResponse> response = collectionsStep.listCollectionsStep();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().size()).isEqualTo(count);
    }

    @Test
    public void getCollectionBySlug() throws IOException {
        Response<RootCollectionResponse> response = collectionsStep.getCollectionBySlugStep(collectionSlug);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getSlug()).isEqualTo(collectionSlug);
    }

    @Test
    public void updateCollectionTest() throws IOException {
        String newName = "I fucked this api";

        UpdateCollectionRequest collectionRequest = new UpdateCollectionRequest(newName);
        Response<RootCollectionResponse> response = collectionsStep.updateCollectionStep(collectionSlug, collectionRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getSlug()).isEqualTo(collectionSlug);
        Assertions.assertThat(response.body().getData().getName()).isEqualTo(newName);


//        Response<RootCollectionResponse> responseCollection = collectionsStep.getCollectionBySlugStep(collectionSlug);
//        Assert.assertTrue(responseCollection.isSuccessful(), "Пришел не тот код " + responseCollection.code());
//        Assertions.assertThat(responseCollection.body()).isNotNull();
//
//        Assertions.assertThat(responseCollection.body().getData().getSlug()).isEqualTo(collectionSlug);
//        Assertions.assertThat(responseCollection.body().getData().getName()).isEqualTo(newName);
    }

    @Test
    public void getRecordsListTest() throws IOException {
        int limit = 20;
        Response<RootListRecordsResponse> response = collectionsStep.listRecordsStep(collectionSlug, projectId, limit);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getPagination().getLimit()).isEqualTo(limit);
        Assertions.assertThat(response.body().getPagination().getTotal()).isEqualTo(response.body().getData().size());
    }

    @Test
    public void createRecord() throws IOException {
        RecordsRequest recordsRequest = new RecordsRequest(record);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);
        Response<RootGetRecordResponse> response = collectionsStep.createRecordStep(collectionSlug, createRecordRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
        String id = response.body().getData().getId();

        Response<RootGetRecordResponse> responseRecord = collectionsStep.getRecordBySlugStep(collectionSlug, id);
        Assert.assertTrue(responseRecord.isSuccessful(), "Пришел не тот код " + responseRecord.code());
        Assertions.assertThat(responseRecord.body()).isNotNull();

        Assertions.assertThat(responseRecord.body().getData().getData().getRecord()).isEqualTo(record);
        Assertions.assertThat(responseRecord.body().getData().getId()).isEqualTo(id);
    }

    @Test
    public void getRecordBySlug() throws IOException {
        Response<RootGetRecordResponse> response = collectionsStep.getRecordBySlugStep(collectionSlug, recordId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getData().getRecord()).isEqualTo(record);
    }

    @Test
    public void updateRecord() throws IOException {
        String newRecord = "testiki2";
        RecordsRequest recordsRequest = new RecordsRequest(newRecord);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);
        Response<RootGetRecordResponse> response = collectionsStep.updateRecordStep(collectionSlug, recordId, createRecordRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getData().getRecord()).isEqualTo(newRecord);
        Assertions.assertThat(response.body().getData().getId()).isEqualTo(recordId);
//        Response<RootGetRecordResponse> responseRecord = collectionsStep.getRecordBySlugStep(collectionSlug, recordId);
//        Assert.assertTrue(responseRecord.isSuccessful(), "Пришел не тот код " + responseRecord.code());
//        Assertions.assertThat(responseRecord.body()).isNotNull();
//
//        Assertions.assertThat(responseRecord.body().getData().getData().getRecord()).isEqualTo(newRecord);
//        Assertions.assertThat(responseRecord.body().getData().getId()).isEqualTo(recordId);

        setRecord(response.body().getData().getData().getRecord());
    }

    @AfterSuite
    public void deleteCollection() throws IOException {
        Response<Void> response = collectionsStep.deleteCollectionStep(collectionSlug);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }

    @AfterClass
    public void deleteRecord() throws IOException {
        Response<Void> response = collectionsStep.deleteRecordStep(collectionSlug, recordId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }

}
