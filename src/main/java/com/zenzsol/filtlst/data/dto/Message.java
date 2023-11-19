package com.zenzsol.filtlst.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Message {
	boolean submitted=false;
	
	@NotEmpty(message = "Enter name.")
    @Size(min = 5, max = 250, message = "Enter valid name.")
    private String name;
	
	@NotEmpty(message = "Enter email id.")
    @Size(min = 5, max = 350, message = "Enter valid email id.")
	@Email
    private String email;
	
	@NotEmpty(message = "Enter message.")
    @Size(min = 5, max = 550, message = "Enter valid message.")
    private String message;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSubmitted() {
		return submitted;
	}
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
	
    
}
