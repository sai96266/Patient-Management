package com.patientmanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	public static Date getFormattedDate(String inputDate) {
		Date date = null;
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf1.parse(inputDate);
		}catch(Exception ex) {
			System.out.println("Exception occured while converting string to date :"+ex);
		}
	 return date;
	}
	
	public static String getFormattedStringDate(Date inputDate) {
		String outputString = "";
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			outputString = new SimpleDateFormat("dd-MM-yyyy").format(inputDate);
		}catch(Exception ex) {
			System.out.println("Exception occured while converting date to string:"+ex);
		}
	 return outputString;
	}
}
