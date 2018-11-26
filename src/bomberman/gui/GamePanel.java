/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import bomberman.Game;
import bomberman.exceptions.GameException;

/**
 * Swing Panel chứa cảnh game
 */
public class GamePanel extends JPanel {
	private Game _game;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		try {
			_game = new Game(frame);
			add(_game);
			_game.setVisible(true);
		} catch (GameException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		setVisible(true);
		setFocusable(true);
	}
	
	public Game getGame() {
		return _game;
	}
}
