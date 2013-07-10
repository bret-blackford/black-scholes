package mBret.misc;

import java.text.SimpleDateFormat;

import java.util.Calendar;

/**
 * 
 * info from http://www.rgagnon.com/javadetails/java-0106.html
 */

public class DateUtils {

	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_NOW2 = "yyyy-MM-dd_HH.mm.ss";

	public static String now() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW2);

		return sdf.format(cal.getTime());

	}

	public static void main(String arg[]) {

		System.out.println("Now : " + DateUtils.now());

	}

}
