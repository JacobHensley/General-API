package Graphics.Misc;

public class AnimSprite {

	private Texture[] textures;
	private int frame, speed, time;
	
	public AnimSprite(SpriteSheet sheet, int hCount, int vCount, int speed) {
		int w = sheet.SIZE / hCount;
		int h = sheet.SIZE / vCount;
		textures = new Texture[hCount * vCount];
		for(int y = 0;y < vCount;y++) {
			for (int x = 0;x < hCount;x++) {
				textures[x + y * hCount] = new Texture(sheet, w, h, x, y);
			}
		}
		this.speed = speed;
	}
	
	public void update() {
		if (time++ % speed == 0) frame++;
		if (frame > textures.length - 1) frame = 0;
	}
	
	public Texture getTexture() {
		return textures[frame];
	}
	
	
	
}
