package tests;

import models.collectionModels.request.*;
import models.collectionModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Response;
import tests.steps.CollectionsStep;

import java.io.IOException;

public class CollectionsTest extends BaseTest {

    @BeforeClass
    public void CreateRecord() throws IOException {
        RecordsRequest recordsRequest = new RecordsRequest(record);
        CreateRecordRequest createRecordRequest = new CreateRecordRequest(recordsRequest);
        Response<RootGetRecordResponse> response = collectionsStep.createRecordStep(collectionSlug, createRecordRequest);
        Assertions.assertThat(response.body()).isNotNull();

        recordId = response.body().getData().getId();
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

        Assertions.assertThat(response.body().getData().getId()).isEqualTo(projectId);
    }

    @Test
    public void updateCollectionTest() throws IOException {
        String name = "newCollection2";
        UpdateCollectionRequest collectionRequest = new UpdateCollectionRequest(name);
        Response<RootCollectionResponse> response = collectionsStep.updateCollectionStep(collectionSlug, collectionRequest);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Response<RootCollectionResponse> responseCollection = collectionsStep.getCollectionBySlugStep(collectionSlug);
        Assert.assertTrue(responseCollection.isSuccessful(), "Пришел не тот код " + responseCollection.code());
        Assertions.assertThat(responseCollection.body()).isNotNull();

        Assertions.assertThat(responseCollection.body().getData().getName()).isEqualTo(name);
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

        Response<RootGetRecordResponse> responseRecord = collectionsStep.getRecordBySlugStep(collectionSlug, recordId);
        Assert.assertTrue(responseRecord.isSuccessful(), "Пришел не тот код " + responseRecord.code());
        Assertions.assertThat(responseRecord.body()).isNotNull();

        Assertions.assertThat(responseRecord.body().getData().getData().getRecord()).isEqualTo(newRecord);
        Assertions.assertThat(responseRecord.body().getData().getId()).isEqualTo(recordId);

        record = response.body().getData().getData().getRecord();
    }



    @AfterClass
    public void deleteRecord() throws IOException {
        Response<Void> response = collectionsStep.deleteRecordStep(collectionSlug, recordId);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}
