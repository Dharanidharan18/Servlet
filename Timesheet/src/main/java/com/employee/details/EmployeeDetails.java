package com.employee.details;

public class EmployeeDetails {
	
	private int empID;
	
	private String name;
	private String phoneNumber;
	private String email;
	private String password;

	public EmployeeDetails( int empID, String name, String phoneNumber ,String email, String password) {
		
		this.empID = empID;
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public int getEmpID() {
		return empID;
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
	
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "EmployeeDetails [empID=" + empID + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", password=" + password + "]";
	}


	


}





