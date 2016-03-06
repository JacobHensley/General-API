package Graphics.Misc;

import java.awt.Color;

public class GraphicsLine implements Comparable<GraphicsLine> {
	
	public int x1, y1, x2, y2;
	public Color color;

	public GraphicsLine(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	public int compareTo(GraphicsLine line) {
		return 0;
	}
}
