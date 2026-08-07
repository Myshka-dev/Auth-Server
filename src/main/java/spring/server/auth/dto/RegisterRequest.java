package spring.server.auth.dto;

import lombok.Getter;
import lombok.Setter;   
import jakarta.validation.constraints.Email;

// Carries only what is needed from the client during registration
// Notice: no id, no role, no createdAT. the client should never dictate those.
// role gets assigned server-side
@Getter
@Setter
public class RegisterRequest {
    private String login;
    private String name;
    private String password;

    @Email
    private String email;
    private String phoneNumber;

}
