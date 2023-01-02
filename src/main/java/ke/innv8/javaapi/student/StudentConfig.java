package ke.innv8.javaapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student john = new Student(
                    "John",
                    "john@gmail.com",
                    LocalDate.of(1995, Month.DECEMBER, 5)
            );

            Student james = new Student(
                    "James",
                    "james@gmail.com",
                    LocalDate.of(2000, Month.APRIL, 10)
            );

            repository.saveAll(List.of(john, james));
        };
    }
}
