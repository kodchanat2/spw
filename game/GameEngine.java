package game;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameEngine{
	private GamePanel gp;
	private SpaceShip v;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private Timer timer;	

	private long score;
	private double difficulty = 0.1;
	
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
		int x_born = (int)(Math.random()*(gp.getWidth()-15))+5;
		Enemy enemy = new Enemy(x_born, gp.getHeight());
		enemies.add(enemy);
		gp.sprites.add(enemy);
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}

		gp.updateGameUI(this);
	}
	

	public long getScore(){
		return score;
	}
}
