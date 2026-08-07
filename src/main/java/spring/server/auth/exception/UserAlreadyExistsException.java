package spring.server.auth.exception;

// Thrown during registration when the login or email is already taken. rather than catching unrelated error like 500 
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
