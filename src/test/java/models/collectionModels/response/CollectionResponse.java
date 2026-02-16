package models.collectionModels.response;

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
public class CollectionResponse {

    private String id;

    @JsonProperty("project_id")
    private int projectId;

    @JsonProperty("user_id")
    private int userId;

    private String name;

    private String slug;

    @JsonProperty("visibility")
    private String visibility;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
