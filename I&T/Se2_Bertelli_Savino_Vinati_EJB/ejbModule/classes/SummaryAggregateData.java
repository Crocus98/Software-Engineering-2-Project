package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SummaryAggregateData {
	private String areaName;
	private List<Integer> values;

	public SummaryAggregateData(String areaName) {
		this.setAreaName(areaName);
		this.values = new ArrayList<Integer>();
	}

	public SummaryAggregateData(String areaName, List<Integer> values) {
		this.setAreaName(areaName);
		this.setValues(values);
	}

	public String getAreaName() {
		return areaName;
	}

	@SuppressWarnings("deprecation")
	public static List<String> getMonths(Date lastDate) {
		List<String> months = new ArrayList<>();
		int month = lastDate.getMonth() + 1;
		for (int i = 0; i < 12; i++) {
			months.add(SummaryAggregateData.convertMonthToString(month));
			month = month + 1;
			if (month == 13) {
				month = 1;
			}
		}
		return months;
	}

	public static List<Integer> getDataFromMap(Map<Integer, Integer> values, int startingMonth) {
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			if(values.get(startingMonth) == null) {
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

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

}
