package il.ben.wise.capture.impl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.KeyStroke;

import com.tulskiy.keymaster.common.Provider;

import il.ben.wise.CaptureCamera;
import il.ben.wise.capture.Capturer;
import il.ben.wise.capture.impl.gif.GifCaptureBorder;
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
		this.setHotkeys();
	}

	@Override
	public void beginSelection() {
		super.getCamera().startSelection(this);
	}

	@Override
	public void startCapturing(int x, int y, int width, int height) {
		Robot robot = super.getCamera().getRobot();
		BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));
		try {
			ImageIO.write(image, "png", new File("random.png"));
			super.setCaptureResult(new File("random.png"));
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
