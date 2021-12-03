package com.patientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.patientmanagement.model.Appointment;
import com.patientmanagement.model.BillingData;
import com.patientmanagement.model.Checkup;
import com.patientmanagement.model.Response;

public interface HomeService {
	public ResponseEntity<Object> fetchDoctorDetails();
	public ResponseEntity<Response> saveCheckupData(Checkup checkup);
	public ResponseEntity<Object> fetchCheckupData();
	public ResponseEntity<Object> fetchBillingData();
	public ResponseEntity<Response> processToBilling(BillingData billingData);
	public ResponseEntity<Response> submitAppointmentData(Appointment appointment);
	public ResponseEntity<Object> fetchAppointmentData();
 }
