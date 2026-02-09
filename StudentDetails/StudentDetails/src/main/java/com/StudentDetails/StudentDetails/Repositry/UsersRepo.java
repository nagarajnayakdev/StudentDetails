package com.StudentDetails.StudentDetails.Repositry;

import com.StudentDetails.StudentDetails.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
