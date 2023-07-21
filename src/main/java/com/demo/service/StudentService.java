package com.demo.service;

import java.util.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.model.*;
import com.demo.repository.*;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public void initRoleAndUser()
	{
		Student st=studentRepo.findByPhoneNumber("8958958313");
		if(st!=null)
		{
			return;
		}
		Student student=new Student();
		student.setName("Tarun-Admin");
		student.setStatus(true);
		student.setPhoneNumber("8958958313");
		student.setPassword(getEncodedPassword("admin@123"));
		List<Role> list=new ArrayList<>();
		Role role=roleRepository.findById("admin").orElse(null);
		list.add(role);
		student.setRoles(list);
		studentRepo.save(student);
	}
	
	public String Register(Student student)
	{
		String phoneNumber=student.getPhoneNumber();
		Student st=studentRepo.findByPhoneNumber(phoneNumber);
		if(st!=null)
		{
			return "Phone Number already register";
		}
		List<Role> list=new ArrayList<>();
		Role role=roleRepository.findById("user").orElse(null);
		list.add(role);
		student.setId(UUID.randomUUID().toString().split("-")[0]);
		student.setStatus(false);
		student.setRoles(list);
		student.setPassword(getEncodedPassword(student.getPassword()));
		studentRepo.save(student);
		return "Successfully register";
	}
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	public String ActivateStudent(String phoneNumber) {
		Student student=studentRepo.findByPhoneNumber(phoneNumber);
		if(student==null)
		{
			return "student does not exist";
		}
		student.setStatus(true);
		studentRepo.save(student);
		return "Student is activated";
	}

	public String insertResult(String phoneNumber, Student student) {
		Student st=studentRepo.findByPhoneNumber(phoneNumber);
		if(st==null)
		{
			return "student does not exist";
		}
		st.setMarks(student.getMarks());
		if(student.getMarks()>40)
		{
		    st.setResult("Pass");
		}
		else
		{
			st.setResult("Fail");
		}
		studentRepo.save(st);
		return "Result updated successfully";
	}

	public String viewResult(String phoneNumber) {
		// TODO Auto-generated method stub
		Student st=studentRepo.findByPhoneNumber(phoneNumber);
		if(!st.isStatus())
		{
			return "You are not active.Please contact your admin";
		}
		
		String name=st.getName();
		int marks=st.getMarks();
		if(marks==0)
		{
			return "Your result is not declared yet!!!";
		}
		String result=st.getResult();
		return name+" your marks is "+marks+" You are "+result;
		}

}

