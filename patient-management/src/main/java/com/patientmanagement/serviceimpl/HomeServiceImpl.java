package com.patientmanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.patientmanagement.dao.HomeDao;
import com.patientmanagement.model.Appointment;
import com.patientmanagement.model.BillingData;
import com.patientmanagement.model.Checkup;
import com.patientmanagement.model.Doctor;
import com.patientmanagement.model.Response;
import com.patientmanagement.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{
	@Autowired
	private HomeDao homeDao;
	
	@Override
	public ResponseEntity<Object> fetchDoctorDetails() {
		List<Doctor> doctorsList = homeDao.fetchDoctorDetails();
		if(null == doctorsList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(doctorsList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> saveCheckupData(Checkup checkup) {
		Response response = new Response();
		int resultsRow = homeDao.saveCheckupData(checkup);
			if(resultsRow > 0) {
			response.setMessage(HttpStatus.OK.toString());
			response.setDescription("Checkup data saved Successfully");
			response.setStatusCode(200);
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}

	@Override
	public ResponseEntity<Object> fetchCheckupData() {
		List<Checkup> checkupList = homeDao.fetchCheckupData();
		if(null == checkupList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(checkupList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> fetchBillingData() {
		List<BillingData> billingList = homeDao.fetchBillingData();
		if(null == billingList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(billingList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Response> processToBilling(BillingData billingData) {
		Response response = new Response();
		int resultsRow = homeDao.processToBilling(billingData);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Billing data saved Successfully");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}

	@Override
	public ResponseEntity<Response> submitAppointmentData(Appointment appointment) {
		Response response = new Response();
		int resultsRow = homeDao.submitAppointmentData(appointment);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Appointment data saved Successfully");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}

	@Override
	public ResponseEntity<Object> fetchAppointmentData() {
		List<Appointment> appointmentList = homeDao.fetchAppointmentData();
		if(null == appointmentList) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(appointmentList,HttpStatus.OK);
	}

}
