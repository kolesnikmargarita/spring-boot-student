package by.ralovets.course.students.controller;

import by.ralovets.course.students.exception.EntityNotFoundException;
import by.ralovets.course.students.model.StudentDTO;
import by.ralovets.course.students.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String findStudents(Model model) {
        final List<StudentDTO> students = studentService.findStudents();

        model.addAttribute("students", students);

        return "students/list";
    }

    @GetMapping("/{id}")
    public String findStudent(Model model, @PathVariable("id") Long id) {
        final StudentDTO student = studentService.findStudentById(id);

        model.addAttribute("student", student);

        return "students/page";
    }

    @GetMapping("/new")
    public String createStudent(@ModelAttribute StudentDTO student) {
        return "students/new";
    }



    /*@GetMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        final StudentDTO student = studentService.findStudentById(id);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final String formattedDate = student.getBirthday().format(formatter);

        model.addAttribute("student", student);
        model.addAttribute("formattedDate", formattedDate);

        return "students/update";
    }*/


}
