package com.patientmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.patientmanagement.model.Patient;
import com.patientmanagement.model.Response;
import com.patientmanagement.service.LoginService;

@Controller
public class LoginController {
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/") 
	public String index(){
		return "redirect:/login";
	}
	
	@GetMapping("/login") 
	public String getLogin(){
		return "login";
	}
	
	@GetMapping("/home") 
	public String home(){
		return "home";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Patient> login(@RequestBody Patient user) throws Exception {
		Patient patientObj = loginService.login(user);
		if(null == patientObj) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}	
		return new ResponseEntity<Patient>(patientObj,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Response> doRegister(@RequestBody Patient patient) {
		return loginService.doRegister(patient);
	}
}
