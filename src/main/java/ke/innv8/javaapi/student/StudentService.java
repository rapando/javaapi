package ke.innv8.javaapi.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// make it a bean (so it can be autowired) - asing auto instantiated
// we can use @Component/ @Service, we use @Service
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        // if the email does not exist, create
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, Student studentDetails) {
        Optional<Student> studentById = studentRepository.findById(id);
        if (!studentById.isPresent()) {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        Student student = studentById.get();
        student.setName(studentDetails.getName());
        student.setDob(studentDetails.getDob());
        student.setEmail(studentDetails.getEmail());
        studentRepository.save(student);
    }
}
