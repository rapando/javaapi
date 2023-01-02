package ke.innv8.javaapi.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @RequestMapping(value = "", method=RequestMethod.POST)
    public void registerNewTeacher(@RequestBody Teacher teacher) {
        teacherService.addNewTeacher(teacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacherDetails) {
        teacherService.updateTeacher(id, teacherDetails);
    }
}
