package com.patientmanagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.patientmanagement.model.BillingData;

public class BillingRowMapper implements RowMapper<BillingData>{

	@Override
	public BillingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		BillingData billingDetails = new BillingData();
		billingDetails.setBillingId(String.valueOf(rs.getInt("billing_id")));
		billingDetails.setAppointmentId(String.valueOf(rs.getInt("appointment_id")));
		billingDetails.setAmount(String.valueOf(rs.getFloat("amount")));
		billingDetails.setPaymentMode((null != rs.getString("payment_mode") && !rs.getString("payment_mode").equals("")) ? "complete" : "pending");
		
		return billingDetails;	
	}

}
