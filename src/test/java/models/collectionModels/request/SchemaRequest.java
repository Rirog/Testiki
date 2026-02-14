package models.collectionModels.request;

import models.collectionModels.response.Properties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchemaRequest {
    private String type;
    private Properties properties;

    public SchemaRequest(String type, Properties properties) {
        this.type = type;
        this.properties = properties;
    }
}
