package models.collectionModels.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class CollectionCreateRequest {

    private String name;

    private String slug;

    @JsonProperty("project_id")
    private String projectId;

    private String visibility;

    private SchemaRequest schemaRequest;


    public CollectionCreateRequest(String name, String slug, String projectId, String visibility, SchemaRequest schemaRequest) {
        this.name = name;
        this.slug = slug;
        this.projectId = projectId;
        this.visibility = visibility;
        this.schemaRequest = schemaRequest;
    }
}