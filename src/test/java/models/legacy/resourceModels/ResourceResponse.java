package models.legacy.resourceModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse {

    private int id;

    private String name;

    private int year;

    private String color;

    @JsonProperty("pantone_value")
    private String pantoneValue;
}
