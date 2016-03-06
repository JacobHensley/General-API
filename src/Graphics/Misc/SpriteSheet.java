package Graphics.Misc;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public final int SIZE;
	private String path;
	public final int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			image.getRGB(0, 0, SIZE, SIZE, pixels, 0, SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}