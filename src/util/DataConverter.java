package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {
	public static Date Converter (String string) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(string);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public static String Parser (Date date) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").format(date);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
