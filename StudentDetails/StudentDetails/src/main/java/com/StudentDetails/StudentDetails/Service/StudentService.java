//package com.StudentDetails.StudentDetails.Service;
//
//import com.StudentDetails.StudentDetails.Entity.Student;
//import com.StudentDetails.StudentDetails.Execption.DuplicateStudentException;
//import com.StudentDetails.StudentDetails.Execption.StudentNotFoundException;
//import com.StudentDetails.StudentDetails.Repositry.StudentRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StudentService {
//    @Autowired
//    private StudentRepo studentrepo;
//
//    public Student saveDetails(Student student){
//        if (student.getStd_id() !=null && studentrepo.existsById(student.getStd_id())){
//            throw new DuplicateStudentException("Student Id already exists");
//        }
//        return studentrepo.save(student);
//    }
//
//    public List<Student> getAllStudents(){
//
//        return studentrepo.findAll();
//    }
//
//    public Student getStudentById(String std_id){
//        return studentrepo.findById(std_id).orElseThrow(() -> new StudentNotFoundException("Student Not Found"));
//    }
//
//    public Student updateStudentById(Student student){
//        Student existingStudent=studentrepo.findById(student.getStd_id()).orElseThrow(()-> new StudentNotFoundException("Student Not Found"));
//        if (existingStudent != null){
//            existingStudent.setStd_name(student.getStd_name());
//            existingStudent.setStd_marks(student.getStd_marks());
//            existingStudent.setStd_email(student.getStd_email());
//            existingStudent.setAddress(student.getAddress());
//            existingStudent.setDepartment(student.getDepartment());
//            existingStudent.setAdmissionDate(student.getAdmissionDate());
//            studentrepo.save(existingStudent);
//        }
//        return existingStudent;
//    }
//
//    public void deleteStudentBy(String std_id){
//        if (!studentrepo.existsById(std_id)){
//             throw new StudentNotFoundException("cannot Delete");
//        }
//        studentrepo.deleteById(std_id);
//    }
//}
//

package com.StudentDetails.StudentDetails.Service;



import com.StudentDetails.StudentDetails.Entity.Department;
import com.StudentDetails.StudentDetails.Entity.Student;
import com.StudentDetails.StudentDetails.Execption.DuplicateStudentException;
import com.StudentDetails.StudentDetails.Execption.StudentNotFoundException;
import com.StudentDetails.StudentDetails.Repositry.DepartmentRepo;
import com.StudentDetails.StudentDetails.Repositry.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepo studentrepo;


    @Autowired
    private DepartmentRepo departmentRepo;

    public Student saveDetails(Student student) {

        if (studentrepo.existsById(student.getStd_id())) {
            throw new DuplicateStudentException("Student id already exists");
        }

        // Prevent duplicate department
        Department dept = departmentRepo.findByDeptName(student.getDepartment().getDeptName())
                .orElse(student.getDepartment());
        student.setDepartment(dept);

        return studentrepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentrepo.findAll();
    }

    public Student getStudentById(String std_id) {
        return studentrepo.findById(std_id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    public Student updateStudentById(Student student) {

        Student existingStudent = studentrepo.findById(student.getStd_id())
                .orElseThrow(() -> new StudentNotFoundException("Student ID not found"));

        // Update simple fields
        existingStudent.setStd_name(student.getStd_name());
        existingStudent.setStd_marks(student.getStd_marks());
        existingStudent.setStd_email(student.getStd_email());
        existingStudent.setAdmissionDate(student.getAdmissionDate());

        // Update address (update fields only)
        if (existingStudent.getAddress() != null) {
            existingStudent.getAddress().setCity(student.getAddress().getCity());
            existingStudent.getAddress().setState(student.getAddress().getState());
        } else {
            existingStudent.setAddress(student.getAddress());
        }

        // Update department safely
        Department dept = departmentRepo.findByDeptName(student.getDepartment().getDeptName())
                .orElse(student.getDepartment());
        existingStudent.setDepartment(dept);

        return studentrepo.save(existingStudent);
    }

    public void deleteStudentBy(String std_id) {
        Student st = studentrepo.findById(std_id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        studentrepo.delete(st);
    }
}
