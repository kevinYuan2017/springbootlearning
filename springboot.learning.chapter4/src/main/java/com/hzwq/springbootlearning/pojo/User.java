package com.hzwq.springbootlearning.pojo;

public class User {
	private String id;
	private String username;
	private String note;
	
	public User(String id, String username, String note) {
		super();
		this.id = id;
		this.username = username;
		this.note = note;
	}
	public User() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "User {id=" + id + ", username=" + username + ", note=" + note + "}";
	}
}
