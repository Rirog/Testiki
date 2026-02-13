package models.collectionModels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootCreateResponse {
    public String id;
    public String slug;
    public String visibility;
    public SchemaResponse schema;
}
