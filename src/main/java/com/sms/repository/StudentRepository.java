package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sms.entity.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    // Isse Spring Boot apne aap "SELECT * FROM students WHERE email = ?" query bana lega
    Student findByEmail(String email);

  
    // Yeh check karega ki kya email pehle se hai
    boolean existsByEmail(String email);

    // ye students ko alphabetical order me laayega
    @Query("SELECT s FROM Student s ORDER BY s.name ASC")
    List<Student> findAllSortedByName();


}
