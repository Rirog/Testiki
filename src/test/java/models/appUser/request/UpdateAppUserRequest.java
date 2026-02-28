package models.appUser.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import models.appUser.MetaData;
import models.appUser.Profile;

@Data
@AllArgsConstructor
public class UpdateAppUserRequest {

    private String email;

    private String status;

    @JsonProperty("metadata")
    private MetaData metaData;

    private Profile profile;
}
