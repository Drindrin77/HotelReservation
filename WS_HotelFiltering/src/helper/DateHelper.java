package helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static Date stringToDate(String date) {
		try { 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			return sdf.parse(date); 
		}catch (Exception e) { 
			return null; 
		}

	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date);  
        return strDate;
	}
	
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	public static boolean intervalReservationOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
		//We can reserve if endDate2 = startDate1 or startDate2 = endDate1
		return startDate1.compareTo(endDate2)<0 && startDate2.compareTo(endDate1)<0;
	}
}
