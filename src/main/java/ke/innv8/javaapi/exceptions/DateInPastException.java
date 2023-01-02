package ke.innv8.javaapi.exceptions;

public class DateInPastException extends IllegalStateException{
    public DateInPastException() {
        super("date provided is in the past");
    }
}
