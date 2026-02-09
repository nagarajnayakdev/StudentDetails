package com.StudentDetails.StudentDetails.Entity;

import com.StudentDetails.StudentDetails.Validation.StartWith25AC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Table(name="StudentTable")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @StartWith25AC(message = "Invalid std_id")
    @NotNull(message = "student id must not be Null")
    private String std_id;

    @NotBlank(message = "Student name must not be blank")
    private String std_name;

    @Min(value = 0,message = "Student marks must not be negative")
    @Max(value = 100,message = "Student marks should not exceed 100")
    private int std_marks;

    @Pattern(regexp = "^[A-Za-z0-9_.*+-]+@gmail.com$",message = "email should be in this format")
    private String std_email;

    @PastOrPresent(message = "Admission Date must be in present or past ")
    private LocalDate admissionDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    @JsonManagedReference
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    @Valid
    @JsonIgnoreProperties("students")
    private Department department;

}
