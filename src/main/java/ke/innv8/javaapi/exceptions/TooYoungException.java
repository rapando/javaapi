package ke.innv8.javaapi.exceptions;

public class TooYoungException extends IllegalStateException {
    public TooYoungException() {
        super("too young");
    }
}
