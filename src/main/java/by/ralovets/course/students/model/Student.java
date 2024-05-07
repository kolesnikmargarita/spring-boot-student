package by.ralovets.course.students.model;

import by.ralovets.course.students.annotation.ContactNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя студента не должно быть пустым")
    @Size(min = 2, max = 16, message = "Имя дожно быть от 2 до 16 символов")
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @NotBlank(message = "Фамилия студента не должна быть пустой")
    @Size(min = 2, max = 16, message = "Фамилия дожна быть от 2 до 16 символов")
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @ContactNumber(message = "Необходимо указать номер телефона от 9 до 14 цифр")
    @Column(name = "phone_number", nullable = false, length = 32)
    private String phoneNumber;

    @NotNull(message = "Необходимо указать дату рождения")
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public Student() {
    }

    public Student(String name, String lastName, String phoneNumber, LocalDate birthday) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
