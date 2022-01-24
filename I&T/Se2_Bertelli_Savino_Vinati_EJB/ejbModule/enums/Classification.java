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
				return Classification.Extremly_Heavy_Rainfall;
			case 1:
				return Classification.VeryHeavyRainfall;
			case 2:
				return Classification.Heavy_Rainfall;
			case 3:
				return Classification.Moderate_Rainfall;
			case 4:
				return Classification.Light_Rainfall;
			case 5:
				return Classification.Very_Light_Rainfall;
			case 6:
				return Classification.No_Rainfall;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
