package models.collectionModels.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCreateRequest {

    private String name;

    private String slug;

    @JsonProperty("project_id")
    private String projectId;

    private String visibility;

    private SchemaRequest schemaRequest;
}
