package tests;

import models.collectionModels.request.CollectionCreateRequest;
import models.collectionModels.request.SchemaRequest;
import models.collectionModels.response.Properties;
import models.collectionModels.response.RootCollectionResponse;
import models.collectionModels.response.TotalResponse;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import retrofit2.Response;
import tests.steps.AppUserSteps;
import tests.steps.CollectionsStep;

import java.io.IOException;

public class BaseTest {

    protected String projectId = "3297";
    protected String projectSlug = "duplingha-neon-cloud";
    protected String collectionSlug;
    protected String email = "duplingha@gmail.com";
    protected String recordId;
    protected String record = "testiki";
    protected CollectionsStep collectionsStep = new CollectionsStep();
    protected AppUserSteps appUserSteps = new AppUserSteps();

    @BeforeSuite
    public void createCollections() throws IOException {
        String name = "newCollection";
        String visibility = "public";
        String typeScheme = "object";
        String typeTotal = "number";

        TotalResponse totalResponse = new TotalResponse(typeTotal);
        Properties properties = new Properties(totalResponse);
        SchemaRequest schemaRequest = new SchemaRequest(typeScheme, properties);
        CollectionCreateRequest createRequest = new CollectionCreateRequest(name, projectSlug, projectId, visibility, schemaRequest);

        Response<RootCollectionResponse> response = collectionsStep.createCollectionStep(createRequest);
        Assertions.assertThat(response.body()).isNotNull();

        collectionSlug = response.body().getData().getSlug();

    }
    @AfterSuite
    public void deleteCollection() throws IOException {
        Response<Void> response = collectionsStep.deleteCollectionStep(collectionSlug);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
    }
}
