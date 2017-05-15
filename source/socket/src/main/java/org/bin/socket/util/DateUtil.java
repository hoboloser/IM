package org.bin.socket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * yes = true , no = false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeek(Date date1, Date date2) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date1);
		int lastweek = ca.WEEK_OF_YEAR;

		ca.setTime(date2);
		int nowweek = ca.WEEK_OF_YEAR;

		if (lastweek == nowweek) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Sunday = 1
	 * </p>
	 * <p>
	 * Monday = 2
	 * </p>
	 * <p>
	 * Tuesday = 3
	 * </p>
	 * <p>
	 * Wednesday = 4
	 * </p>
	 * <p>
	 * Thursday = 5
	 * </p>
	 * <p>
	 * Friday = 6
	 * </p>
	 * <p>
	 * Saturday = 7
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static int dayForWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * 
	 * <p>
	 * Sunday = 1
	 * </p>
	 * <p>
	 * Monday = 2
	 * </p>
	 * <p>
	 * Tuesday = 3
	 * </p>
	 * <p>
	 * Wednesday = 4
	 * </p>
	 * <p>
	 * Thursday = 5
	 * </p>
	 * <p>
	 * Friday = 6
	 * </p>
	 * <p>
	 * Saturday = 7
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static int dayForWeek(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate;
		try {
			tmpDate = format.parse(date);
		} catch (ParseException e) {
			tmpDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(tmpDate);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}

	/**
	 * Calculated date "DATE" before "hour: minute: seconds" before or after,
	 * return -1 is before 
	 * return 1 is after
	 * return 0 at the same time
	 * 
	 * @return
	 */
	public static int hourBetDay(Date date, int hour, int minute, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, seconds);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		if (c.after(c1)) {
			return 1;
		}
		if (c.before(c1)) {
			return -1;
		}

		return 0;
	}

	public static String format(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss sss");
		return format.format(date);
	}
	
	public static String format(Date date,String format) {
		SimpleDateFormat formats = new SimpleDateFormat(format);
		return formats.format(date);
	}

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
						.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}
}
