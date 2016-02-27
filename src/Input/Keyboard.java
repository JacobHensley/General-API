package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeyListener {
	
	private static boolean[] keys = new boolean[65536];
	private static List<Integer> pressedKeys = new ArrayList<Integer>();
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		pressedKeys.remove(new Integer(e.getKeyCode()));
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public static boolean isKeyPressed(int keyCode) {
		return keys[keyCode];
	}
	
	public static boolean isKeyTyped(int keyCode) {
		if (!keys[keyCode]) return false;
		if (pressedKeys.contains(keyCode)) return false;
		
		pressedKeys.add(keyCode);
		return true;
	}
	
}
