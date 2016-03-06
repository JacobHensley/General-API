package Graphics.Misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.PriorityQueue;
import java.util.Queue;

public class Screen {

	private BufferedImage image;
	private int[] pixels;
	private int drawCalls;

	private Queue<GraphicsString> strings = new PriorityQueue<GraphicsString>();
	private Queue<GraphicsLine> lines = new PriorityQueue<GraphicsLine>();
	
	private int width, height;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();	
	}

	
	public void drawRect(int x, int y, int width, int height, int thickness, int color) {	
		for (int yl = 0; yl < 2;yl++) {
			int xo = x + width * yl;
			for (int yy = 0;yy < height;yy++) {
				int yo = y + yy;
				if (xo < 0 || xo >= this.width || yo < 0 || yo >= this.height)
					continue;
				
				pixels[xo + yo * this.width] = color;
			}
		}
		
		for (int xl = 0; xl < 2;xl++) {
			int yo = y + height * xl;
			for (int xx = 0;xx <= width;xx++) {
				if (xx == width && xl == 0)
					continue;
				
				int xo = x + xx;
				if (xo < 0 || xo >= this.width || yo < 0 || yo >= this.height)
					continue;
				
				pixels[xo + yo * this.width] = color;
			}	
		}
	}
	
	
	public void drawRect(Rectangle bounds, int thickness, int color) {
		drawRect(bounds.x, bounds.y, bounds.width, bounds.height, thickness, color);
	}
	
	public void fillRect(int x, int y, int width, int height, int color) {	
		drawCalls++;
		for (int yy = 0; yy < height; yy++) {
			int yo = y + yy;
			if (yo < 0 || yo >= this.height) continue;
			
			for (int xx = 0; xx < width; xx++) {
				int xo = x + xx;
				if (xo < 0 || xo >= this.width) continue;
				
				if (color != 0xFFFF00FF) pixels[xo + yo * this.width] = color;
			}			
		}
		
	}
	
	public void drawTexture(Texture texture, int x, int y) {
		drawCalls++;
		for (int yy = 0; yy < texture.getHeight(); yy++) {
			int yo = y + yy;
			if (yo < 0 || yo >= this.height)
				continue;
			for (int xx = 0; xx < texture.getWidth(); xx++) {
				int xo = x + xx;
				if (xo < 0 || xo >= this.width)
					continue;
				
				int color = texture.getPixels()[xx + yy * texture.getWidth()];
				if (color == 0xFFFF00FF)
					continue;
				
				pixels[xo + yo * this.width] = color;
			}	
		}
	}
	
	public void drawTexture(Texture texture, int x, int y, float angle) {
		drawCalls++;
		double r = (Math.PI / 180) * angle;
		
		for (int yy = 0; yy < texture.getHeight(); yy++) {
			int yo = y + yy;
			if (yo < 0 || yo >= this.height)
				continue;
			for (int xx = 0; xx < texture.getWidth(); xx++) {
				int xo = x + xx;
				if (xo < 0 || xo >= this.width)
					continue;
				int[] rpixels = rotate(texture.getPixels(), texture.getHeight(), texture.getWidth(), r);
				
				int color = rpixels[xx + yy * texture.getWidth()];
				if (color == 0xFFFF00FF)
					continue;
				
				pixels[xo + yo * this.width] = color;
			}	
		}
	}
	
	private int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		double nx_x = rotX(-angle, 1.0, 0.0);
		double nx_y = rotY(-angle, 1.0, 0.0);
		double ny_x = rotX(-angle, 0.0, 1.0);
		double ny_y = rotY(-angle, 0.0, 1.0);
		
		double x0 = rotX(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double y0 = rotY(-angle, -width / 2.0, -height / 2.0) + height / 2.0;
		
		for (int y = 0;y < height;y++) {
			double x1 = x0;
			double y1 = y0; 
			for (int x = 0;x < width;x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				int color;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height) 
					color = 0xFFFF00FF;
				else
					color = pixels[xx + yy * width];
				
				result[x + y * width] = color;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}

		return result;
	}
	
	private double rotX(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return x * cos + y * -sin;
	}
	
	private double rotY(double angle, double x, double y) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return x * sin + y * cos;
	}
	
	public void drawString(String string, int x, int y, int z, Font font, Color color) {
		drawCalls++;
		strings.add(new GraphicsString(string, font, color, x, y, z));
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		drawCalls++;
		lines.add(new GraphicsLine(x1, y1, x2, y2, color));
	}
	
	public void clear(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public void restDrawCalls() {
		drawCalls = 0;
	}
	
	public Queue<GraphicsString> getStringBuffer() {
		return strings;
	}
	
	public Queue<GraphicsLine> getLineBuffer() {
		return lines;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDrawCalls() {
		return drawCalls;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
