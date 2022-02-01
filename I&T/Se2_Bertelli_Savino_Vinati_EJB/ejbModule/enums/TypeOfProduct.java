package enums;

import java.util.ArrayList;
import java.util.List;

public enum TypeOfProduct {
	Rice(0), Wheat(1), Sugarcane(2), Groundnut(3), Pulses(4), Vegetable(5), Fruit(6);

	private final int value;

	TypeOfProduct(int value) {
		this.value = value;
	}

	public static TypeOfProduct getTypeOfProductFromInt(int value) {
		switch (value) {
		case 0:
			return TypeOfProduct.Rice;
		case 1:
			return TypeOfProduct.Wheat;
		case 2:
			return TypeOfProduct.Sugarcane;
		case 3:
			return TypeOfProduct.Groundnut;
		case 4:
			return TypeOfProduct.Pulses;
		case 5:
			return TypeOfProduct.Vegetable;
		case 6:
			return TypeOfProduct.Fruit;
		}
		return null;
	}

	public static List<TypeOfProduct> getAllTypesOfProduct() {
		List<TypeOfProduct> values = new ArrayList<>();
		int i = 0;
		while (true) {
			TypeOfProduct temp = TypeOfProduct.getTypeOfProductFromInt(i);
			if (temp == null) {
				break;
			} else {
				values.add(temp);
				i++;
			}
		}
		return values;
	}

	public int getValue() {
		return value;
	}

}
