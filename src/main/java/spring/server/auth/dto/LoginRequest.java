package spring.server.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Login is required")
    String login,

    @Email(message = "email must be in this format: name@gmail.com")
    String email,

    @NotBlank(message = "Password is required")
    String password

) {}
