package com.employee.details;

public class EmployeeDetails {
	private String email;
	private String name;
	private String phoneNumber;

	public EmployeeDetails( String name, String phoneNumber ,String email) {
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}


	@Override
	public String toString() {
		return "EmployeeDetails [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}


}





