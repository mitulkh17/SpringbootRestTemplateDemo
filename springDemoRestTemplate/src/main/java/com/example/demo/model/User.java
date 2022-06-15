package com.example.demo.model;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String email;

	public User() {

	}

	public User(String firstName, String lastName, String email, long id) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.id = id;
	}

	public User(String firstName, String lastName, String email) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
