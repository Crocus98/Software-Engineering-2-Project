package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
	
	public static Double calculateEntropy(List<Integer> values) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		//Count occurrences of each value
		for (Integer item : values) {
			if (!map.containsKey(item)) {
				map.put(item, 0);
			}
			map.put(item, map.get(item) + 1);
		}

		//Entropy calculation
		Double result = 0.0;
		for (Integer item : map.keySet()) {
			Double frequency = (double) map.get(item) / values.size();
			result -= frequency * (Math.log(frequency) / Math.log(2));
		}
		return result;
	}
	
	public static int compare(double value_this, double value_that) {
		double epsilon = 0.000001d;
		if (Math.abs(value_this - value_that) < epsilon) {
			return 0;
		} else if (value_this > value_that) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public static double round (double numberToRound) {
		BigDecimal temp = new BigDecimal(numberToRound);
		return Double.parseDouble(temp.setScale(2, RoundingMode.HALF_UP).toString());
	}
	
	public static String convertMonthToString(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "";
		}
	}
	
	public static List<Integer> getDataFromMap(Map<Integer, Integer> values, int startingMonth) {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			if(!values.containsKey(startingMonth)) {
				values.put(startingMonth, 0);
			}
			data.add(values.get(startingMonth));
			startingMonth += 1;
			if(startingMonth == 12) {
				startingMonth = 0;
			}
		}
		return data;
	}
	
	@SuppressWarnings("deprecation")
	public static List<String> getMonths(Date lastDate) {
		List<String> months = new ArrayList<>();
		int month = lastDate.getMonth() + 1;
		for (int i = 0; i < 12; i++) {
			months.add(Utility.convertMonthToString(month));
			month = month + 1;
			if (month == 13) {
				month = 1;
			}
		}
		return months;
	}
}
