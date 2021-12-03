package com.patientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.patientmanagement.model.Patient;
import com.patientmanagement.model.Response;

public interface LoginService {
	public Patient login(Patient user);
	public ResponseEntity<Response> doRegister(Patient patient);
}
