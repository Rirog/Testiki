package models.collectionModels.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalResponse {
    public String type;

    public TotalResponse(String type) {
        this.type = type;
    }
}