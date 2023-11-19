package com.zenzsol.filtlst.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Register{

	boolean submitted;
	
	@NotEmpty(message = "Enter email id.")
    @Size(min = 5, max = 350, message = "Enter valid email id.")
	@Email
	String email;
	
	@NotEmpty(message = "Enter store name.")
    @Size(min = 5, max = 100, message = "Enter valid store name.")
    private String name;
	

	@NotEmpty(message = "Enter store website url.")
    @Size(min = 5, max = 250, message = "Enter store website url")
    private String url;
	


	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
}
