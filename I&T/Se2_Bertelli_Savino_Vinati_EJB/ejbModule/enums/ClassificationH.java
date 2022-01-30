package enums;

public enum ClassificationH {
	Extremly_Arid(0), Very_Arid(1), Slightly_Arid(2), Balanced(3), Slightly_Humid(4), Very_Humid(5), Extremely_Humid(6);

	private final int value;

	ClassificationH(int value) {
		this.value = value;
	}

	public static ClassificationH getClassificationHFromInt(int value) {
		switch (value) {
		case 0:
			return ClassificationH.Extremly_Arid;
		case 1:
			return ClassificationH.Very_Arid;
		case 2:
			return ClassificationH.Slightly_Arid;
		case 3:
			return ClassificationH.Balanced;
		case 4:
			return ClassificationH.Slightly_Humid;
		case 5:
			return ClassificationH.Very_Humid;
		case 6:
			return ClassificationH.Extremely_Humid;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
