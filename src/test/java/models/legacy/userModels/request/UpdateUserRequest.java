package models.legacy.userModels.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String name;

    private String job;

}
