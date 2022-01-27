package enums;

public enum Classification {
	Extremly_Heavy_Rainfall(0), VeryHeavyRainfall(1), Heavy_Rainfall(2), Moderate_Rainfall(3),
	Light_Rainfall(4), Very_Light_Rainfall(5), No_Rainfall(6);

	private final int value;
	

	Classification(int value) {
		this.value = value;
	}

	public static Classification getClassificationFromInt(int value) {
		switch (value) {
			case 0:
				return Classification.Extremly_Heavy_Rainfall; // x > 204 (Value in mm)
			case 1:
				return Classification.VeryHeavyRainfall; // 115 < x <= 204
			case 2:
				return Classification.Heavy_Rainfall; // 64 < x <= 115
			case 3:
				return Classification.Moderate_Rainfall; // 15 < x <= 64
			case 4:
				return Classification.Light_Rainfall; // 2 < x <= 15
			case 5:
				return Classification.Very_Light_Rainfall; // 0 < x <= 2
			case 6:
				return Classification.No_Rainfall; // x = 0
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
