package com.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.*;
import com.demo.repository.*;



@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role)
	{
		return roleRepository.save(role);
	}

}
