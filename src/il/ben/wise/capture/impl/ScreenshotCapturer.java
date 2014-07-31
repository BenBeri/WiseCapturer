package il.ben.wise.capture.impl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.KeyStroke;

import il.ben.wise.capture.Capturer;
import il.ben.wise.listeners.CancelCapture;

/**
 * Screenshot capturer
 * Captures a simple screenshot with selection area, to the PNG format.
 * @author Ben
 *
 */
public class ScreenshotCapturer extends Capturer {

	public ScreenshotCapturer() throws AWTException {
		super();
	}

	@Override
	public void beginSelection() {
		super.init();
		this.setHotkeys();
		super.getCamera().startSelection();
	}

	@Override
	public void startCapturing(int x, int y, int width, int height) {
		Robot robot = super.getCamera().getRobot();
		BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));
		super.disableSelectionFrame();
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ImageIO.write(image, "png", stream);
			super.setCaptureResult(stream);
			super.finish();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setHotkeys() {
		super.getProvider().register(KeyStroke.getKeyStroke("ESCAPE"), new CancelCapture(this));
	}

	@Override
	public void cancel() {
		super.getCamera().cancelSelection();
	}


}
