package com.sms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sms.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    void deleteStudentById(Integer id);
    Student getStudentById(Integer id);
    
    
} 
