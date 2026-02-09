package com.StudentDetails.StudentDetails.Service;

import com.StudentDetails.StudentDetails.Entity.UserPrincipal;
import com.StudentDetails.StudentDetails.Entity.Users;
import com.StudentDetails.StudentDetails.Repositry.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsername(username);
        if (username == null){
            System.out.println("user not found ");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(users);
    }
}
