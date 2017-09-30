package dccode.lunchinator3000.models;

public class ErrorMessage {

	private String errorMessage;
	
	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	// Getters and Setters
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
