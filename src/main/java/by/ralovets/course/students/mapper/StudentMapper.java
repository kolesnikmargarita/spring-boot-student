package by.ralovets.course.students.mapper;

import by.ralovets.course.students.model.Student;
import by.ralovets.course.students.model.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student) {
        final StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setLastName(student.getLastName());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setBirthday(student.getBirthday());

        return dto;
    }


}
