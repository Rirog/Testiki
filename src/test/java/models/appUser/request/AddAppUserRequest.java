package models.appUser.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAppUserRequest {

    private String email;

    public AddAppUserRequest(String email) {
        this.email = email;
    }
}
