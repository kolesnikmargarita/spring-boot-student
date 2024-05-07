package by.ralovets.course.students.controller;

import by.ralovets.course.students.mapper.StudentMapper;
import by.ralovets.course.students.model.Student;
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
    public String createStudent(@ModelAttribute Student student) {
        return "students/new";
    }

    @PostMapping("/new")
    public String handleStudentCreation(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "students/new";
        }
        final StudentMapper mapper = new StudentMapper();
        studentService.save(mapper.toDTO(student));
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        final StudentDTO student = studentService.findStudentById(id);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final String formattedDate = student.getBirthday().format(formatter);

        model.addAttribute("student", student);
        model.addAttribute("formattedDate", formattedDate);

        return "students/update";
    }

    @PutMapping("/{id}")
    public String handleStudentUpdate(
            @PathVariable Long id,
            @Valid @ModelAttribute StudentDTO student,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "students/update";

        studentService.update(id, student);
        return "redirect:/students";
    }
}
