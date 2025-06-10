package com.cts.PatientAppointmentBookingSystem.service;

import java.util.Optional;

import org.springframework.ui.Model;

import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;

import jakarta.servlet.http.HttpSession;

public interface LoginService {

	//String authenticateAndRedirect(String email, String password, HttpSession session, Model model);

	Optional<Patient> authenticatePatient(String email, String password);

	Optional<Doctor> authenticateDoctor(String email, String password);

	boolean isAdmin(String email, String password);

}
