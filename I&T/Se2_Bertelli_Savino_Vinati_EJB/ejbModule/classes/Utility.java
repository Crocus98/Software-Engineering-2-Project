package classes;

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
}
