package game;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
	private int width;
	private int height;
	private BufferedImage bi;
	private Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;

		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);	
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);

	}

	public void updateGameUI(GameEngine engine){
		big.clearRect(0, 0, width, height);

		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", engine.getScore()), width-80, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}

		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}