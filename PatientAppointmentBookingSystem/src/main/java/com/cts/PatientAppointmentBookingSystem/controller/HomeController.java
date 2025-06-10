package com.cts.PatientAppointmentBookingSystem.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;
import com.cts.PatientAppointmentBookingSystem.service.AppointmentService;
import com.cts.PatientAppointmentBookingSystem.service.DoctorService;
import com.cts.PatientAppointmentBookingSystem.service.LoginService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/show")
	public String showhome() {
		return "home";
	}
	
	@GetMapping("/loginpage")
	public String showLogin() {
		return "login";
	}
	
	
	@PostMapping("/signIn")
	public String login(@RequestParam String email, 
			            @RequestParam String password,  
			            Model model, 
			            HttpSession session) {
		
	    Optional<Patient> patient = loginService.authenticatePatient(email,password);
	    if (patient.isPresent()) {
	        session.setAttribute("user", patient.get());
	        session.setAttribute("role", "PATIENT");
	        session.setAttribute("patLogged", patient.get());
	        
	        session.setAttribute("appointments", appointmentService.findByPatientid(patient.get().getId()));
	        session.setAttribute("doctors", doctorService.findByStatus(true));

			 String patientId = patient.get().getId();
			
			 session.setAttribute("completedAppointments",appointmentService.findByPatientidAndStatus(patientId,true));
			
	        return "redirect:/patient/patientDashboard "; 
	    }
	    
	    Optional<Doctor> doctor = doctorService.findByEmail(email);
	    if (doctor.isPresent() && passwordEncoder.matches(password, doctor.get().getPassword())) {
	        session.setAttribute("user", doctor.get());
	        session.setAttribute("role", "DOCTOR");
	        session.setAttribute("doctorLogged", doctor.get());
	        
	        if(doctor.get().isStatus()==false)
	        {
	        	return "redirect:/doctor/accessDenied";
	        }
	        return "redirect:/doctor/doctorDashboard"; 
	    }
	    
	    
	    if (loginService.isAdmin(email, password)) {
	        return "redirect:/admin/view"; 
	    }
		
		else {
            String errorMsg = "Invalid Email or Password. Please try again.";
            model.addAttribute("errorMessage", errorMsg);
            return "login";
        }
	    
	    
		}


}


	
	
	

	

