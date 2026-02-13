package tests;

import endpoints.ResourceService;
import io.qameta.allure.Allure;
import models.legacy.resourceModels.ResourceResponse;
import models.legacy.resourceModels.RootResourceByIdResponse;
import models.legacy.resourceModels.RootResourceResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ResourceTest {
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final ResourceService resourceService = retrofit.create(ResourceService.class);

    private final String token = System.getProperty("API_KEY");


    @Test
    public void resourceDefaultListTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalResource = 12;

        Allure.step("Проверка списка пользователей");
        Response<RootResourceResponse> response = resourceService
                .defaultResourceList(token)
                .execute();
        Assertions.assertThat(response.isSuccessful());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getPage()).isEqualTo(page);
        Assertions.assertThat(response.body().getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(response.body().getTotal()).isEqualTo(totalResource);

        ArrayList<ResourceResponse> userList = response.body().getData();
        Assertions.assertThat(userList).isNotEmpty();
        Assertions.assertThat(userList.size()).isEqualTo(perPage);
    }

    @Test
    public void resourceListTest() throws IOException {
        int page = 3;
        int perPage = 3;
        int totalPage = 4;

        Allure.step("Проверка списка ресурсов с параметрами page и per_page");
        Response<RootResourceResponse> response = resourceService
                .resourceList(token, page, perPage)
                .execute();
        Assertions.assertThat(response.isSuccessful());

        Assertions.assertThat(response.body()).isNotNull();
        Assertions.assertThat(response.body().getPage()).isEqualTo(page);
        Assertions.assertThat(response.body().getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(response.body().getTotalPages()).isEqualTo(totalPage);

        ArrayList<ResourceResponse> userList = response.body().getData();
        Assertions.assertThat(userList).isNotEmpty();
        Assertions.assertThat(userList.size()).isEqualTo(perPage);
    }

    @Test
    public void resourceByIdTest() throws IOException {
        int id = 2;
        Allure.step("Проверка ресурса по айди");
        Response<RootResourceByIdResponse> response = resourceService
                .resourceByIdList(token, id)
                .execute();
        Assertions.assertThat(response.isSuccessful());
        Assertions.assertThat(response.body()).isNotNull();

        int actualId = response.body().getData().getId();
        Assertions.assertThat(actualId).isEqualTo(id);
    }
}
