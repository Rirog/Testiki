package models.appUser.request;


import lombok.*;

@Data
@AllArgsConstructor
public class VerifyLoginRequest {
    private String token;

}
