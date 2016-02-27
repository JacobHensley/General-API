package Input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int x = -1, y = -1, mouseButton = -1;
	private static List<Integer> clickedButtons = new ArrayList<Integer>();
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
		if (clickedButtons.contains(e.getButton())) clickedButtons.remove(new Integer(e.getButton()));
	}
	
	public static boolean mouseClicked(int button) {
		if (button != mouseButton) return false;
		if (clickedButtons.contains(button)) return false;
			
		clickedButtons.add(button);
		return true;
	}
	
	public static Rectangle getBoundBox() {
		return new Rectangle(x, y, 1, 1);
	}
	
	public static int getMouseX() {
		return x;
	}
	
	public static int getMouseY() {
		return y;
	}
	
	public static int getMouseButton() {
		return mouseButton;
	}

}