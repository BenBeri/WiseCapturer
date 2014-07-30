package il.ben.wise.capture.impl.gif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class GifCaptureBorder extends JFrame {

	private final int tickness = 3;
	private Border border;
	private LoadingBar bar;
	
	public GifCaptureBorder(int x, int y, int width, int height) {
		super.setLayout(new BorderLayout());
		
		x -= tickness;
		y -= tickness;
		width += tickness * 2;
		height += tickness * 2;
		
		super.setSize(width, height);
		super.setLocation(x, y);
		
		super.setUndecorated(true);
		super.setBackground(new Color(255, 255, 255, 1));
		
		this.border = new Border(width, height);
		
		super.add(this.border, BorderLayout.CENTER);
		super.setVisible(true);
	}
	
	public void initializeLoadingBar() {
		this.border.disable();
		this.bar = new LoadingBar();
		
		super.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 200;
		gbc.ipady = 15;
		
		super.add(this.bar, gbc);
		super.setSize(300, 300);
		
		super.repaint();
	}
	
	public void updateProgress(int progress) {
		this.bar.setValue(progress);
	}
	
	private class LoadingBar extends JProgressBar {
		
		public LoadingBar() {
			super(0, 100);
			super.setValue(0);
			super.setForeground(Color.GREEN);
		}
	}
	
	private class Border extends JPanel {
		
		public Border(int width, int height) {
			super.setBackground(new Color(255, 255, 255, 1));
			super.setSize(width, height);
			super.setLocation(0, 0);
			super.setBorder(BorderFactory.createLineBorder(Color.RED, tickness));
		}
		
		@Override
		public void disable() {
			super.setVisible(false);
		}
	}
}
