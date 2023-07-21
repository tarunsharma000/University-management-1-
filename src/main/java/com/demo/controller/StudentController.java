package com.demo.controller;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.*;
import com.demo.service.*;



@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	  @PostConstruct
	    public void initRoleAndUser() {
		  studentService.initRoleAndUser();
	    }

	  
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String Register(@RequestBody Student student)
	{
		return studentService.Register(student);
	}
	
	
	@RequestMapping(value="/activatestudent/{phoneNumber}",method=RequestMethod.PUT)
	@PreAuthorize("hasRole('admin')")
	public String ActivateStudent(@PathVariable("phoneNumber") String phoneNumber)
	{
		return studentService.ActivateStudent(phoneNumber);
	}
	
	@RequestMapping(value="/insertresult/{phoneNumber}",method=RequestMethod.PUT)
	@PreAuthorize("hasRole('admin')")
	public String insertResult(@PathVariable("phoneNumber") String phoneNumber,@RequestBody Student student)
	{
		return studentService.insertResult(phoneNumber,student);
	}
	
	@RequestMapping(value="/viewresult/{phoneNumber}",method=RequestMethod.GET)
	@PreAuthorize("hasRole('user')")
	public String viewResult(@PathVariable("phoneNumber") String phoneNumber)
	{
		return studentService.viewResult(phoneNumber);
	}
	
	
}
