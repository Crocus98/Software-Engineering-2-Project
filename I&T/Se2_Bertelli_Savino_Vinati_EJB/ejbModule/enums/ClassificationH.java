package enums;

public enum ClassificationH {
	Extremly_Arid(0), Very_Arid(1), Balanced(2), Very_Humid(3), Extremely_Humid(4);

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
				return ClassificationH.Balanced;
			case 3:
				return ClassificationH.Very_Humid;
			case 4:
				return ClassificationH.Extremely_Humid;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
