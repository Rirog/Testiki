package models.collectionModels.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordsRequest {
    private String record;

    public RecordsRequest(String record) {
        this.record = record;
    }
}
