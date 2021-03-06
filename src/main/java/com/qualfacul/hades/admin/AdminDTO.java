package com.qualfacul.hades.admin;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class AdminDTO {

	@NotEmpty(message = "hades.admin.empty.name")
	private String name;

	@NotEmpty(message = "hades.admin.invalid.email")
	@Email(message = "hades.admin.invalid.email")
	private String email;

	@NotEmpty(message = "hades.admin.empty.password")
	@Length(min = 4, message = "hades.admin.invalid.password")
	private String password;

	public AdminDTO() {
	}

	public AdminDTO(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}