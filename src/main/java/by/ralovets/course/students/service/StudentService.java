package by.ralovets.course.students.service;

import by.ralovets.course.students.exception.EntityNotFoundException;
import by.ralovets.course.students.mapper.StudentMapper;
import by.ralovets.course.students.model.Student;
import by.ralovets.course.students.model.StudentDTO;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final StudentMapper studentMapper;

    public StudentService(SessionFactory sessionFactory, StudentMapper studentMapper) {
        this.sessionFactory = sessionFactory;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> findStudents() {
        final List<Student> students = sessionFactory.fromSession(session -> {
            Query<Student> query = session.createQuery("from Student", Student.class);
        return query.list();
        });

        return students.stream().map(studentMapper::toDTO).toList();
    }

    public StudentDTO findStudentById(Long id) {
        Optional<Student> optionalStudent = sessionFactory.fromSession(session -> {
            Query<Student> query = session.createQuery("from Student S where S.id = :id", Student.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        });

        if(optionalStudent.isPresent()) {
            return studentMapper.toDTO(optionalStudent.get());
        }

        throw new EntityNotFoundException("Студента с ID " + id + " не существует!");
    }

    public void save(StudentDTO studentDTO) {
        final Student student = studentMapper.toEntity(studentDTO);

        sessionFactory.inTransaction(session -> {
            session.persist(student);
        });
    }

    public void deleteById(Long id) {
        sessionFactory.inTransaction(session -> {
            MutationQuery query = session.createMutationQuery("delete from Student s where s.id = :id");

            query.setParameter("id", id);

            query.executeUpdate();
        });
    }

    public void update(Long id, StudentDTO student) {
        sessionFactory.inTransaction(session -> {
            MutationQuery query = session.createMutationQuery("""

                    update Student s set s.name = :name,
                    s.lastName = :lastName,
                    s.phoneNumber = :phoneNumber,
                    s.birthday = :birthday
                    where s.id = :id
                    """);

            query.setParameter("id", id);
            query.setParameter("name", student.getName());
            query.setParameter("lastName", student.getLastName());
            query.setParameter("phoneNumber", student.getPhoneNumber());
            query.setParameter("birthday", student.getBirthday());

            query.executeUpdate();
        });
    }
}
