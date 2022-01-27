package exceptions;

public class PostsRetrievalException extends Exception {
	private static final long serialVersionUID = 1L;

	public PostsRetrievalException(String message) {
		super(message);
	}
}
