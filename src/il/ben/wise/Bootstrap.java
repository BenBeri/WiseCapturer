package il.ben.wise;

import il.ben.wise.capture.Capturer;
import il.ben.wise.listeners.ScreenCaptureCallback;

import java.awt.AWTException;
import java.io.File;

/**
 * The boostrap class
 * @author Ben
 *
 */
public class Bootstrap {

	/**
	 * If true, all captured images will be saved as history into a folder {@link Bootstrap#folder}
	 */
	private boolean saveToFolder;
	
	/**
	 * If {@link Bootstrap#saveToFolder}, the system will save ALL captured images to this folder
	 */
	private File folder;
	
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
	public Bootstrap(Capturer c) throws AWTException {
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
	public void beginCapture(ScreenCaptureCallback c) {
		this.capturer.setCallback(c);
		this.capturer.beginSelection();
	}
	
	/**
	 * Set save history {@link Bootstrap#saveToFolder}
	 * @param flag
	 */
	public void setSaveHistory(boolean flag) {
		this.saveToFolder = flag;
	}
	
	/**
	 * Sets history folder {@link Bootstrap#folder} by String path
	 * @param path
	 * 				String URL of the folder
	 */
	public void setHistoryFolder(String path) {
		this.folder = new File(path);
	}
	
	/**
	 * Sets history folder {@link Bootstrap#folder} by file instance
	 * @param path
	 * 				File instance
	 */
	public void setHistoryFolder(File file) {
		this.folder = file;
	}
}
