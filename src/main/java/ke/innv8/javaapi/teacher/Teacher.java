package ke.innv8.javaapi.teacher;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "teacher_sequence"
    )
    private Long id;
    private String tscNo;
    private String name;
    private String email;
    private LocalDate dob;
    private LocalDate dateOfEmployment;
    @Transient
    private Integer age;
    @Transient
    private Integer yearsEmployed;

    public Teacher() {
    }

    public Teacher(
            Long id,
            String tscNo,
            String name,
            String email,
            LocalDate dob,
            LocalDate dateOfEmployment
    ) {
        this.id = id;
        this.tscNo = tscNo;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.dateOfEmployment = dateOfEmployment;
    }

    public Teacher(
            String tscNo,
            String name,
            String email,
            LocalDate dob,
            LocalDate dateOfEmployment
    ) {
        this.tscNo = tscNo;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.dateOfEmployment = dateOfEmployment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTscNo() {
        return tscNo;
    }

    public void setTscNo(String tscNo) {
        this.tscNo = tscNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getYearsEmployed() {
        return Period.between(this.dateOfEmployment, LocalDate.now()).getYears();
    }

    public void setYearsEmployed(Integer yearsEmployed) {
        this.yearsEmployed = yearsEmployed;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tscNo='" + tscNo + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", dateOfEmployment=" + dateOfEmployment +
                ", age=" + age +
                ", yearsEmployed=" + yearsEmployed +
                '}';
    }
}
