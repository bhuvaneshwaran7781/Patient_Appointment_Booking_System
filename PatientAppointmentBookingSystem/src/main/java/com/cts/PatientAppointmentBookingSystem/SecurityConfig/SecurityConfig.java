package com.cts.PatientAppointmentBookingSystem.SecurityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
 
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests(configurer->configurer
				.anyRequest().permitAll()
				);
		return http.build();
		}
	
	
	@Bean
	public BCryptPasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	

}