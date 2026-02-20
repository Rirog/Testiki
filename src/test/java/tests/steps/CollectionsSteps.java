package tests.steps;

import endpoints.CollectionsService;
import io.qameta.allure.Step;
import models.collectionModels.request.CollectionCreateRequest;
import models.collectionModels.request.CreateRecordRequest;
import models.collectionModels.request.UpdateCollectionRequest;
import models.collectionModels.response.RootCollectionResponse;
import models.collectionModels.response.RootGetRecordResponse;
import models.collectionModels.response.RootListCollectionsResponse;
import models.collectionModels.response.RootListRecordsResponse;
import retrofit2.Response;

import java.io.IOException;

public class CollectionsSteps extends BaseSteps {

    private final CollectionsService collectionsService = createRetrofit(CollectionsService.class);

    @Step("Создание коллекции")
    public Response<RootCollectionResponse> createCollectionStep(CollectionCreateRequest collectionCreateRequest) throws IOException {
        return collectionsService.createCollection(tokenAdmin, collectionCreateRequest).execute();
    }

    @Step("Получение списка колекций")
    public Response<RootListCollectionsResponse> listCollectionsStep() throws IOException {
        return collectionsService.listCollection(tokenAdmin).execute();
    }

    @Step("Обнавление коллекции")
    public Response<RootCollectionResponse> updateCollectionStep(String collectionSlug, UpdateCollectionRequest updateCollectionRequest) throws IOException {
        return collectionsService.updateCollection(tokenAdmin, collectionSlug, updateCollectionRequest).execute();
    }

    @Step("Получение коллекции по slug")
    public Response<RootCollectionResponse> getCollectionBySlugStep(String collectionSlug) throws IOException {
        return collectionsService.getCollectionById(tokenAdmin, collectionSlug).execute();
    }

    @Step("Получения списка записей коллекции")
    public Response<RootListRecordsResponse> listRecordsStep(String collectionSlug, String projectId, int limit) throws IOException {
        return collectionsService.listRecords(tokenAdmin, collectionSlug, projectId, limit).execute();
    }

    @Step("Создание записи")
    public Response<RootGetRecordResponse> createRecordStep(String collectionSlug, CreateRecordRequest createRecordRequest) throws IOException {
        return collectionsService.createRecord(tokenAdmin, collectionSlug, createRecordRequest).execute();
    }

    @Step("Получения записи по slug")
    public Response<RootGetRecordResponse> getRecordBySlugStep(String collectionSlug, String recordId) throws IOException {
        return collectionsService.getRecordById(tokenAdmin, collectionSlug, recordId).execute();
    }

    @Step("Изменение записи")
    public Response<RootGetRecordResponse> updateRecordStep(String collectionSlug, String recordId, CreateRecordRequest record) throws IOException {
        return collectionsService.updateRecord(tokenAdmin, collectionSlug, recordId, record).execute();
    }

    @Step("Удаление записи")
    public Response<Void> deleteRecordStep(String collectionSlug, String recordId) throws IOException {
        return collectionsService.deleteRecord(tokenAdmin, collectionSlug, recordId).execute();
    }

    @Step("Удаление коллекции")
    public Response<Void> deleteCollectionStep(String slug) throws IOException {
        return collectionsService.deleteCollection(tokenAdmin, slug).execute();
    }
}
