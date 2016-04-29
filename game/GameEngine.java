package game;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameEngine implements KeyListener{
	private GamePanel gp;
	private SpaceShip v;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private Timer timer;	

	private long score;
	private String message;
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

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
		message = "GAME OVER!";
		gp.updateGameUI(this);
	}

	public long getScore(){
		return score;
	}

	public String getMessage(){
		return message;
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				v.move(-1);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				v.move(1);
				break;
			case KeyEvent.VK_SPACE:
				// System.out.println(e);
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
