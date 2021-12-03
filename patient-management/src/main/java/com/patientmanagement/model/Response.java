package com.patientmanagement.model;

public class Response {
	private String message;
	private String description;
	private int statusCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int internalServerError) {
		this.statusCode = internalServerError;
	}
	
	@Override
	public String toString() {
		return "Response [message=" + message + ", description=" + description + ", statusCode=" + statusCode + "]";
	}
}
