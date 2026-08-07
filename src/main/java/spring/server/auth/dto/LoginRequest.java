package spring.server.auth.dto;

import lombok.*;
import jakarta.validation.constraints.Email;


@Getter
@Setter
public class LoginRequest {
    private String login;

    @Email
    private String email;
    private String password;

}
