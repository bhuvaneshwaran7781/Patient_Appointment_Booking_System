package com.cts.PatientAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;


public interface DoctorService {

	public Doctor register(Doctor doctor);



	public List<Doctor> findByStatus(boolean status);

	public Optional<Doctor> findByEmail(String email);

	public Optional<Doctor> findById(String id);

	public void delete(Doctor doctor);

	public Doctor findByName(String doctorName);



	public List<Appointment> getPendingAppointmentsForDoctor(String doctorId);



	public List<Appointment> getCompletedAppointmentsForDoctor(String doctorId);



	public void completeAppointment(String id, String doctorid);



	public List<Doctor> getAvailableDoctors();



	public boolean uniqueEmail(String email);

	
		
		
	

}
