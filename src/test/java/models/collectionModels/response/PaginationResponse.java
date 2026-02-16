package models.collectionModels.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationResponse {
    private int page;
    private int limit;
    private int total;
    private int pages;
}