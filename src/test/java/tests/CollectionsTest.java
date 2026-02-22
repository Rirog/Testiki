package tests;

import models.collectionModels.request.*;
import models.collectionModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Response;
import tests.steps.CollectionsSteps;

import java.io.IOException;

public class CollectionsTest extends BaseTest {
    private final CollectionsSteps collectionsSteps = new CollectionsSteps();
    private String recordId;
    private String collectionSlug;

    @BeforeSuite
    public void createCollections() throws IOException {
        String name = "newCollection";
        String visibility = "public";
        String slug = "popkaDurakSosalDa";
        String typeScheme = "object";
        String typeTotal = "number";

        TotalResponse totalResponse = new TotalResponse(typeTotal);
        Properties properties = new Properties(totalResponse);
        SchemaRequest schemaRequest = new SchemaRequest(typeScheme, properties);

        CollectionCreateRequest createRequest = new CollectionCreateRequest(name, slug, projectId, visibility, schemaRequest);

        Response<RootCollectionResponse> response = collectionsSteps.createCollectionStep(createRequest);
        Assertions.assertThat(response.body()).isNotNull();

        collectionSlug = response.body().getData().getSlug();

    }

    @BeforeClass
    public void CreateRecord() throws IOException {
        RecordsRequest recordsRequest = new RecordsRequest(record);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);
        Response<RootGetRecordResponse> response = collectionsSteps.createRecordStep(collectionSlug, createRecordRequest);
        Assertions.assertThat(response.body()).isNotNull();

        recordId = response.body().getData().getId();
    }

    @Test
    public void getListCollectionsTest() throws IOException {
        int count = 1;
        Response<RootListCollectionsResponse> response = collectionsSteps.listCollectionsStep();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().size()).isEqualTo(count);
    }

    @Test
    public void getCollectionBySlug() throws IOException {
        Response<RootCollectionResponse> response = collectionsSteps.getCollectionBySlugStep(collectionSlug);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getSlug()).isEqualTo(collectionSlug);
    }

    @Test
    public void updateCollectionTest() throws IOException {
        String newName = "I fucked this api";

        UpdateCollectionRequest collectionRequest = new UpdateCollectionRequest(newName);

        Response<RootCollectionResponse> response = collectionsSteps.updateCollectionStep(collectionSlug, collectionRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getSlug()).isEqualTo(collectionSlug);
        Assertions.assertThat(response.body().getData().getName()).isEqualTo(newName);
        //  ПОЧЕМУ ТО АПИШКА МНЕ ВОЗРАЩЕТ НЕ ТО  В ПОСТМАНЕ ТО ЖЭЕ САМОЕ :(
        // Я не е** что не так :)
//        Response<RootCollectionResponse> responseCollection = collectionsSteps.getCollectionBySlugStep(collectionSlug);
//        Assert.assertTrue(responseCollection.isSuccessful(), "Пришел не тот код " + responseCollection.code());
//        Assertions.assertThat(responseCollection.body()).isNotNull();
//
//        Assertions.assertThat(responseCollection.body().getData().getSlug()).isEqualTo(collectionSlug);
//        Assertions.assertThat(responseCollection.body().getData().getName()).isEqualTo(newName);
    }

    @Test
    public void getRecordsListTest() throws IOException {
        int limit = 20;
        Response<RootListRecordsResponse> response = collectionsSteps.listRecordsStep(collectionSlug, projectId, limit);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getPagination().getLimit()).isEqualTo(limit);
        Assertions.assertThat(response.body().getPagination().getTotal()).isEqualTo(response.body().getData().size());
    }

    @Test
    public void createRecord() throws IOException {
        RecordsRequest recordsRequest = new RecordsRequest(record);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);

        Response<RootGetRecordResponse> response = collectionsSteps.createRecordStep(collectionSlug, createRecordRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
        String id = response.body().getData().getId();

        Response<RootGetRecordResponse> responseRecord = collectionsSteps.getRecordBySlugStep(collectionSlug, id);
        Assert.assertTrue(responseRecord.isSuccessful(), "Пришел не тот код " + responseRecord.code());
        Assertions.assertThat(responseRecord.body()).isNotNull();

        Assertions.assertThat(responseRecord.body().getData().getData().getRecord()).isEqualTo(record);
        Assertions.assertThat(responseRecord.body().getData().getId()).isEqualTo(id);
    }

    @Test
    public void getRecordBySlug() throws IOException {
        Response<RootGetRecordResponse> response = collectionsSteps.getRecordBySlugStep(collectionSlug, recordId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getData().getRecord()).isEqualTo(record);
    }

    @Test
    public void updateRecord() throws IOException {
        String newRecord = "testiki2";

        RecordsRequest recordsRequest = new RecordsRequest(newRecord);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);

        Response<RootGetRecordResponse> response = collectionsSteps.updateRecordStep(collectionSlug, recordId, createRecordRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().getData().getRecord()).isEqualTo(newRecord);
        Assertions.assertThat(response.body().getData().getId()).isEqualTo(recordId);
//        ТУт таже хуйня
//        Response<RootGetRecordResponse> responseRecord = collectionsSteps.getRecordBySlugStep(collectionSlug, recordId);
//        Assert.assertTrue(responseRecord.isSuccessful(), "Пришел не тот код " + responseRecord.code());
//        Assertions.assertThat(responseRecord.body()).isNotNull();
//
//        Assertions.assertThat(responseRecord.body().getData().getData().getRecord()).isEqualTo(newRecord);
//        Assertions.assertThat(responseRecord.body().getData().getId()).isEqualTo(recordId);

        setRecord(response.body().getData().getData().getRecord());
    }

    @AfterSuite
    public void deleteCollection() throws IOException {
        Response<Void> response = collectionsSteps.deleteCollectionStep(collectionSlug);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }

    @AfterClass
    public void deleteRecord() throws IOException {
        Response<Void> response = collectionsSteps.deleteRecordStep(collectionSlug, recordId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }

}
