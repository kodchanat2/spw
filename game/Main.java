package game;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("SPACE WAR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setVisible(true);

		GamePanel gp = new GamePanel();
		frame.add(gp);
	}
}