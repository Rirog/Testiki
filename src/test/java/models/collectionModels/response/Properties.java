package models.collectionModels.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Properties {
    private TotalResponse totalResponse;

    public Properties(TotalResponse totalResponse) {
        this.totalResponse = totalResponse;
    }
}