package classes;

import java.util.ArrayList;
import java.util.List;

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
