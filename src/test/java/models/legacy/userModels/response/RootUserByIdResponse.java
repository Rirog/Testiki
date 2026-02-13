package models.legacy.userModels.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootUserByIdResponse {
    private UserResponse data;
}
