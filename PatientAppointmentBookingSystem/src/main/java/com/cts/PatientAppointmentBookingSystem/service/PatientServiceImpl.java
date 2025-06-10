package com.cts.PatientAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.PatientAppointmentBookingSystem.entity.Patient;
import com.cts.PatientAppointmentBookingSystem.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	@Autowired
	private PatientRepository patientrepo;
	@Override
	public Patient save(Patient patient) {
		return patientrepo.save(patient);
		
	}
	
	public Optional<Patient> findByEmail(String email) {
		// TODO Auto-generated method stub
		return patientrepo.findByEmail(email);
	}
	
	public List<Patient> findAll(){
		return patientrepo.findAll();
	}

	@Override
	public void registerPatient(Patient patient) {
		String encodedpassword = PasswordEncoder.encode(patient.getPassword());
		patient.setPassword(encodedpassword);
		String confirmpasswordencoded = PasswordEncoder.encode(patient.getConfirmpassword());
		patient.setConfirmpassword(confirmpasswordencoded);
		patientrepo.save(patient);
		
	}

	@Override
	public boolean uniqueEmail(String email) {
		Optional<Patient> patient = patientrepo.findByEmail(email);
		return !patient.isPresent();
	}
	

}
