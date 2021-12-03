package com.patientmanagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.patientmanagement.model.Checkup;

public class CheckupRowMapper implements RowMapper<Checkup>{
	public Checkup mapRow(ResultSet rs, int rowNum) throws SQLException {	
		Checkup checkupDetails = new Checkup();
		checkupDetails.setAppointmentId(String.valueOf(rs.getInt("appointment_id")));
		checkupDetails.setDisease(rs.getString("diagnosis"));
		checkupDetails.setBloodPressure(String.valueOf(rs.getFloat("blood_pressure")));
		checkupDetails.setWeight(String.valueOf(rs.getFloat("weight")));
		checkupDetails.setRemark(rs.getString("remark"));
		
		return checkupDetails;
	}
}
