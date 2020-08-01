package com.micro.secureapp.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.micro.secureapp.model.User;

@Repository
public interface UserRepo extends ReactiveMongoRepository<User, String> {

}
