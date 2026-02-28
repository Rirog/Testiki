package models.collectionModels.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.collectionModels.response.Properties;

@Data
@AllArgsConstructor
public class SchemaRequest {

    private String type;

    private Properties properties;

}