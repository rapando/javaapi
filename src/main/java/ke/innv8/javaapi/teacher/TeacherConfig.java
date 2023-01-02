package ke.innv8.javaapi.teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class TeacherConfig {

    @Bean
    CommandLineRunner teacherCommandLineRunner(TeacherRepository repository) {
        return args -> {
            Teacher kemp = new Teacher(
                    "KJHDs",
                    "Kemp Doe",
                    "kemp.doe@example.com",
                    LocalDate.of(1980, Month.APRIL, 1),
                    LocalDate.of(2010, Month.APRIL, 23)
            );

            repository.save(kemp);
        };
    }
}
