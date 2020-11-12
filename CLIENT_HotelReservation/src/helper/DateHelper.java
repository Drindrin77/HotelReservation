package helper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
	
	public static Date localDatetoDate(LocalDate localDate) {
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
        return date;
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

}
