package com.cts.PatientAppointmentBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;

import jakarta.servlet.http.HttpSession;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PatientService patientService; // Use the service layer to interact with patients

    @Autowired
    private DoctorService doctorService; // Use the service layer to interact with doctors

    @Autowired
    private AppointmentService appointmentService;

	public Optional<Patient> authenticatePatient(String email, String password) {
        Optional<Patient> patient = patientService.findByEmail(email);
        if (patient.isPresent() && passwordEncoder.matches(password, patient.get().getPassword())) {
            return patient;
        }
        return Optional.empty();
    }

    public Optional<Doctor> authenticateDoctor(String email, String password) {
        Optional<Doctor> doctor = doctorService.findByEmail(email);
        if (doctor.isPresent() && passwordEncoder.matches(password, doctor.get().getPassword())) {
            return doctor;
        }
        return Optional.empty();
    }

    public boolean isAdmin(String email, String password) {
        return email.equals("admin123@gmail.com") && password.equals("12345");
    }
}
