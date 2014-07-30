package il.ben.wise.listeners;

import il.ben.wise.capture.Capturer;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;


/**
 * Listener used to cancel any selection area, capturing process.
 * @author Ben
 *
 */
public class CancelCapture implements HotKeyListener {

	private Capturer c;
	
	public CancelCapture(Capturer c) {
		this.c = c;
	}

	@Override
	public void onHotKey(HotKey arg0) {
		this.c.cancel();
	}

}
