package com.cts.PatientAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.repository.AppointmentRepository;
import com.cts.PatientAppointmentBookingSystem.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
    private AppointmentRepository appointmentRepository;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Override
	public Doctor register(Doctor doctor) {
//		String encodedpassword = passwordEncoder.encode(doctor.getPassword());
//		doctor.setPassword(encodedpassword);
//		String confirmpasswordencoded = passwordEncoder.encode(doctor.getConfirmpassword());
//		doctor.setConfirmpassword(confirmpasswordencoded);
		return doctorRepository.save(doctor);
	}
	@Override
	public List<Doctor> findByStatus(boolean status) {
		return doctorRepository.findByStatus(status);
	}
	@Override
	public Optional<Doctor> findByEmail(String email) {
		
		return doctorRepository.findByEmail(email);
	}
	@Override
	public Optional<Doctor> findById(String id) {
		// TODO Auto-generated method stub
		return doctorRepository.findById(id);
	}
	@Override
	public void delete(Doctor doctor) {
		doctorRepository.delete(doctor);
		
	} 
	
	@Override
	public Doctor findByName(String doctorName) {
		return doctorRepository.findByName(doctorName);
	}
	
	@Override
	public List<Appointment> getPendingAppointmentsForDoctor(String doctorId) {
		return appointmentRepository.findByDoctoridAndStatus(doctorId, false);
	}
	
	@Override
	public List<Appointment> getCompletedAppointmentsForDoctor(String doctorId) {
		return appointmentRepository.findByDoctoridAndStatus(doctorId, true);
	}
	@Override
	public void completeAppointment(String appointmentId, String doctorid) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
           
                appointment.setStatus(true); // Mark as completed
                appointmentRepository.save(appointment);
           
        }
		
	}
	@Override
	public List<Doctor> getAvailableDoctors() {
		
		return doctorRepository.findByStatus(true);
	}
	@Override
	public boolean uniqueEmail(String email) {
		Optional<Doctor> doctor = doctorRepository.findByEmail(email);
		return !doctor.isPresent();
	}

	
}
	

