package com.StudentDetails.StudentDetails.Repositry;

import com.StudentDetails.StudentDetails.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
