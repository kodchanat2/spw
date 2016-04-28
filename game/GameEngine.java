package game;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class GameEngine{
	private GamePanel gp;
	private SpaceShip v;	
	private Enemy enemy;	
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		int x_born = gp.getWidth()/2;
		enemy = new Enemy(x_born);
		gp.sprites.add(enemy);
		
		gp.updateGameUI();	
	}
}
