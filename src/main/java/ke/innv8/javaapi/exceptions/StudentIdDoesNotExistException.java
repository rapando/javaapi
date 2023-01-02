package ke.innv8.javaapi.exceptions;

public class StudentIdDoesNotExistException extends IllegalStateException{
    public StudentIdDoesNotExistException(Long id) {
        super("student with id " + id + " does not exist");
    }
}
