package com.patientmanagement.dao;

import com.patientmanagement.model.Patient;

public interface LoginDao {
	public Patient login(Patient user);
	public int doRegister(Patient patient);
}
