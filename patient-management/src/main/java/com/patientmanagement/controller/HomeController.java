package com.patientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patientmanagement.model.Appointment;
import com.patientmanagement.model.BillingData;
import com.patientmanagement.model.Checkup;
import com.patientmanagement.model.Response;
import com.patientmanagement.service.HomeService;

@Controller

public class HomeController {
	private HomeService homeService;
	
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@GetMapping("/doctorDetails")
	@ResponseBody
	public ResponseEntity<Object> fetchDoctorDetails() {
		return homeService.fetchDoctorDetails();
	}
	
	@PostMapping("/saveCheckupData")
	@ResponseBody
	public ResponseEntity<Response> saveCheckupData(@RequestBody Checkup checkup) {
		return homeService.saveCheckupData(checkup);
	}
	
	@GetMapping("/fetchCheckupData")
	@ResponseBody
	public ResponseEntity<Object> fetchCheckupData() {
		return homeService.fetchCheckupData();
	}
	
	@GetMapping("/fetchBillingData")
	@ResponseBody
	public ResponseEntity<Object> fetchBillingData() {
		return homeService.fetchBillingData();
	}
	
	@PostMapping("/processToBilling")
	@ResponseBody
	public ResponseEntity<Response> processToBilling(@RequestBody BillingData billingData) {
		return homeService.processToBilling(billingData);
	}
	
	@PostMapping("/submitAppointmentData")
	@ResponseBody
	public ResponseEntity<Response> submitAppointmentData(@RequestBody Appointment appointment) {
		return homeService.submitAppointmentData(appointment);
	}
	
	@GetMapping("/fetchAppointmentData")
	@ResponseBody
	public ResponseEntity<Object> fetchAppointmentData() {
		return homeService.fetchAppointmentData();
	}
}
