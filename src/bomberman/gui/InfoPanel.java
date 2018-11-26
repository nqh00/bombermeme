/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.Game;

/**
 * Swing Panel hiển thị thông tin thời gian, điểm mà người chơi đạt được
 */
public class InfoPanel extends JPanel {
	private JLabel timeLabel;
	private JLabel pointsLabel;
	private JLabel livesLabel;
	private Font font;
	
	public InfoPanel(Game game) {
		setLayout(new GridLayout());
		font = new Font("Bomber Font", Font.PLAIN, 5 * Game.SCALE);

		timeLabel = new JLabel("TIME " + game.getBoard().getTime());
		timeLabel.setFont(font);
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		pointsLabel = new JLabel("SCORE " + game.getBoard().getPoints());
		pointsLabel.setFont(font);
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		livesLabel = new JLabel("LEFT " + game.getBoard().getLives());
		livesLabel.setFont(font);
		livesLabel.setForeground(Color.white);
		livesLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(timeLabel);
		add(pointsLabel);
		add(livesLabel);
		
		setBackground(Color.decode("#b9b9b9"));
		setPreferredSize(new Dimension(0, 70));
	}
	
	/**
	 * Setter: cập nhật thời gian
	 */
	public void setTime(int t) {
		timeLabel.setText("TIME " + t);
	}

	/**
	 * Setter: cập nhật điểm
	 */
	public void setPoints(int t) {
		pointsLabel.setText("SCORE " + t);
	}
	
	/**
	 * Setter: cập nhật mạng
	 */
	public void setLives(int t) {
		livesLabel.setText("LEFT " + t);
	}
}
