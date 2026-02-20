package tests;

import models.legacy.resourceModels.ResourceResponse;
import models.legacy.resourceModels.RootResourceByIdResponse;
import models.legacy.resourceModels.RootResourceResponse;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.ResourceSteps;

import java.io.IOException;
import java.util.ArrayList;

public class ResourceTest {
    private final ResourceSteps resourceSteps = new ResourceSteps();


    @Test
    public void resourceDefaultListTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalResource = 12;

        Response<RootResourceResponse> response = resourceSteps.resourceDefaultListStep();
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

        Response<RootResourceResponse> response = resourceSteps.resourceListStep(page, perPage);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
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

        Response<RootResourceByIdResponse> response = resourceSteps.resourceByIdTest(id);
        Assert.assertTrue(response.isSuccessful(), "Пришел не тот код " + response.code());
        Assertions.assertThat(response.body()).isNotNull();

        int actualId = response.body().getData().getId();
        Assertions.assertThat(actualId).isEqualTo(id);
    }
}
