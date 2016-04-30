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
	ArrayList<Sprite> sprites;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;

		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);	
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);

	}

	public void updateGameUI(GameEngine engine){
		big.clearRect(0, 0, width, height);

		big.setColor(Color.YELLOW);
		big.setFont(big.getFont().deriveFont(10.0f));
		big.drawString(String.format("highscore : %08d", engine.getHighScore()), width-130, 15);
		big.setColor(Color.WHITE);
		big.setFont(big.getFont().deriveFont(12.0f));
		big.drawString(String.format("%08d", engine.getScore()), width-80, 30);

		for(Sprite s : sprites){
			s.draw(big);
		}
		drawMessage(engine.getMessage());

		repaint();
	}

	public void drawMessage(String[] message){
		if(message != null){
			big.setColor(Color.YELLOW);		
			for(int i = 0; i<message.length ; i++){
				float fontSize = 25.0f-5.0f*i;
				big.setFont(big.getFont().deriveFont(fontSize));
				big.drawString(message[i], 
				width/2 - message[i].length()*fontSize*0.35f+(25-fontSize)*5, height/3+i*(fontSize+10));
			}
		}
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