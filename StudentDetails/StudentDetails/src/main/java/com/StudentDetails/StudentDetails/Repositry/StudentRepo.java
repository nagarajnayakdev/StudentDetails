package com.StudentDetails.StudentDetails.Repositry;

import com.StudentDetails.StudentDetails.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,String> {
}
