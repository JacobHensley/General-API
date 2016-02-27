package Graphics.Layer;

import Graphics.Misc.Screen;

public abstract class Layer {
	
	protected Screen screen;
	
	public Layer(Screen screen) {
		this.screen = screen;
	}
	
	public abstract void render();
	
}
