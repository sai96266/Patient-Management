package com.patientmanagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.patientmanagement.model.Patient;

public class UserRowMapper implements RowMapper<Patient> {

	 public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {	
		    Patient user = new Patient();
		    user.setPatientId(String.valueOf(rs.getInt("patient_id")));
			user.setPatientName(rs.getString("patient_name"));
			return user;
		}

}
