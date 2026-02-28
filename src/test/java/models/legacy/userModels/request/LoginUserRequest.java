package models.legacy.userModels.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequest {
    private String email;

    private String password;

}
