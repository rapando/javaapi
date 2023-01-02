package ke.innv8.javaapi.exceptions;

public class EmailTakenException extends IllegalStateException {
    public EmailTakenException(String email) {
        super("Email " + email + " already taken");
    }
}
