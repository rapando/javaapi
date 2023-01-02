package ke.innv8.javaapi.teacher;

import jakarta.transaction.Transactional;
import ke.innv8.javaapi.exceptions.DateInPastException;
import ke.innv8.javaapi.exceptions.EmailTakenException;
import ke.innv8.javaapi.exceptions.TeacherIdDoesNotExistException;
import ke.innv8.javaapi.exceptions.TooYoungException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void addNewTeacher(Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository
                .findTeacherByEmail(teacher.getEmail());
        if (teacherOptional.isPresent()) {
            throw new EmailTakenException(teacher.getEmail());
        }
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            throw new TeacherIdDoesNotExistException(id);
        }
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(
                        () -> new TeacherIdDoesNotExistException(id)
                );

        if (
                teacherDetails.getTscNo() != null &&
                        teacherDetails.getTscNo().length() > 0 &&
                        !Objects.equals(teacherDetails.getTscNo(), teacher.getTscNo())) {
            teacher.setTscNo(teacherDetails.getTscNo());
        }

        if (
                teacherDetails.getName() != null &&
                        teacherDetails.getName().length() > 0 &&
                        !Objects.equals(teacherDetails.getName(), teacher.getName())) {
            teacher.setName(teacherDetails.getName());
        }

        if (
                teacherDetails.getEmail() != null &&
                        teacherDetails.getEmail().length() > 0 &&
                        !Objects.equals(teacherDetails.getEmail(), teacher.getEmail())) {
            Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacherDetails.getEmail());
            if (teacherOptional.isPresent()) {
                throw new EmailTakenException(teacherDetails.getEmail());
            }
            teacher.setEmail(teacherDetails.getEmail());
        }

        if (teacherDetails.getDob() != null) {
            if (Period.between(teacherDetails.getDob(), LocalDate.now()).getYears() < 18) {
                throw new TooYoungException();
            }
            teacher.setDob(teacherDetails.getDob());
        }

        if (teacherDetails.getDateOfEmployment() != null) {
            if (Period.between(teacherDetails.getDateOfEmployment(), teacher.getDateOfEmployment()).getDays() < 0) {
                throw new DateInPastException();
            }
            teacher.setDateOfEmployment(teacherDetails.getDateOfEmployment());
        }

        teacherRepository.save(teacher);

    }
}
