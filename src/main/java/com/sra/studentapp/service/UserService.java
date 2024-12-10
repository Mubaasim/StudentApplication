package com.sra.studentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.model.User;
import com.sra.studentapp.repository.StudentRepository;
import com.sra.studentapp.repository.UserRepository;
import com.sra.studentapp.security.JWTService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StudentRepository studentRepository;



    public User register(User user) {
        userRepository.save(user);
        return user;
    }

    public ResponseEntity<String> verify(User user, HttpServletResponse response) {
    	try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                response.addHeader("Set-Cookie", "token=" + token + "; HttpOnly; SameSite=Strict");
                Student student = studentRepository.findByEmail(user.getUsername());
                return ResponseEntity.ok(student.getId());
            }
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed");
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("Invalid username or password");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An unexpected error occurred: " + e.getMessage());
	    }
    }
}
