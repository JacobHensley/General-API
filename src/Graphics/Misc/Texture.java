package Graphics.Misc;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	public BufferedImage image;
	
	private int width, height, x, y;
	private int[] pixels;

	private SpriteSheet sheet;
	
	private Texture fliped, baseTexture;
	
	public Texture(SpriteSheet sheet, int width, int height, int x, int y) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		load();
	}
	
	public Texture(String path) {
		try {
			image = ImageIO.read(new FileInputStream(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture(int width, int height, int color) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	public Texture(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			this.pixels[i] = pixels[i];
		}
	}
	
	public static Texture rotate(Texture texture, double angle) {
		return new Texture(rotate(texture.pixels, texture.width, texture.height, angle), texture.width, texture.height);
	} 
	
	private static int[] rotate(int[] pixels, int width, int height, double angle) {
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
	
	private static double rotX(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2.0);
		double sin = Math.sin(angle - Math.PI / 2.0);
		return x * cos + y * -sin;
	}
	
	private static double rotY(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2.0);
		double sin = Math.sin(angle - Math.PI / 2.0);
		return x * sin + y * cos;
	}
	
	public void load() {
		for (int y = 0;y < height;y++) {
			for (int x = 0;x < width;x++) {
				pixels[x + y * width] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}
	
	public Texture flip() {
		if (fliped != null)
			return fliped;
		int[] pixels = new int[width * height];
		for (int y = 0;y < height;y++) {
			int yi = y;
			for (int x = 0;x < width;x++) {
				int xi = width - x - 1;
				pixels[x + y * width] = this.pixels[xi + yi * width];
			}
		}
		fliped = new Texture(pixels, width, height);
		return fliped;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getPixel(int x, int y) {
		return pixels[x + y * width];
	}

}
