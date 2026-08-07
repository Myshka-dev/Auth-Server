package spring.server.auth.service;

import spring.server.auth.Entity.User;
import spring.server.auth.Entity.Role;
import spring.server.auth.Repository.UserRepository;    
import spring.server.auth.exception.UserAlreadyExistsException;
import spring.server.auth.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.server.auth.dto.RegisterRequest;
import spring.server.auth.dto.LoginRequest;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Constructor injection: Spring sees this constructor and automatically supplie both dependency
    // from its application context
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest registerRequest) {
        // Check if the login or email already exists in the database
        if (userRepository.existsByLogin(registerRequest.login())) {
            throw new UserAlreadyExistsException("Login is already taken");
        }
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new UserAlreadyExistsException("Email is already taken");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password());

        // Create a new user entity and set its properties
        User newUser = User.builder()
                .login(registerRequest.login())
                .name(registerRequest.name())
                .email(registerRequest.email())
                .password(hashedPassword)
                .phoneNumber(registerRequest.phoneNumber())
                .role(Role.USER) // every new registration defaults to USER role
                .build();

        // save() triggers the @prePersist method in the User entity, which sets the createdAt timestamp
        return userRepository.save(newUser);
    }

    public User login(LoginRequest loginRequest) {
        // find user by login
        User user = userRepository.findByLogin(loginRequest.login())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid login"));

        boolean passwordMatches = passwordEncoder.matches(loginRequest.password(), user.getPassword());

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid password");
        }

        return user;
    }

}
