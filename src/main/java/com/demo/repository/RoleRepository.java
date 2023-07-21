package com.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.*;



@Repository
public interface RoleRepository extends MongoRepository<Role,String> {

}