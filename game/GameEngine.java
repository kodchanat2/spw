package game;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameEngine implements KeyListener{
	private GamePanel gp;
	private SpaceShip v;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private Timer timer;	

	private long score;
	private int hp;
	private long highScore = 0;
	private String[] messages;
	private double difficulty;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;	

		init();
	}

	public void init(){
		messages = null;
		score = 0;
		difficulty = 0.1;
		hp = 3;
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		gp.sprites = new ArrayList<Sprite>();
		gp.sprites.add(v);

		if(timer != null){
			timer.stop();
		}
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

	private void generateBullet(){
		Bullet b = new Bullet(v.getX(), v.getY());
		bullets.add(b);
		gp.sprites.add(b);
	}

	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}

		updateEnemiesAndBullet();
		
		gp.updateGameUI(this);

		checkIntersects();
		
	}

	public void updateEnemiesAndBullet(){
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
				highScore = Math.max(score,highScore);
			}
		}
		for(Bullet b : bullets)
			b.proceed();
	}
	public void checkIntersects(){
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr) && e.isAlive()){
				hit();
				e.die();
			}

			Iterator<Bullet> b_iter = bullets.iterator();
			while(b_iter.hasNext()){
				Bullet b = b_iter.next();
				br = b.getRectangle();

				if(er.intersects(br) && e.isAlive()){
					b_iter.remove();
					gp.sprites.remove(b);
					e.die();
				}
			}
		}
	}

	public void hit(){
		hp--;
		if(hp <= 0){
			die();
		}
	}
	
	public void die(){
		timer.stop();
		messages = new String[]{
			"GAME OVER!",
			String.format("YOUR SCORE is %08d", score),
			"please 'R' to retry"
		};
		gp.updateGameUI(this);
	}

	public long getScore(){
		return score;
	}
	public long getHighScore(){
		return highScore;
	}

	public int getHP(){
		return hp;
	}

	public String[] getMessages(){
		return messages;
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
				generateBullet();
				break;
			case KeyEvent.VK_R:
				init();
				start();
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
