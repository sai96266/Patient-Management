package com.patientmanagement.serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.patientmanagement.dao.LoginDao;
import com.patientmanagement.model.Patient;
import com.patientmanagement.model.Response;
import com.patientmanagement.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	private LoginDao loginDao;
	
	public LoginServiceImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@Override
	public Patient login(Patient user) {
		Patient userObj = null;
		if(!Optional.ofNullable(user.getEmailId()).get().equals("") ||
				!Optional.ofNullable(user.getPassword()).get().equals("")) {
			userObj = loginDao.login(user);
		}
		return userObj;
	}

	@Override
	public ResponseEntity<Response> doRegister(Patient patient) {
		Response response = new Response();
		int resultsRow = loginDao.doRegister(patient);
		if(resultsRow > 0) {
			 response.setMessage(HttpStatus.OK.toString());
			 response.setDescription("Patient Registerd Successfully");
			 response.setStatusCode(200);
			 return new ResponseEntity<Response>(response,HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
}
