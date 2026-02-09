package com.StudentDetails.StudentDetails.Repositry;//package com.StudentDetails.StudentDetails.Repositry;
//
//import com.StudentDetails.StudentDetails.Entity.Department;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface DepartmentRepo extends JpaRepository<Department,Integer> {
//}


import com.StudentDetails.StudentDetails.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    Optional<Department> findByDeptName(String deptName);
}
