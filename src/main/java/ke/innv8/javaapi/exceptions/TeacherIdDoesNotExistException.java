package ke.innv8.javaapi.exceptions;

public class TeacherIdDoesNotExistException extends IllegalStateException{
    public TeacherIdDoesNotExistException(Long id) {
        super("teacher with " + id + " does not exist");
    }
}
