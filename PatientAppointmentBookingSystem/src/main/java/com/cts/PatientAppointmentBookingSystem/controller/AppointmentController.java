package com.cts.PatientAppointmentBookingSystem.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.PatientAppointmentBookingSystem.entity.Appointment;
import com.cts.PatientAppointmentBookingSystem.entity.Doctor;
import com.cts.PatientAppointmentBookingSystem.entity.Patient;
import com.cts.PatientAppointmentBookingSystem.service.AppointmentService;
import com.cts.PatientAppointmentBookingSystem.service.DoctorService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/appointment")
@SessionAttributes("user")
public class AppointmentController {
   @Autowired
   public AppointmentService appointmentService;
   @Autowired
   public DoctorService doctorService;
   
    @PostMapping("/patientBooking")
    public String bookPat(@RequestParam("doctorselected") String doctorName,
                          @RequestParam("appointment_date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate appointmentDate,
                          @RequestParam("appointment_time") String appointmentTime,
                          HttpSession session,Model model) 
    {
        Patient patLogged = (Patient) session.getAttribute("patLogged");
        if (patLogged == null) {
            return "redirect:/login"; // Redirect if session data is missing
        }
     
        Doctor doctor = doctorService.findByName(doctorName);
        if (doctor == null) {
            return "redirect:/login"; 
        }
        
        String doctorId = doctor.getId(); 
        boolean isBooked = appointmentService.isAlreadyBooked(doctorId, appointmentDate, appointmentTime);
        if (isBooked) {
            model.addAttribute("errorMessage", "This appointment is already booked.");
            return "patDashboard";
        }

        String patientid = patLogged.getId();
        
        Appointment appointment = new Appointment();
        appointment.setPatientid(patientid);
        appointment.setDoctor_id(doctorId);
        appointment.setDoctor_name(doctor.getName());
        appointment.setPatient_name(patLogged.getName());
        appointment.setDate(appointmentDate);
        appointment.setTime(appointmentTime);
        
        appointmentService.save(appointment); 
        
        
        return "redirect:/appointment/upcomingAppointments"; 

    }

    @GetMapping("/upcomingAppointments")
    public String showUpcomingAppointments(HttpSession session,Model model,@SessionAttribute("user")Patient user) 
    {
    	
    	 List<Appointment> upcomingAppointments = appointmentService.findByPatientidAndStatus(user.getId(),false);
         session.setAttribute("upcomingappointments", upcomingAppointments);
       
         return "patDashboard"; 
    }
    
    @GetMapping("/patientBooking")
    public String showBookingPage(Model model) {
        List<Doctor> availableDoctors = doctorService.findByStatus(true);
        model.addAttribute("doctors", availableDoctors);
        return "patDashboard"; 
    }

    @PostMapping("/reschedule/{appointmentId}")
    public String reschedule(@PathVariable String appointmentId,
                             @RequestParam("appointment_date") LocalDate appointmentDate,
                             @RequestParam("appointment_time") String appointmentTime,
                             @RequestParam("doctorselected") String selectDoctor,
                             HttpSession session,Model model) {

        Optional<Appointment> existingAppointment = appointmentService.findByAppointmentId(appointmentId);

        if (existingAppointment.isPresent()) {
            Appointment appointment = existingAppointment.get();
            
            // Update fields
            appointment.setDate(appointmentDate);
            appointment.setTime(appointmentTime);
            appointment.setDoctor_name(selectDoctor);

            // Save changes
            appointmentService.save(appointment);

            // Refresh upcoming appointments in session
            List<Appointment> updatedAppointments = appointmentService.findByPatientid(appointment.getPatientid());
            session.setAttribute("upcomingappointments", updatedAppointments);
            
         
        }

        return "redirect:/patient/patientDashboard"; // Redirect back after successful reschedule
    }
    
    @GetMapping("/cancel/{appointmentId}")
	  public String cancel(@PathVariable String appointmentId ,HttpSession session) {

		  
		  Optional<Appointment> app= appointmentService.findByAppointmentId(appointmentId);
				appointmentService.Delete(app.get());

		  List<Appointment>patientstatus=appointmentService.findByPatientidAndStatus(app.get().getPatientid(),false);
		  session.setAttribute("upcomingappointments",patientstatus);
		  return"patDashboard";
	  }

}
