package spring.server.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Carries only what is needed from the client during registration
// Notice: no id, no role, no createdAT. the client should never dictate those.
// role gets assigned server-side

public record RegisterRequest(

    //@NotBlank: rejects null, empty string "", and whitespace " "
    // the message is what get returns to the client if this fails
    @NotBlank(message = "Login is required")
    @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
    String login,

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 6 and 20 characters")
    String password,

    @NotBlank(message = "email is required")
    @Email(message = " email must be in this format: name@gmail.com")
    String email,

    String phoneNumber

) {}
