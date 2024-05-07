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

    public Student toEntity(StudentDTO dto) {
        final Student entity = new Student();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setBirthday(dto.getBirthday());

        return entity;
    }
}
