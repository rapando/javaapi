package ke.innv8.javaapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired // dependency injection (instead of using new StudentService())
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateStudent(@PathVariable("id") Long id, @RequestBody Student studentDetails) {
        studentService.updateStudent(id, studentDetails);
    }
}
