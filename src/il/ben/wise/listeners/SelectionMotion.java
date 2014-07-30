package il.ben.wise.listeners;

import il.ben.wise.CaptureCamera;
import il.ben.wise.SelectionCamera;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SelectionMotion implements MouseMotionListener {

	private CaptureCamera camera;
	private SelectionCamera selector;
	
	public SelectionMotion(CaptureCamera c, SelectionCamera s) {
		this.camera = c;
		this.selector = s;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
        Point dragPoint = e.getPoint();
        Point startPoint = this.selector.getStartPoint();
        
        int x = Math.min(startPoint.x, dragPoint.x);
        int y = Math.min(startPoint.y, dragPoint.y);
        int width = Math.max(startPoint.x - dragPoint.x, dragPoint.x - startPoint.x);
        int height = Math.max(startPoint.y - dragPoint.y, dragPoint.y - startPoint.y);
        
		this.selector.setCameraDimension(width, height);
		this.selector.setCoordinates(x, y);
		
		this.camera.repaint(); // important
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
