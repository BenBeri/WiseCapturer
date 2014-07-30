package il.ben.wise.capture.exception;

public class NoCaptureFoundException extends WiseCaptureException {

	public NoCaptureFoundException() {
		super("There was no capture file set in the varaible, nullpointer exception");
	}

}
