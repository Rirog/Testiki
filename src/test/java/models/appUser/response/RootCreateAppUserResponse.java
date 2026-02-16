package models.appUser.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootCreateAppUserResponse {
    private UpdateAppUserResponse data;
}
