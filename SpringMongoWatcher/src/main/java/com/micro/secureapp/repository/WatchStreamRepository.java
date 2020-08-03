package com.micro.secureapp.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.micro.secureapp.model.WatchStreamDoc;

public interface WatchStreamRepository extends MongoRepository<WatchStreamDoc, UUID>{
	
	public WatchStreamDoc findFirst1ByOrderByCreatedDateDesc();
}
