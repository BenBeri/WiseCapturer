package il.ben.wise.capture.impl.gif;

import java.awt.Rectangle;
import java.awt.Robot;
import java.util.TimerTask;

public class TimerCaptureProcess extends TimerTask {

	private GifCapturer capturer;
	private Robot robot;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public TimerCaptureProcess(GifCapturer gif, int x, int y, int width, int height) {
		this.capturer = gif;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.robot = this.capturer.getCamera().getRobot();
	}
	
	@Override
	public void run() {
		this.capturer.addFrame(this.robot.createScreenCapture(new Rectangle(this.x,
				this.y,
				this.width,
				this.height)));
	}

	public void stop() {
		super.cancel();
		this.capturer.createGif();
	}
}
