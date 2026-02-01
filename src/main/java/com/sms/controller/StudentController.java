package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.sms.entity.Student;
import com.sms.repository.StudentRepository;
import com.sms.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Home page – student list
    @GetMapping("/")
    public String home(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        return "students";
    }

    // Save student
    @PostMapping("/saveStudent")
public String saveStudent(@ModelAttribute Student student, org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
    try {
        studentService.saveStudent(student);
        ra.addFlashAttribute("success", "Student successfully Added!");
    } catch (RuntimeException e) {
        // Agar duplicate email mili, toh error message bhej do
        ra.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/";
}

    // Student delete karne ke liye
@GetMapping("/deleteStudent/{id}")
public String deleteStudent(@PathVariable Integer id) {
    studentService.deleteStudentById(id);
    return "redirect:/";
}

// Update page dikhane ke liye
@GetMapping("/editStudent/{id}")
public String editStudentForm(@PathVariable Integer id, Model model) {
    model.addAttribute("student", studentService.getStudentById(id));
    return "edit_student"; // Ek naya HTML page banana padega
}

// Naya student form dikhane ke liye
@GetMapping("/students/new")
public String createStudentForm(Model model) {
    // Khali student object bhej rahe hain form binding ke liye
    model.addAttribute("student", new Student()); 
    return "create_student"; 
}
}
