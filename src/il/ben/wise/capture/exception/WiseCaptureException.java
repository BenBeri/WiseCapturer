package il.ben.wise.capture.exception;

public class WiseCaptureException extends Exception {

	public WiseCaptureException(String message) {
		super("[WiseCapture exception]: " + message);
	}

}
