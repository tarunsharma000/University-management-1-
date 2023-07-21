package com.demo.repository;

import org.springframework.stereotype.Repository;

import com.demo.model.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

	Student findByPhoneNumber(String phoneNumber);
}

