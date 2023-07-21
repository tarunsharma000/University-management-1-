package com.demo.service;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.model.JwtRequest;
import com.demo.model.JwtResponse;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import com.demo.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getPhoneNumber();
        String userPassword = jwtRequest.getPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Student student = studentRepo.findByPhoneNumber(userName);
        return new JwtResponse(student, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Student student = studentRepo.findByPhoneNumber(username);

        if (student != null) {
            return new org.springframework.security.core.userdetails.User(
            		student.getPhoneNumber(),
                    student.getPassword(),
                    getAuthority(student)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(Student student) {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        student.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
