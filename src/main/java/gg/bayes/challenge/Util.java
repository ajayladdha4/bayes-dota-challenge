package gg.bayes.challenge;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Util {
//	public static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	public static DateTimeFormatter formatter
    = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");



	public static long parseDate(String timestamp) {
		try {
			return LocalTime.parse(timestamp, formatter).get(ChronoField.MILLI_OF_DAY);
		} catch (Exception e) {
			return 0l;
		}
	}
}