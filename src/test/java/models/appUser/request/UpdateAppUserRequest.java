package models.appUser.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import models.appUser.MetaData;
import models.appUser.Profile;

@Getter
@Setter
public class UpdateAppUserRequest {
    private String email;
    private String status;
    @JsonProperty("metadata")
    private MetaData metaData;
    private Profile profile;

    public UpdateAppUserRequest(String email, String status, MetaData metaData, Profile profile) {
        this.email = email;
        this.status = status;
        this.metaData = metaData;
        this.profile = profile;
    }
}
