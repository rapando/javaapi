package ke.innv8.javaapi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
new Student(
                        1L,
                        "John",
                        "john@gmail.com",
                        LocalDate.of(1995, Month.DECEMBER, 5),
                        27
                )
 */
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

     @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);


}
