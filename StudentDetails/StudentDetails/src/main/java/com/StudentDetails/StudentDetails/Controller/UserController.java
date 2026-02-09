//package com.StudentDetails.StudentDetails.Controller;
//
//import com.StudentDetails.StudentDetails.Entity.Users;
//import com.StudentDetails.StudentDetails.Service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserController {
//    @Autowired
//    private UsersService usersService;
//
//    @PostMapping("/register")
//    public Users register (@RequestBody Users users){
//        return usersService.saveUsers(users);
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody Users users){
//        return usersService.verify(users);
//    }
//}

package com.StudentDetails.StudentDetails.Controller;


import com.StudentDetails.StudentDetails.Entity.Users;
import com.StudentDetails.StudentDetails.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users users) {

        return userService.saveUsers(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        String token = userService.verify(users);

        if (token == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok(Map.of("token", token));  // <-- Return JSON
    }
}
