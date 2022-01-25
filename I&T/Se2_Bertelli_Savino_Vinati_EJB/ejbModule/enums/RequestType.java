package enums;

public enum RequestType {
	HELP(0), SUGGESTION(1);

	private final int value;

	RequestType(int value) {
		this.value = value;
	}

	public static RequestType getRequestTypeFromInt(int value) {
		switch (value) {
			case 0:
				return RequestType.HELP;
			case 1:
				return RequestType.SUGGESTION;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
