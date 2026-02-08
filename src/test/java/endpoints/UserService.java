import models.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("api/users")
    Call<UserResponse> getUSerList();
}
