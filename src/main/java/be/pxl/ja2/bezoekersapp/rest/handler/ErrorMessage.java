package be.pxl.ja2.bezoekersapp.rest.handler;

public class ErrorMessage {

	private final String message;

	public ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}