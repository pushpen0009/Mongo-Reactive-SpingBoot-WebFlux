package com.micro.secureapp.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.mongodb.client.model.changestream.OperationType;

public class WatchStreamDoc {

	@Id
	private UUID _id;
	private String resumeToken;
	private User user;
	private OperationType operationType;
	private long createdDate;
	
	public long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getResumeToken() {
		return resumeToken;
	}
	public void setResumeToken(String resumeToken) {
		this.resumeToken = resumeToken;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public UUID get_id() {
		return _id;
	}
	public void set_id(UUID _id) {
		this._id = _id;
	}
	
	@Override
	public String toString() {
		return "WatchStreamDoc [_id=" + _id + ", resumeToken=" + resumeToken + ", user=" + user + ", operationType="
				+ operationType + ", createdDate=" + createdDate + "]";
	}
}
