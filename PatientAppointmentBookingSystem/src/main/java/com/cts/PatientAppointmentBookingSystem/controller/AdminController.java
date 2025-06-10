package com.cts.PatientAppointmentBookingSystem.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.cts.PatientAppointmentBookingSystem.model.Doctor;
//import com.cts.PatientAppointmentBookingSystem.repository.DoctorRepository;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Controller
//public class AdminController {
//	@Autowired
//	public DoctorRepository doctorrepo;
//	@GetMapping("/admin")
//	public String showAdmin() {
//		return "/adminDash";
//	}
//	@GetMapping("/adminDash")
//	public String showAdminDash(Model model) {
//		List<Doctor> manage = doctorrepo.findByStatus(false);
//		model.addAttribute("manageDoctors", manage);
//		System.out.println(manage);
//		return "login";
//	}
//	
//}
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;
import com.cts.PatientAppointmentBookingSystem.service.AppointmentService;
import com.cts.PatientAppointmentBookingSystem.service.DoctorService;
import com.cts.PatientAppointmentBookingSystem.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private AppointmentService appointmentService; 
	@GetMapping("/admin")
	public String admin() {
		return "login";
}    
	
	@GetMapping("/approve/{id}")
	public String approve(@PathVariable String id,HttpSession session) {
		Optional<Doctor>approve=doctorService.findById(id);
		if(approve.isPresent()) {
			approve.get().setStatus(true);
			doctorService.register(approve.get());
			
			List<Doctor> li = doctorService.findByStatus(true);
			session.setAttribute("alldoctor", li);	
	}
		return "redirect:/admin/showDoctor";
}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable String id) {
		Optional<Doctor>remove=doctorService.findById(id);
		if(remove.isPresent()) {
			doctorService.delete(remove.get());
	}return "redirect:/admin/showDoctor";
	}
	
	
	@GetMapping("/showDoctor")
	public String showfalse(HttpSession session) {
		List<Doctor>mandoct =doctorService.findByStatus(false);
		session.setAttribute("managedoctor", mandoct);
		 return "adminDashboard";
	}

	
	@GetMapping("/view")
    public String getAllDetails(Model model, HttpSession session) {
		
		List<Doctor>mandoct =doctorService.findByStatus(false);
		session.setAttribute("managedoctor", mandoct);
		
		List<Appointment> appointmentslist = appointmentService.findByStatus(false);
		session.setAttribute("viewAllAppointments", appointmentslist);
	
		List<Doctor> li = doctorService.findByStatus(true);
		session.setAttribute("alldoctor", li);	
		
        List<Patient> allPatients = patientService.findAll();
        session.setAttribute("allPatientsList", allPatients); 
        model.addAttribute("showpatients", allPatients);
        
        long totalAppointments = appointmentService.count(); 
        model.addAttribute("totalAppCount",totalAppointments);
        long completedAppointments = appointmentService.countByStatus(true);
        model.addAttribute("totalCompleted", completedAppointments);
        long pendingAppointments = appointmentService.countByStatus(false);
        model.addAttribute("totalPending", pendingAppointments);
        		
        return "adminDashboard"; 
    }
}

 
