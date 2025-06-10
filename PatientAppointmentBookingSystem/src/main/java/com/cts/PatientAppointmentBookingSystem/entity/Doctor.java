package com.cts.PatientAppointmentBookingSystem.entity;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {
	@Id
	private String doctor_id;
	@PrePersist
	public void generateappointmentId() {
		this.doctor_id="DOC"+new Random().nextInt(1000);
	}
	@NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
 
    @NotBlank(message = "Specialization cannot be empty")
    private String specialization;
 
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true)
    private String email;
 
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;
 
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
             message = "Password must contain at least one uppercase letter and one number")
    private String password;
 
  
    private String confirmpassword;
 
    
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void getStatus(boolean status) {
		this.status = status;
	}
	public String getId() {
		return doctor_id;
	}
	public void setId(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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
