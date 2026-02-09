//package com.StudentDetails.StudentDetails.Controller;
//
//import com.StudentDetails.StudentDetails.Entity.Student;
//import com.StudentDetails.StudentDetails.Service.StudentService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class StudentController {
//    @Autowired
//    private StudentService studentservice;
//
//    @PostMapping("/studentDetails")
//    public Student postDetails(@Valid @RequestBody Student student){
//        return studentservice.saveDetails(student);
//    }
//
//    @GetMapping("/fetchStudents")
//    public List<Student> fetchStudents(HttpServletRequest request){
//        String sessionId = request.getSession().getId();
//        System.out.println("current session id is" +sessionId );
//        return studentservice.getAllStudents();
//    }
//
//    @GetMapping("/csrfToken")
//    public CsrfToken csrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
//
//    @GetMapping("/fetchStudents/{std_id}")
//    public Student fetchStudentById(@PathVariable String std_id){
//        return studentservice.getStudentById(std_id);
//    }
//
//    @PutMapping("/updateStudent")
//    public Student putStudents(@RequestBody Student student){
//        return studentservice.updateStudent(student);
//    }
//
//    @DeleteMapping("/deleteStudent/{std_id}")
//    public String deleteById(@PathVariable String std_id){
//        studentservice.deleteStudentById(std_id);
//        return "record deleted";
//    }
//}
package com.StudentDetails.StudentDetails.Controller;

import com.StudentDetails.StudentDetails.Entity.Student;
import com.StudentDetails.StudentDetails.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")   // All endpoints start with /api/students
@CrossOrigin(origins = "http://localhost:63342") // match your frontend Live Server
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        try {
            Student saved = studentService.saveDetails(student);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\":\"Unable to save student: " + e.getMessage() + "\"}");
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{std_id}")
    public ResponseEntity<?> getStudentById(@PathVariable String std_id) {
        Student student = studentService.getStudentById(std_id);

        if (student == null) {
            return ResponseEntity
                    .status(404)
                    .body("{\"error\":\"Student not found\"}");
        }

        return ResponseEntity.ok(student);
    }


    @PutMapping("/{std_id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable String std_id,
            @Valid @RequestBody Student updatedStudent) {

        try {
            updatedStudent.setStd_id(std_id);
            Student result = studentService.updateStudentById(updatedStudent);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\":\"Unable to update student: " + e.getMessage() + "\"}");
        }
    }


    @DeleteMapping("/{std_id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String std_id) {
        try {
            studentService.deleteStudentBy(std_id);
            return ResponseEntity.ok("{\"message\":\"Student deleted successfully\"}");

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("{\"error\":\"Unable to delete student: " + e.getMessage() + "\"}");
        }
    }
}
