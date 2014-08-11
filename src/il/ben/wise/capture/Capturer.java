package il.ben.wise.capture;

import il.ben.wise.Bootstrap;
import il.ben.wise.CaptureCamera;
import il.ben.wise.listeners.ScreenCaptureCallback;

import java.awt.AWTException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
	 * Should the system play a sound on capture?
	 */
	private boolean allowSound = false;
	
	/**
	 * Sound MP3 file
	 */
	private File sound;
	
	/**
	 * The captured file data
	 */
	private ByteArrayInputStream captured;
	
	public Capturer() {
		try {
			this.camera = new CaptureCamera(this);
			System.err.println("[WiseCapturer] Initialized capturer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public ByteArrayInputStream getCapturedResult() {
		return this.captured;
	}
	
	/**
	 * Disables the capture frame
	 */
	public void disableSelectionFrame() {
		System.out.println("called");
		//this.camera.setVisible(false);
	}
	
	/**
	 * Sets a new captured file result
	 * @param file
	 */
	public void setCaptureResult(ByteArrayOutputStream file) {
		this.captured = new ByteArrayInputStream(file.toByteArray());
	}
	
	/**
	 * Finishes capture, sends back a reply to the callback event
	 */
	protected void finish() {
		this.provider.stop();
		this.callback.captureEnded(new CapturedImage(this.captured));
	}
	
	protected void init() {
		this.provider = Provider.getCurrentProvider(false);
		
	}
	
	public void setSound(File file) throws Exception {
		this.sound = file;
	}
	
	public boolean soundAllowed() {
		return this.allowSound;
	}
	
	public void setSoundAllowed(boolean flag) {
		this.allowSound = flag;
	}
	
	public File getSound() {
		return this.sound;
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
