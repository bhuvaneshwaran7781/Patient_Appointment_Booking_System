package com.cts.PatientAppointmentBookingSystem.entity;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Appointment {
	
	@Id
	private String appointmentId;
	@PrePersist
	public void generateappointmentId() {
		this.appointmentId="APP"+new Random().nextInt(1000);
	}
	private String patientid;
	private String doctorid;
	private String doctor_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;
	private String time;
	private String patient_name;
	private boolean status;
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getDoctor_id() {
		return doctorid;
	}
	public void setDoctor_id(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
