package models.collectionModels.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCollectionRequest {

    private String name;

    public UpdateCollectionRequest(String visibility) {
        this.name = visibility;
    }
}
