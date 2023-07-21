package com.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.*;
import com.demo.service.*;



@RestController
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/createNewRole",method=RequestMethod.POST)
	@PreAuthorize("hasRole('admin')")
	public Role createNewRole(@RequestBody Role role)
	{
		return roleService.createNewRole(role);
	}

}