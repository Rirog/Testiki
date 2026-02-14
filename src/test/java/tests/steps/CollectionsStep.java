package tests.steps;

import endpoints.CollectionsService;
import io.qameta.allure.Step;
import models.collectionModels.response.RootListCollectionsResponse;
import models.collectionModels.response.RootListRecordsResponse;
import retrofit2.Response;

import java.io.IOException;

public  class CollectionsStep extends BaseSteps {

    private final CollectionsService collectionsService = retrofit.create(CollectionsService.class);

    @Step("Получение списка колекций")
    public Response<RootListCollectionsResponse> listCollections() throws IOException {
        return collectionsService.listCollection(tokenAdmin).execute();
    }

    @Step("Получения списка записей коллекции")
    public Response<RootListRecordsResponse> listRecords(String projectSlug, String projectId, int limit) throws IOException {
        return collectionsService.listRecords(tokenAdmin, projectSlug, projectId, limit).execute();
    }
}
