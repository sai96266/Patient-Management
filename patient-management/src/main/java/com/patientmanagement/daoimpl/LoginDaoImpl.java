package com.patientmanagement.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.patientmanagement.Utility;
import com.patientmanagement.dao.LoginDao;
import com.patientmanagement.model.Patient;
import com.patientmanagement.rowmapper.UserRowMapper;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Patient login(Patient user) {
		 String sql = "SELECT patient_id, patient_name FROM tbl_patient WHERE patient_email = ? AND patient_password = ? ";
		 Patient patient = null;
		 try {
			 patient =  jdbcTemplate.queryForObject(sql, 
				 new UserRowMapper(), 
				 new Object[] {user.getEmailId(),user.getPassword()});
		 }catch (Exception e) {
				System.out.println("Exception occured while checking patient login : "
								+e.getMessage());
		 }
		return patient;
	}

	@Override
	public int doRegister(Patient patient) {
		String sql = "insert into tbl_patient"
					 + " (patient_name, patient_dob, patient_gender,"
					 + " patient_email, patient_password)"
					 + " values(?,?,?,?,?)";
		int rows = 0;
		
		try {
		 rows = jdbcTemplate.update(sql, new Object[] {patient.getPatientName(),
				 Utility.getFormattedDate(patient.getDob()),patient.getGender(), patient.getEmailId(),
				patient.getPassword()});
		}catch (Exception e) {
			System.out.println("Exception occured while inserting patient data : "+e.getMessage());
		}
	 return rows;
	}

}
