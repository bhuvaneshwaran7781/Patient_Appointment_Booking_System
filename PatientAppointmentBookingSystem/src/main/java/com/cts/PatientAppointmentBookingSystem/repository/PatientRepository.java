package com.cts.PatientAppointmentBookingSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.PatientAppointmentBookingSystem.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

	Optional<Patient> findByEmail(String email);
	
	Optional<Patient> findById(String id);
	
	List<Patient> findAll();
	

}
