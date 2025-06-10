package com.cts.PatientAppointmentBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	

	public List<Appointment> findByPatientid(String patientId);

	public List<Appointment> findByDoctoridAndStatus(String id, boolean status);

	public List<Appointment> findByStatus(boolean status);
	
	public long countByStatus(boolean status);

	public List<Appointment> findByPatientidAndStatus(String patientId, boolean status);

	public List<Appointment> findByDoctoridAndDateAndTime(String doctorId, LocalDate date, String time);
	
	public Optional<Appointment> findByAppointmentId(String appointmentId);
	

}
