package il.ben.wise;

import il.ben.wise.capture.Capturer;
import il.ben.wise.listeners.SelectionAdapter;
import il.ben.wise.listeners.SelectionMotion;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Capture Camera
 * @author Ben
 *
 */
public class CaptureCamera extends JFrame {

	private static final long serialVersionUID = 1L;

	private Toolkit toolkit;
	private Dimension screen;
	private Robot robot;
	
	/**
	 * Selection panel, for capturing an area
	 */
	private SelectionCamera selector;
	
	/**
	 * Current Capturer instance
	 */
	private Capturer c;
	
	public CaptureCamera(Capturer c) throws AWTException {
		this.c = c;
		this.toolkit = Toolkit.getDefaultToolkit();
		this.screen = this.toolkit.getScreenSize();
		this.robot = new Robot();
		this.selector = new SelectionCamera();
		
		super.setSize(this.screen);
		super.setUndecorated(true);
		super.setBackground(new Color(255, 255, 255, 1));
		
		// Listeners for area selection
		super.addMouseListener(new SelectionAdapter(this, this.selector));
		super.addMouseMotionListener(new SelectionMotion(this, this.selector));
			
		super.add(this.selector);
		
	}

	/**
	 * Starts area selection event
	 * @param c Capturer instance
	 */
	public void startSelection() {
		super.setVisible(true);
	}
	
	/**
	 * Gets Robot instance
	 * @return
	 */
	public Robot getRobot() {
		return this.robot;
	}
	
	/**
	 * Cancels the capture selection area
	 */
	public void cancelSelection() {
		this.selector.clear();
		this.setVisible(false);
	}
	
	/**
	 * When the user finishes selecting the area, A.K.A releases the mouse click,
	 * this method will end the selection process and pass the input to the next
	 * step.
	 */
	public void endSelection() {
		this.setVisible(false);
		this.c.startCapturing(this.selector.getCameraX(), 
				this.selector.getCameraY(),
				this.selector.getCameraWidth(), 
				this.selector.getCameraHeight());
		this.cancelSelection();
	}
}
