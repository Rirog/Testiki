package models.collectionModels.request;

import lombok.Data;
import models.collectionModels.response.Properties;

@Data
public class SchemaRequest {
    private String type;
    private Properties properties;

    public SchemaRequest(String type, Properties properties) {
        this.type = type;
        this.properties = properties;
    }
}