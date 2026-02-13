package models.appUser.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyLoginRequest {
    private String token;


    public VerifyLoginRequest(String token) {
        this.token = token;
    }
}
