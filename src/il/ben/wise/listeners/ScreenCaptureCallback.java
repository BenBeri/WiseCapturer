package il.ben.wise.listeners;

import il.ben.wise.capture.CapturedImage;

/**
 * This listener is used to let the application that uses this library 
 * know that the capture is finished,
 * @author Ben
 *
 */
public interface ScreenCaptureCallback {
	public void captureEnded(CapturedImage captured);
}
