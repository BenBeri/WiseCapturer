package il.ben.wise.listeners;

import il.ben.wise.capture.impl.gif.GifCapturer;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

/**
 * When using the {@link GifCapturer} capturer, this listener is used
 * to force-finish the gif capturing.
 * @author Ben
 *
 */
public class FinishGifCapture implements HotKeyListener {

	private GifCapturer capturer;
	
	public FinishGifCapture(GifCapturer g) {
		this.capturer = g;
	}
	
	@Override
	public void onHotKey(HotKey arg0) {
		this.capturer.finishCapture();
	}


}
