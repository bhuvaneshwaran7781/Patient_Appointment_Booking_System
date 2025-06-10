package com.cts.PatientAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.PatientAppointmentBookingSystem.entity.Patient;


public interface PatientService {

	public Patient save(Patient patient);

	public Optional<Patient> findByEmail(String email);

	public List<Patient> findAll();

	public void registerPatient(Patient patient);

	public boolean uniqueEmail(String email);
}
