package tests.steps;

import endpoints.ResourceService;
import io.qameta.allure.Step;
import models.legacy.resourceModels.RootResourceByIdResponse;
import models.legacy.resourceModels.RootResourceResponse;
import retrofit2.Response;

import java.io.IOException;

public class ResourceSteps extends BaseSteps {
    private final ResourceService resourceService = createRetrofit(ResourceService.class);

    @Step("Получение списка пользователей")
    public Response<RootResourceResponse> resourceDefaultListStep() throws IOException {
        return resourceService.defaultResourceList(token).execute();
    }
    @Step("Получение списка ресурсов с параметрами page и per_page")
    public Response<RootResourceResponse> resourceListStep(int page, int perPage) throws IOException {
        return resourceService.resourceList(token, page, perPage).execute();
    }
    @Step("Получение ресурса по айди")
    public Response<RootResourceByIdResponse> resourceByIdTest(int id) throws IOException {
        return resourceService.resourceByIdList(token, id).execute();
    }
}
