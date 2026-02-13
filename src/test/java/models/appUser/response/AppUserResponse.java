package models.appUser.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserResponse {
    public String id;
    public String email;
    public String status;

    @JsonProperty("last_login_at")
    public Date lastLoginAt;

    @JsonProperty("created_at")
    public Date createdAt;
}
