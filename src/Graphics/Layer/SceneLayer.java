package Graphics.Layer;

import Graphics.Misc.Screen;

public class SceneLayer extends Layer {

	public SceneLayer(Screen screen) {
		super(screen);
	}

	public void render() {
		screen.restDrawCalls();
	}

}
