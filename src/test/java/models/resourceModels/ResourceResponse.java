package models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResourceResponse {

    private int id;

    private String name;

    private int year;

    private String color;

    @JsonProperty("pantone_value")
    private String pantoneValue;
}
