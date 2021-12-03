package com.patientmanagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.patientmanagement.Utility;
import com.patientmanagement.model.Appointment;

public class AppointmenetRowMapper implements RowMapper<Appointment>{

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Appointment appointmentDetails = new Appointment();
		appointmentDetails.setAppointmentId(String.valueOf(rs.getInt("appointment_id")));
		appointmentDetails.setPatientId(String.valueOf(rs.getInt("patient_id")));
		appointmentDetails.setDoctorId(String.valueOf(rs.getInt("dr_id")));
		appointmentDetails.setName(rs.getString("dr_name"));
		appointmentDetails.setCharges(String.valueOf(rs.getFloat("amount")));
		appointmentDetails.setAppointmentDate(Utility.getFormattedStringDate(rs.getDate("schedule_date")));
		appointmentDetails.setAppointmentTime(rs.getString("schedule_shift"));
		appointmentDetails.setAppointmentStatus(rs.getString("status"));
		
		return appointmentDetails;
	}

}
