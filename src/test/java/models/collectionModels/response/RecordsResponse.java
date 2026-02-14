package models.collectionModels.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordsResponse {
    private String id;
    @JsonProperty("collection_id")
    private String collectionId;

    @JsonProperty("project_id")
    private String projectId;

    @JsonProperty("app_user_id")
    private String appUserId;
}
