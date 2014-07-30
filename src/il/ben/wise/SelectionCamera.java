package il.ben.wise;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * Selection Camera
 * @author Ben
 *
 */
public class SelectionCamera extends JPanel {

	private static final long serialVersionUID = -2286129793282990036L;

	/**
	 * X-asis coordinate of the selection area
	 */
	private int x;
	
	/**
	 * Y-asis coordinate of the selection area
	 */
	private int y;
	
	/**
	 * Width of the area
	 */
	private int width;
	
	/**
	 * Height of the area
	 */
	private int height;
	
	/**
	 * This is the location of where the user started selection the
	 * area from, A.K.A his first click.
	 */
	private Point startPoint;
	
	public SelectionCamera() {
		super.setBackground(new Color(0, 0, 0, 0));
		super.setVisible(false);
	}
	
	/**
	 * Gets X coordinate of the area
	 * @return X
	 */
	public int getCameraX() {
		return this.x;
	}
	
	/**
	 * Gets Y coordinate of the area
	 * @return Y
	 */
	public int getCameraY() {
		return this.y;
	}
	
	/**
	 * Sets new coordinates
	 * @param x	X coordinate
	 * @param y Y coordinate
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the area width
	 * @return width
	 */
	public int getCameraWidth() {
		return this.width;
	}
	
	/**
	 * Gets the area height
	 * @return height
	 */
	public int getCameraHeight() {
		return this.height;
	}
	
	/**
	 * If the user just clicks the mouse without selection an area, accidently or
	 * in purpose, we will resize it to 1,1 to prevent AWT exceptions.
	 */
	public void fix() {
		if (this.width < 1)
			this.width = 1;
		if (this.height < 1)
			this.height = 1;
	}
	
	/**
	 * Sets new dimension to the area
	 * @param width The new Width
	 * @param height The new Height
	 */
	public void setCameraDimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets a new startPoint {@link SelectionCamera#startPoint}
	 * @param point	New point
	 */
	public void setStartPoint(Point point) {
		this.startPoint = point;
	}
	
	/**
	 * Gets startPoint
	 * @return
	 */
	public Point getStartPoint() {
		return this.startPoint;
	}
	
	/**
	 * Clears the selection area, to be ready for the next selection.
	 */
	public void clear() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.startPoint = null;
		this.setVisible(false);
	}
	
	/**
	 * Drawing the selected area
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0.5f));
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
}
