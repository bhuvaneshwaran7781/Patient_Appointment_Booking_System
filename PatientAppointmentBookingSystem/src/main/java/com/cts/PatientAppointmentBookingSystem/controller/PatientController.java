package com.cts.PatientAppointmentBookingSystem.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // Import this
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;
import com.cts.PatientAppointmentBookingSystem.service.AppointmentService;
import com.cts.PatientAppointmentBookingSystem.service.DoctorService;
import com.cts.PatientAppointmentBookingSystem.service.PatientService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid; // Import this

@Controller
@RequestMapping("/patient")
@SessionAttributes("user")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DoctorService doctorService;

	@GetMapping("/loginpage")
	public String showlogin() {
		return "login";
	}

	@GetMapping("/patientregister")
	public String show(Model model) {
		model.addAttribute("patientobj", new Patient());
		return "patientreg";
	}

	@PostMapping("/save-patient")
	public String savePatient(@Valid @ModelAttribute("patientobj") Patient patientobj, BindingResult result, Model model) {
	
	    model.addAttribute("patientobj", patientobj);
	    
	    if (result.hasErrors()) {
	    	model.addAttribute("errorMessage", "Invalid input data. Please check your fields.");
	    	model.addAttribute("messageType","danger");
	        return "patientreg"; // Return to the registration form
	    }
	    
	    patientService.registerPatient(patientobj);
	   	return "redirect:/patient/loginpage"; // Use redirect for successful POST
	}

	@GetMapping("/patientDashboard")
	public String patientDashboard(HttpSession session,Model model,@SessionAttribute("user") Patient patient) {
		if(!"PATIENT".equals(session.getAttribute("role"))) {
			return "login";
		}
		 List<Doctor> availableDoctors = doctorService.findByStatus(true);
		 model.addAttribute("doctors", availableDoctors);
		 
		 String patientId = patient.getId(); // Use getPatientId()
		 
		 List<Appointment> completedappointments = appointmentService.findByPatientidAndStatus(patientId,true);
		 session.setAttribute("completedAppointments", completedappointments);
		 
		 List<Appointment> upcomingAppointments = appointmentService.findByPatientidAndStatus(patientId,false);
         session.setAttribute("upcomingappointments", upcomingAppointments);

         
		 return "patDashboard";
	}
}