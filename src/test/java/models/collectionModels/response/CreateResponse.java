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
public class CreateResponse {
    private String id;
    @JsonProperty("project_id")
    private String projectId;
    private String slug;
    private String visibility;
    private SchemaResponse schema;
}
