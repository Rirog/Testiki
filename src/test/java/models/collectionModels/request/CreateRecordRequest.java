package models.collectionModels.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRecordRequest {
    private RecordsRequest data;

    public CreateRecordRequest(RecordsRequest data) {
        this.data = data;
    }
}
