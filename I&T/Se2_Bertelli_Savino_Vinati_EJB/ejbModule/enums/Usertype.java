package enums;

public enum Usertype {
	PolicyMaker(0), Agronomist(1), Farmer(2);

	private final int value;

	Usertype(int value) {
		this.value = value;
	}

	public static Usertype getUsertypeFromInt(int value) {
		switch (value) {
			case 0:
				return Usertype.PolicyMaker;
			case 1:
				return Usertype.Agronomist;
			case 2:
				return Usertype.Farmer;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
