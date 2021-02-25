package in.co.sunrays.proj3.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Data Utility class to format data from one format to another
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class DataUtility {
	public static final String APP_DATE_FORMAT = "MM/dd/yyyy";
	public static final String APP_TIME_FOMATE = "dd/MM/yyyy HH:mm:ss";

	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FOMATE);

	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);

		} else {
			return 0;
		}
	}

	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}

		return date;
	}

	
	public static Date getDateSql(String val) {
		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(val);
		} catch (Exception e) {

		}

		return date;
	}

	
	public static String getDateString(Date date) {
		try {

			return formatter.format(date);
		} catch (Exception e) {

		}
		return "";
	}

	public static String getDate(Date date, int day) {
		return null;
	}

	public static Timestamp getTimestamp(String val) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(timeFormatter.parse(val).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;

	}

	public static Timestamp getTimestamp(long l) {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timestamp;
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
			
		}
		return timeStamp;
	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;

		}
	}

	
}
