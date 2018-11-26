/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bomberman.Game;

/**
 * Swing Frame chứa toàn bộ các component
 */
public class Frame extends JFrame {
	private JPanel _containerpanel;
	private GamePanel _gamepanel;
	private InfoPanel _infopanel;
	
	private Game _game;

	public Frame() {
		_containerpanel = new JPanel(new BorderLayout());
		_gamepanel = new GamePanel(this);
		_infopanel = new InfoPanel(_gamepanel.getGame());
		
		_containerpanel.add(_infopanel, BorderLayout.PAGE_START);
		_containerpanel.add(_gamepanel, BorderLayout.PAGE_END);
		
		_game = _gamepanel.getGame();
		
		add(_containerpanel);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
		_game.start();
	}
	
	/**
	 * Setter: cập nhật thời gian
	 */
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	/**
	 * Setter: cập nhật điểm
	 */
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}
	
	/**
	 * Setter: cập nhật mạng
	 */
	public void setLive(int lives) {
		_infopanel.setLives(lives);
	}
}
