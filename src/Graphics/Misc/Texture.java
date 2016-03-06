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
	
	private Texture fliped;
	
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
