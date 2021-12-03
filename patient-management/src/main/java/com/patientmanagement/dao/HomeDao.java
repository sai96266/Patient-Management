package com.patientmanagement.dao;

import java.util.List;

import com.patientmanagement.model.Appointment;
import com.patientmanagement.model.BillingData;
import com.patientmanagement.model.Checkup;
import com.patientmanagement.model.Doctor;

public interface HomeDao {
	public List<Doctor> fetchDoctorDetails();
	public int saveCheckupData(Checkup checkup);
	public List<Checkup> fetchCheckupData();
	public List<BillingData> fetchBillingData();
	public int processToBilling(BillingData billingData);
	public int submitAppointmentData(Appointment appointment);
	public List<Appointment> fetchAppointmentData();
}
