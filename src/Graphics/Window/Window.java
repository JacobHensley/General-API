package Graphics.Window;

import java.awt.Canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import Graphics.Misc.GraphicsLine;
import Graphics.Misc.GraphicsString;
import Graphics.Misc.Screen;

public class Window extends Canvas {
	private static final long serialVersionUID = 1L;

	private String title;
	private int width, height;

	private JFrame frame;
	private Graphics g;
	private BufferStrategy bs;
	private Screen screen;
	
	protected boolean closed = false;

	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		setPreferredSize(new Dimension(width, height));

		frame = new JFrame(title);
		frame.setResizable(true);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener(this));
		frame.setLocationRelativeTo(null);
		
		init();
	}

	private void init() {
		createBufferStrategy(3);
		bs = getBufferStrategy();
		g = bs.getDrawGraphics();
			
		screen = new Screen(width, height);
	}
	
	public void update() {
		g.drawImage(screen.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		while (!screen.getStringBuffer().isEmpty()) {
			GraphicsString string = screen.getStringBuffer().poll();
			g.setColor(string.color);
			g.setFont(string.font);
			g.drawString(string.string, string.x, string.y);
		}
		
		while(!screen.getLineBuffer().isEmpty()) {
			GraphicsLine line = screen.getLineBuffer().poll();
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
		
		g.dispose();
		bs.show();
	}
	

	public void showWindow() {
		frame.setVisible(true);
		requestFocus();
	}

	public boolean closed() {
		return closed;
	}

	public void clear(int color) {
		g = bs.getDrawGraphics();
		screen.clear(color);
	}
	
	public Screen getScreen() {
		return screen;
	}

	public Graphics getWindowGraphics() {
		return g;
	}

}
