package classes;

import java.util.List;

//Class used for digesting data for users
public class ProductionAggregateData {
	private final String name = "Production (kg)";
	
	private List<Integer> values;
	
	public ProductionAggregateData(List<Integer> values) {
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
}
