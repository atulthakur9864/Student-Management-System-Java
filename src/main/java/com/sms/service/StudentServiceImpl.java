package com.sms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sms.entity.Student;
import com.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
     
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> getAllStudents() {
       // return repo.findAll();  <--
         return repo.findAllSortedByName();
    }

    
    @Override
    public Student saveStudent(Student student) {
        // Naya student (ID null) check aur duplicate email check
        if (student.getId() == null && repo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email is already registerd!");
        }
        return repo.save(student);
    }

    @Override
    public void deleteStudentById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Student getStudentById(Integer id) {
        // findById return karta hai Optional, isliye .orElse(null) use karna safe hai
        return repo.findById(id).orElse(null);
    }
}