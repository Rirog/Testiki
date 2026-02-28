package models.appUser.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
public class LoginRequest {
    private String email;

    @JsonProperty("project_id")
    private String projectId;
}
