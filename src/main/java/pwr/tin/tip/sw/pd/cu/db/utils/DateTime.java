package pwr.tin.tip.sw.pd.cu.db.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	public static Date now() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(sdf.format(Calendar.getInstance().getTime()));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Long nowInMillis() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			return sdf.parse(sdf.format(Calendar.getInstance().getTime())).getTime();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
