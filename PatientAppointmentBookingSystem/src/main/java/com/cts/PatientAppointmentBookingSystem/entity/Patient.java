package com.cts.PatientAppointmentBookingSystem.entity;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
// Import for fields not to be persisted
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Patient {

	@Id
	private String patientId;

	@PrePersist
	public void generateappointmentId() {
		this.patientId = "PAT" + new Random().nextInt(9000) + 1000; // Ensures a 4-digit number
	}

	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Gender cannot be empty")
	@Pattern(regexp = "male|female|nonbinary", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid gender")
	private String gender;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone number cannot be empty")
	private String phone;

	@NotBlank(message = "Address cannot be empty")
	@Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	private String address;

	@Min(value = 0, message = "Age must be a positive number")
	private int age;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	// Add more complex password regex if needed (e.g., for uppercase, special chars)
	private String password;

	
	@NotBlank(message = "Confirm password cannot be empty")
	private String confirmpassword;

	// Getters and Setters (already provided, just ensuring they are here for completeness)
	public String getId() { // Renamed for consistency with field name
		return patientId;
	}
	

	public void setId(String patientId) { // Renamed for consistency with field name
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
}