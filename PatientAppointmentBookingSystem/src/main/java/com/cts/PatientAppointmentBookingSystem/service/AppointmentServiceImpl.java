package com.cts.PatientAppointmentBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	
	@Autowired
	public AppointmentRepository appointmentrepo;
	@Override
	public void save(Appointment appointment) {
		appointmentrepo.save(appointment);
		
	}
	@Override
	public List<Appointment> findByPatientid(String patientid) {
		
		return appointmentrepo.findByPatientid(patientid);
	}
	@Override
	public List<Appointment> findByDoctoridAndStatus(String id, boolean status) {
		
		return appointmentrepo.findByDoctoridAndStatus(id, status);
	}
	@Override
	public List<Appointment> findByStatus(boolean status) {
		
		return appointmentrepo.findByStatus(status);
	}
	@Override
	public long count() {
		return appointmentrepo.count();
	}
	@Override
	public long countByStatus(boolean status) {
		return appointmentrepo.countByStatus(status);
	}
	@Override
	public Optional<Appointment> findById(String appointmentId) {
		// TODO Auto-generated method stub
		return appointmentrepo.findById(appointmentId);
	}
	@Override
	public List<Appointment> findByPatientidAndStatus(String patientId, boolean status) {
		return appointmentrepo.findByPatientidAndStatus(patientId,status);
	}
//	@Override
//	public boolean findByDoctoridAndDateAndTime(String doctorId, LocalDate date, String time) {
//		
//		return appointmentrepo.findByDoctoridAndDateAndTime(doctorId,date,time).isPresent();
//	}
	@Override
	public boolean isAlreadyBooked(String doctorId, LocalDate appointmentDate, String appointmentTime) {
		List<Appointment> booked= appointmentrepo.findByDoctoridAndDateAndTime(doctorId, appointmentDate, appointmentTime);	
		return !booked.isEmpty();
	}
	@Override
	public List<Appointment> findAllAppointments() {
		return appointmentrepo.findAll();
	}
	@Override
	public Optional<Appointment> findByAppointmentId(String appointmentId) {
		// TODO Auto-generated method stub
		return appointmentrepo.findByAppointmentId(appointmentId);
	}
	@Override
	public void Delete(Appointment appointment) {
		appointmentrepo.delete(appointment);
		
	}
	
	
	
	

}
