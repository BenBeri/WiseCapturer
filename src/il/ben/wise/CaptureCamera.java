package il.ben.wise;

import il.ben.wise.capture.Capturer;
import il.ben.wise.listeners.SelectionAdapter;
import il.ben.wise.listeners.SelectionMotion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.Timer;

import javazoom.jl.player.Player;

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
	
	private SelectionAdapter selectionMosueAdapter;
	private SelectionMotion selectionMouseMotion;
	
	public CaptureCamera(Capturer c) throws Exception {
		this.c = c;
		
		this.toolkit = Toolkit.getDefaultToolkit();
		this.screen = this.toolkit.getScreenSize();
		this.robot = new Robot();
		
		this.selector = new SelectionCamera();
		this.selectionMosueAdapter = new SelectionAdapter(this, this.selector);
		this.selectionMouseMotion = new SelectionMotion(this, this.selector);
		
		super.addMouseListener(this.selectionMosueAdapter);
		super.addMouseMotionListener(this.selectionMouseMotion);
		
		super.add(this.selector);
		super.setSize(this.screen);
		super.setUndecorated(true);
		super.setBackground(new Color(255, 255, 255, 1));
	}
	
	
	/**
	 * Starts area selection event
	 * @param c Capturer instance
	 */
	public void startSelection() {

		super.setLocation(-10000, 0);
		super.setVisible(true);
		
		new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setLocation(0, 0);
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}).start();
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
	
	private void playSound() {
		if (this.c.soundAllowed()) {
			try {
				FileInputStream finput = new FileInputStream(c.getSound());
				BufferedInputStream bis = new BufferedInputStream(finput);
				Player player = new Player(bis);
				player.play();
				player.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * When the user finishes selecting the area, A.K.A releases the mouse click,
	 * this method will end the selection process and pass the input to the next
	 * step.
	 */
	public void endSelection() {
		super.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		int x = this.selector.getCameraX();
		int y = this.selector.getCameraY();
		int w = this.selector.getCameraWidth();
		int h = this.selector.getCameraHeight();

		//super.repaint();
		super.setVisible(false);
		this.playSound();
		this.c.startCapturing(x, y, w, h);

		
		this.cancelSelection();
	}
}
