package Graphics.Window;

import java.awt.event.WindowEvent;

public class WindowListener implements java.awt.event.WindowListener {
	
	private Window window;
	
	public WindowListener(Window window) {
		this.window = window;
	}

	public void windowOpened(WindowEvent e) {
		
	}

	public void windowClosing(WindowEvent e) {
		window.closed = true;
	}

	public void windowClosed(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowActivated(WindowEvent e) {
		
	}

	public void windowDeactivated(WindowEvent e) {
		
	}

}
