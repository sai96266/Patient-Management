package com.patientmanagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.patientmanagement.model.Doctor;

public class DoctorRowMapper implements RowMapper<Doctor>{

	 public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {	
		    Doctor doctorDetails = new Doctor();
		    doctorDetails.setDoctorId(String.valueOf(rs.getInt("dr_id")));
		    doctorDetails.setName(rs.getString("dr_name"));
			doctorDetails.setCharges(String.valueOf(rs.getFloat("dr_charge")));
			
			return doctorDetails;
		}

}