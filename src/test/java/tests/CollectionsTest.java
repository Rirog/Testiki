package tests;

import models.collectionModels.response.*;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.CollectionsStep;

import java.io.IOException;

public class CollectionsTest extends BaseTest {
    private final CollectionsStep collectionsStep = new CollectionsStep();

    @Test
    public void getListCollectionsTest() throws IOException {
        int count = 1;
        Response<RootListCollectionsResponse> response = collectionsStep.listCollections();
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        Assertions.assertThat(response.body().getData().size()).isEqualTo(count);
    }

    @Test
    public void getRecordsListTest() throws IOException {
        int limit = 20;
        Response<RootListRecordsResponse> response = collectionsStep.listRecords(collectionSlug, projectId, limit);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();
    }
}
