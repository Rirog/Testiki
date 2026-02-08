package models.resourceModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResourceResponse {
    public int page;

    @JsonProperty("per_page")
    public int perPage;

    public int total;

    @JsonProperty("total_pages")
    public int totalPages;

    public ArrayList<ResourceResponse> data;
}