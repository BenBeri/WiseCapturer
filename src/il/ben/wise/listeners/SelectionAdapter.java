package il.ben.wise.listeners;

import il.ben.wise.CaptureCamera;
import il.ben.wise.SelectionCamera;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionAdapter extends MouseAdapter {

	private CaptureCamera camera;
	private SelectionCamera selector;
	
	public SelectionAdapter(CaptureCamera c, SelectionCamera s) {
		this.camera = c;
		this.selector = s;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		this.selector.setStartPoint(new Point(x, y));
		this.selector.setCoordinates(x, y);
		
		this.selector.setVisible(true);
		
		this.camera.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.selector.fix();
		this.camera.endSelection();
	}
	
	public void updateCamera(SelectionCamera c) {
		this.selector = c;
	}
}
