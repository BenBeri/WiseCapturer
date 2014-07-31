package il.ben.wise.capture;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CapturedImage {

	private ByteArrayInputStream in;
	
	public CapturedImage(ByteArrayInputStream in) {
		this.in = in;
	}

	public BufferedImage getBufferedImage() {
		try {
			return ImageIO.read(this.in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public File getFile(String url, String format) {
		BufferedImage i = this.getBufferedImage();
		File image = new File(url);
		
		try {
			ImageIO.write(i, format, image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
}
