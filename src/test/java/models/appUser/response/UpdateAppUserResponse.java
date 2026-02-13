package models.appUser.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.appUser.MetaData;
import models.appUser.Profile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateAppUserResponse {
    private String id;
    private String email;
    private String status;
    private MetaData metaData;
    private Profile profile;
}
