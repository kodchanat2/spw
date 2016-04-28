package game;

import java.awt.*;

public class Enemy extends Sprite{
	static private int WIDTH = 5;
	static private int HEIGHT = 10;
	static private int Y_BORN = 30;
	
	public Enemy(int x) {
		super(x, Y_BORN, WIDTH, HEIGHT);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}
}