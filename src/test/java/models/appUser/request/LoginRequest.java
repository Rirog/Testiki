package models.appUser.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;

    @JsonProperty("project_id")
    private String projectId;

    public LoginRequest(String email, String projectId) {
        this.email = email;
        this.projectId = projectId;
    }
}
