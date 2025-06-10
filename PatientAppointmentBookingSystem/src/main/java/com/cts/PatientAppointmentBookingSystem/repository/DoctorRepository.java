package com.cts.PatientAppointmentBookingSystem.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
@Repository

public interface DoctorRepository extends JpaRepository<Doctor,String>{

	public Optional<Doctor> findByEmail(String email);

	public List<Doctor> findByStatus(boolean status);

	public Doctor findByName(String doctorPhone);

	public Optional<Doctor> findById(String id);

}
