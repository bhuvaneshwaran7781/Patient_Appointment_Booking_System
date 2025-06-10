package com.cts.PatientAppointmentBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;

public interface AppointmentService {

	public void save(Appointment appointment);

	public List<Appointment> findByPatientid(String patientid);

	public List<Appointment> findByDoctoridAndStatus(String id, boolean status);

	public List<Appointment> findByStatus(boolean status);

	public long count();

	public long countByStatus(boolean status);

	public Optional<Appointment> findById(String id);

	public List<Appointment> findByPatientidAndStatus(String patientId, boolean status);

	public boolean isAlreadyBooked(String doctorId, LocalDate appointmentDate, String appointmentTime);

	public List<Appointment> findAllAppointments();

	public Optional<Appointment> findByAppointmentId(String appointmentId);

	public void Delete(Appointment appointment);

}
