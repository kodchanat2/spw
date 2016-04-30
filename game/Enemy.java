package game;

import java.awt.*;

public class Enemy extends Sprite{
	static private int Y_BORN = 0;
	static private int Y_TO_DIE;
	static private int Y_TO_FADE;

	private int step = 12;
	private boolean alive = true;
	
	public Enemy(int x, int screenHeight) {
		super(x, Y_BORN, 10, 20);
		Y_TO_DIE = screenHeight-height;
		Y_TO_FADE = Y_TO_DIE*85/100;
	}

	@Override
	public void draw(Graphics2D g) {
		if(y >= Y_TO_FADE){			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		//set alphaComposite back
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}

	public boolean isAlive(){
		return alive;
	}

	public void die(){
		alive = false;
	}
}