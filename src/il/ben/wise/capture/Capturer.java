package il.ben.wise.capture;

import il.ben.wise.Bootstrap;
import il.ben.wise.CaptureCamera;
import il.ben.wise.listeners.ScreenCaptureCallback;

import java.awt.AWTException;
import java.io.File;

import com.tulskiy.keymaster.common.Provider;

/**
 * Capturer
 * 
 * Any capturer in the library will implement this class
 * @author Ben
 *
 */
public abstract class Capturer {
	
	/**
	 * Capture camera instance
	 */
	
	private CaptureCamera camera;
	
	/**
	 * Callback instance
	 */
	private ScreenCaptureCallback callback;
	
	/**
	 * Provider instance from JKeyMaster, JNA used for hotkeys
	 */
	private Provider provider;
	
	/**
	 * The captured file
	 */
	private File captured;
	
	public Capturer() throws AWTException {
		this.camera = new CaptureCamera();
		this.provider = Provider.getCurrentProvider(false);
	}
	
	/**
	 * Gets the Capture camera
	 * @return
	 */
	public CaptureCamera getCamera() {
		return this.camera;
	}

	/**
	 * Gets the provider JKeyMaster for hotkeys
	 * @return
	 */
	protected Provider getProvider() {
		return this.provider;
	}
	
	/**
	 * Sets a new callback instance
	 * @param c
	 */
	public void setCallback(ScreenCaptureCallback c) {
		this.callback = c;
	}
	
	/**
	 * Gets the captured file result
	 * @return
	 */
	public File getCapturedResult() {
		return this.captured;
	}
	
	/**
	 * Disables the capture frame
	 */
	public void disableSelectionFrame() {
		this.camera.setVisible(false);
	}
	
	/**
	 * Sets a new captured file result
	 * @param file
	 */
	public void setCaptureResult(File file) {
		this.captured = file;
	}
	
	/**
	 * Finishes capture, sends back a reply to the callback event
	 */
	protected void finish() {
		this.callback.captureEnded(this.captured);
	}
	
	/**
	 * Starts selection
	 */
	public abstract void beginSelection();
	
	/**
	 * Sets up hotkeys
	 */
	public abstract void setHotkeys();
	
	/**
	 * Start capture process
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public abstract void startCapturing(int x, int y, int width, int height);
	
	/**
	 * Cancel capture
	 */
	public abstract void cancel();
}
