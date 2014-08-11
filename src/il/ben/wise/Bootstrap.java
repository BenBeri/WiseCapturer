package il.ben.wise;

import il.ben.wise.capture.Capturer;
import il.ben.wise.capture.impl.ScreenshotCapturer;
import il.ben.wise.listeners.ScreenCaptureCallback;

import java.awt.AWTException;
import java.io.File;

import javax.swing.SwingUtilities;

/**
 * The boostrap class
 * @author Ben
 *
 */
public class Bootstrap {

	
	/**
	 * The capturer instance, which will handle the image capturing.
	 */
	private Capturer capturer;
	
	/**
	 * Bootstrap consturctor, let it bootstrap!
	 * @param c
	 * 				Capturer instance
	 * @throws AWTException
	 */
	public Bootstrap(Capturer c) {
		this.capturer = c;
	}
	
	/**
	 * Starts capturing the screen, sends back a callback event with the
	 * captured file.
	 * 
	 * The system saves a temporary file to send the file.
	 * @param c
	 * 			Callback instance
	 */
	public void beginCapture(final ScreenCaptureCallback c) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				capturer.setCallback(c);
				capturer.beginSelection();
				
			}
		});
	}
	
	public void setAllowSound(boolean flag) {
		this.capturer.setSoundAllowed(flag);
	}
	
	public void setSound(File file) {
		try {
			this.capturer.setSound(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
