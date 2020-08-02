package com.micro.secureapp.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private UUID _id;
	private String name;
	
	
	public User() {
		super();
	}


	public UUID get_id() {
		return _id;
	}


	public void set_id(UUID _id) {
		this._id = _id;
	}


	@Override
	public String toString() {
		return "User [_id=" + _id + ", name=" + name + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
