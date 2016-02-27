package Graphics.Misc;

import java.awt.Color;
import java.awt.Font;

public class GraphicsString implements Comparable<GraphicsString>{

	public String string;
	public Font font;
	public Color color;
	public int x, y;
	private int z;
	
	public GraphicsString(String string, Font font, Color color, int x, int y, int z) {
		this.font = font;
		this.color = color;
		this.string = string;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int compareTo(GraphicsString string) {
		if (z < string.z) return +1;
		else if (z > string.z) return -1;
		return 0;
	}
	
}
