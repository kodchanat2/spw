package game;

import javax.swing.JFrame;

public class Main {
	static int WIDTH = 400;
	static int HEIGHT = 600;
	public static void main(String[] args){
		JFrame frame = new JFrame("SPACE WAR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);

		SpaceShip v = new SpaceShip(WIDTH/2-10, HEIGHT*8/10, 20, 20);
		GamePanel gp = new GamePanel(WIDTH, HEIGHT);
		GameEngine engine = new GameEngine(gp, v);
		frame.add(gp);
		frame.setVisible(true);

	}
}