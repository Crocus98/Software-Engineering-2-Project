package exceptions;

public class ForecastRetrievalException extends Exception {
	private static final long serialVersionUID = 1L;

	public ForecastRetrievalException(String message) {
		super(message);
	}
}
