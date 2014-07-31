package il.ben.wise.capture.impl.gif;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.KeyStroke;

import il.ben.util.AnimatedGifEncoder;
import il.ben.wise.capture.Capturer;
import il.ben.wise.listeners.CancelCapture;
import il.ben.wise.listeners.FinishGifCapture;

/**
 * GifCapturer
 * Creates a new gif animated image, by the selection area using the free GIF4J library
 * @author Ben
 *
 */
public class GifCapturer extends Capturer {

	private List<BufferedImage> frames = new ArrayList<BufferedImage>();
	private TimerCaptureProcess process;
	
	private GifCaptureBorder border;
	
	public GifCapturer() throws AWTException {
		super();
	}

	public void addFrame(BufferedImage i) {
		this.frames.add(i);
	}
	
	@Override
	public void beginSelection() {
		super.init();
		this.setHotkeys();
		super.getCamera().startSelection();
	}

	@Override
	public void startCapturing(final int x, final int y, final int width, final int height) {
		this.border = new GifCaptureBorder(x, y, width, height);
		this.process = new TimerCaptureProcess(this, x, y, width, height);
		Timer timer = new Timer();
		timer.schedule(this.process, 0, 600);
	}

	/**
	 * Creates a new animated image with the GIF format using GIF4J library
	 */
	public void createGif() {
		super.disableSelectionFrame();

		AnimatedGifEncoder gif = new AnimatedGifEncoder();
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		gif.start(stream);
		gif.setDelay(1000);
		
		this.border.updateProgress(10);
		
		for(int i = 0; i < this.frames.size(); i++) {
			gif.addFrame(this.frames.get(i));
		}
		
		this.border.updateProgress(50);
		
		gif.finish();
		
		super.setCaptureResult(stream);
		
		this.border.updateProgress(100);
		
		super.finish();
		this.border.setVisible(false);
		this.border = null;
	}
	
	/**
	 * Finished capturing, now save the gif, this may take a while.
	 */
	public void finishCapture() {
		this.process.cancel();
		this.border.initializeLoadingBar();
		this.createGif();
	}

	@Override
	public void setHotkeys() {
		System.out.println("hey!");
		super.getProvider().register(KeyStroke.getKeyStroke("ESCAPE"), new CancelCapture(this));
		super.getProvider().register(KeyStroke.getKeyStroke("ENTER"), new FinishGifCapture(this));
	}

	@Override
	public void cancel() {
		super.getCamera().cancelSelection();
		this.border.setVisible(false);
		this.border = null;
	}
	

}
