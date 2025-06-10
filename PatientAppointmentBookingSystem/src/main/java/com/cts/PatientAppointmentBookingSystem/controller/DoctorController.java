package com.cts.PatientAppointmentBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.service.AppointmentService;
import com.cts.PatientAppointmentBookingSystem.service.DoctorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/doctor")
@SessionAttributes("user")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@GetMapping("/accessDenied")
		public String login() {
			return "accessDenied";
	}
	
	@GetMapping("/doctorregister")
	public String show(Model model ) {
		Doctor doctor=new Doctor();
		model.addAttribute("doctor", doctor);
		return "doctorreg";
	}
	
	@PostMapping("/savedoctor")
    public String saveDoctor(@Valid @ModelAttribute Doctor doctor,BindingResult result,Model model) {
		String encodedpassword = PasswordEncoder.encode(doctor.getPassword());
		doctor.setPassword(encodedpassword);
		String confirmpasswordencoded = PasswordEncoder.encode(doctor.getConfirmpassword());
		doctor.setConfirmpassword(confirmpasswordencoded);
		
		
		if(result.hasErrors()) {
			model.addAttribute("errorMessage", "Invalid input data. Please check your fields.");
			return "doctorreg";
		}
		
		doctorService.register(doctor);
		
		return "successDoc";
	}
	
	@GetMapping("/doctorDashboard")
	public String showDashboard(HttpSession session,Model model,@SessionAttribute("user")Doctor doctor) {
		
		String doctorId= doctor.getId();
		
		List<Appointment> patientAppointments= doctorService.getPendingAppointmentsForDoctor(doctorId);
		session.setAttribute("patientAppointment", patientAppointments);
		model.addAttribute("appointmentrequests",patientAppointments);
		
		List<Appointment> completedAppointments = doctorService.getCompletedAppointmentsForDoctor(doctorId);
	    session.setAttribute("AppoCompleted", completedAppointments);
	    
		return "docDashboard";
	}
	
	@GetMapping("/complete/{id}")
	public String approve(@PathVariable String id, HttpSession session, Model model) {

		
		Doctor currentDoctor = (Doctor) session.getAttribute("user");
		if(currentDoctor!=null) 
		{
			doctorService.completeAppointment(id,currentDoctor.getId());
		}
	    return "redirect:/doctor/doctorDashboard";
}

}
