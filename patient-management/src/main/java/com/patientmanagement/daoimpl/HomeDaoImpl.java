package com.patientmanagement.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.patientmanagement.Utility;
import com.patientmanagement.dao.HomeDao;
import com.patientmanagement.model.Appointment;
import com.patientmanagement.model.BillingData;
import com.patientmanagement.model.Checkup;
import com.patientmanagement.model.Doctor;
import com.patientmanagement.rowmapper.AppointmenetRowMapper;
import com.patientmanagement.rowmapper.BillingRowMapper;
import com.patientmanagement.rowmapper.CheckupRowMapper;
import com.patientmanagement.rowmapper.DoctorRowMapper;

@Repository
public class HomeDaoImpl implements HomeDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Doctor> fetchDoctorDetails() {
		List<Doctor> doctorsList = new ArrayList<Doctor>();
		try {
			String sql = "SELECT dr_id, dr_name, dr_charge FROM tbl_doctor ORDER BY dr_name ASC";
			doctorsList = jdbcTemplate.query(sql,new DoctorRowMapper());
			return doctorsList;
		}catch (Exception e) {
			System.out.println("Exception occured while fetching doctors list : "
							+e.getMessage());
			return null;
		}
	}

	@Override
	public int saveCheckupData(Checkup checkup) {
		String sql = "insert into tbl_checkup"
				 + " (appointment_id, diagnosis, blood_pressure,"
				 + " weight, remark)"
				 + " values(?,?,?,?,?)";
	int rowsCount = 0;
	int updateRowsCount = 0;
	try {
		rowsCount = jdbcTemplate.update(sql, new Object[] {Integer.valueOf(checkup.getAppointmentId()),
			 checkup.getDisease(),Float.valueOf(checkup.getBloodPressure()),
			 Float.valueOf(checkup.getWeight()),checkup.getRemark()});
		if(rowsCount > 0) {
			String sqlQuery = "update tbl_appointment set status = ? where appointment_id = ?";
			updateRowsCount = jdbcTemplate.update(sqlQuery, new Object[] {"checkup complete", Integer.valueOf(checkup.getAppointmentId())});
			if(updateRowsCount > 0) {
				updateRowsCount = 0;
				String newSqlQuery = "insert into tbl_billing"
						 + " (appointment_id, amount, payment_mode)"
						 + " values(?,?,?)";
				updateRowsCount = jdbcTemplate.update(newSqlQuery, new Object[] {Integer.valueOf(checkup.getAppointmentId()),
						Float.valueOf(checkup.getAmount()),""});
				return updateRowsCount;
			}
		}
	}catch (Exception e) {
		System.out.println("Exception occured while inserting checkup data and inserting in "
				+ "billing table and updating appointment data : "
						+e.getMessage());
	}
return updateRowsCount;
	}

	@Override
	public List<Checkup> fetchCheckupData() {
		List<Checkup> checkUpList = new ArrayList<Checkup>();
		try {
			String sql = "SELECT appointment_id, diagnosis, blood_pressure, weight, remark FROM tbl_checkup";
			checkUpList = jdbcTemplate.query(sql, new CheckupRowMapper());
			return checkUpList;
		} catch (Exception e) {
			System.out.println(
					"Exception occured while fetching checkup data : " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<BillingData> fetchBillingData() {
		List<BillingData> billingDataList = new ArrayList<BillingData>();
		try {
			String sql = "SELECT billing_id, appointment_id, amount, payment_mode FROM tbl_billing";
			billingDataList = jdbcTemplate.query(sql, new BillingRowMapper());
			return billingDataList;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching billing list : " + e.getMessage());
			return null;
		}
	}

	@Override
	public int processToBilling(BillingData billingData) {
		String sql = "update tbl_billing set appointment_id = ?, amount = ?, payment_mode = ? "
				 + " where billing_id = ?";
	int rowsCount = 0;
	int updateRowsCount = 0;
	try {
		rowsCount = jdbcTemplate.update(sql, new Object[] {Integer.valueOf(billingData.getAppointmentId()),
				Float.valueOf(billingData.getAmount()),billingData.getPaymentMode(),billingData.getBillingId()});
		if(rowsCount > 0) {
			String sqlQuery = "update tbl_appointment set status = ? where appointment_id = ?";
			updateRowsCount = jdbcTemplate.update(sqlQuery, new Object[] {"complete", Integer.valueOf(billingData.getAppointmentId())});
		}
	}catch (Exception e) {
		System.out.println("Exception occured while updating billing data and updating appointment data : "
								+e.getMessage());
	}
	return updateRowsCount;
  }

	@Override
	public int submitAppointmentData(Appointment appointment) {
		String sql = "insert into tbl_appointment"
				 + " (patient_id, dr_id, amount, schedule_date, schedule_shift, status)"
				 + " values(?,?,?,?,?,?)";
		int rowsCount = 0;
	
		try {
			rowsCount = jdbcTemplate.update(sql, new Object[] {Integer.valueOf(appointment.getPatientId()),
					Integer.valueOf(appointment.getDoctorId()),appointment.getCharges(),
					Utility.getFormattedDate(appointment.getAppointmentDate()),appointment.getAppointmentTime(),"pending"});
		}catch (Exception e) {
			System.out.println("Exception occured while inserting appointment data : "+e.getMessage());
		}
		return rowsCount;
	}

	@Override
	public List<Appointment> fetchAppointmentData() {
		List<Appointment> appointmentList = new ArrayList<Appointment>();
		try {
			String sql = "SELECT app.appointment_id, app.patient_id, app.dr_id, doc.dr_name,"
					+ " app.amount, app.schedule_date, app.schedule_shift, app.status"
					+ " FROM tbl_appointment app JOIN tbl_doctor doc ON app.dr_id = doc.dr_id";
			appointmentList = jdbcTemplate.query(sql, new AppointmenetRowMapper());
			return appointmentList;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching appointment data : " + e.getMessage());
			return null;
		}
	}

}
