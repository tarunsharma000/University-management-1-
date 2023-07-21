package com.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("role")
public class Role {
	
	
	@Id
	private String role;
	
	private String description;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}

//{
//    "role": "manager",
//    "description": "Manager Role"
//}

//
//{
//    "name":"Tarun",
//    "age":"22",
//    "password":"ts97788",
//    "marks":"100",
//    "result":"passs",
//    "phoneNumber":"7668357117"
//}
