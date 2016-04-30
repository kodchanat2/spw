package game;

import java.awt.*;

public class Bullet extends Sprite{
	static private int Y_TO_DIE;

	private int step = 20;
	
	public Bullet(int x, int y) {
		super(x-2, y, 4, 20);
		Y_TO_DIE = -20;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}

	public void proceed(){
		y -= step;
	}
}
