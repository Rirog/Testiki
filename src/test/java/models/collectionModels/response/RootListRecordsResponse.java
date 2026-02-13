package models.collectionModels.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootListRecordsResponse {

    @JsonProperty("data")
    private ArrayList<RecordsResponse> records;

    private PaginationResponse pagination;
}
