package com.StudentDetails.StudentDetails.Service;

import com.StudentDetails.StudentDetails.Entity.Users;
import com.StudentDetails.StudentDetails.Repositry.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
     AuthenticationManager authmanager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users saveUsers(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        return usersRepo.save(users);

    }

    public String verify(Users users) {
        Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(users.getUsername());
        }
        return "Failed";
    }
}
