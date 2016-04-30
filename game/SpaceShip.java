package game;

import java.awt.*;

public class SpaceShip extends Sprite{
	
	int step = 8;
	private int screenWidth;

	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > screenWidth - width)
			x = screenWidth - width;
	}

	public void setScreenWidth(int w){
		screenWidth = w;
	}

	public int getX(){
		return x + width/2;
	}
	public int getY(){
		return y;
	}
}
