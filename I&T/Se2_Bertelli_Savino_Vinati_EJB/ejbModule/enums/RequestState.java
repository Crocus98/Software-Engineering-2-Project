package enums;

public enum RequestState {
	CLOSED(0), OPEN(1);

	private final int value;

	RequestState(int value) {
		this.value = value;
	}

	public static RequestState getRequestStateFromInt(int value) {
		switch (value) {
		case 0:
			return RequestState.CLOSED;
		case 1:
			return RequestState.OPEN;
		}
		return null;
	}

	public int getValue() {
		return value;
	}

}
