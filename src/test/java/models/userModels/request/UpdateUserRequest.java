package models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    private String name;

    private String job;

    public UpdateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
