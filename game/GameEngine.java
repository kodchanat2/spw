package game;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GameEngine{
	private GamePanel gp;
	private SpaceShip v;
	private Enemy enemy;
	private Timer timer;	

	private long score;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;	
		score = 0;
		
		gp.sprites.add(v);

		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
	}

	public void start(){
		timer.start();
	}

	private void generateEnemy(){
		int x_born = gp.getWidth()/2;
		enemy = new Enemy(x_born);
		gp.sprites.add(enemy);
	}

	private void process(){
		generateEnemy();	
		score++;
		
		gp.updateGameUI(this);
	}
	

	public long getScore(){
		return score;
	}
}
