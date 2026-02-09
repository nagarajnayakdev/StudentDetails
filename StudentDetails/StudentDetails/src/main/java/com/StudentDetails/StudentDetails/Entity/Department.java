package com.StudentDetails.StudentDetails.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
//    @StartWithDepAC(message = "Department Id must start with DepAc")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_id;

    @NotBlank(message = "Student must have a department")
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "department")
//    @JsonManagedReference
    @JsonIgnoreProperties("department")
    private List<Student> students = new ArrayList<>();


}
