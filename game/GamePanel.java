package game;

import javax.swing.*;

public class GamePanel extends JPanel {

	public GamePanel() {
		JLabel text = new JLabel("Hello world", JLabel.CENTER);
		text.setFont(text.getFont().deriveFont(40.0f));
		add(text);
	}
}