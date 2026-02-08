import endpoints.ResourceService;
import models.resourceModels.ResourceResponse;
import models.resourceModels.RootResourceByIdResponse;
import models.resourceModels.RootResourceResponse;
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

    private final String token = "reqres_c20842925bb149f1995ed473f138cd98";


    @Test
    public void resourceDefaultList() throws IOException {
        int page = 1;
        int perPage = 6;
        int totalResource = 12;
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
    public void resourceList() throws IOException {
        int page = 3;
        int perPage = 3;
        int totalPage = 4;
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
    public void resourceById() throws IOException {
        int id = 2;

        Response<RootResourceByIdResponse> response = resourceService
                .resourceByIdList(token, id)
                .execute();
        Assertions.assertThat(response.isSuccessful());
        Assertions.assertThat(response.body()).isNotNull();

        int actualId = response.body().getData().getId();
        Assertions.assertThat(actualId).isEqualTo(id);
    }
}
