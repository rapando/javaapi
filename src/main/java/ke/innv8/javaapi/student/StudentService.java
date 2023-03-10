package ke.innv8.javaapi.student;

import jakarta.transaction.Transactional;
import ke.innv8.javaapi.exceptions.EmailTakenException;
import ke.innv8.javaapi.exceptions.StudentIdDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            throw new EmailTakenException(student.getEmail());
        }

        // if the email does not exist, create
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new StudentIdDoesNotExistException(id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(
                        () -> new StudentIdDoesNotExistException(id)
                );

        if (
                studentDetails.getName() != null &&
                        studentDetails.getName().length() > 0 &&
                        !Objects.equals(studentDetails.getName(), student.getName())
        ) {
            student.setName(studentDetails.getName());
        }

        if (
                studentDetails.getEmail() != null &&
                        studentDetails.getEmail().length() > 0 &&
                        !Objects.equals(studentDetails.getEmail(), student.getEmail())
        ) {

            // also check if the email has already been taken
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDetails.getEmail());
            if (studentOptional.isPresent()) {
                throw new EmailTakenException(studentDetails.getEmail());
            }
            student.setEmail(studentDetails.getEmail());
        }

        if (
                studentDetails.getDob() != null
        ) {

            student.setDob(studentDetails.getDob());
        }

        studentRepository.save(student);
    }
}
