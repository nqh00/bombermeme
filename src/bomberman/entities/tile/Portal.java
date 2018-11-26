/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.Board;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Portal
 */
public class Portal extends Tile {
	protected Board _board;
	
	/**
	 * Hiển thị hình ảnh của thực thể Portal
	 */
	public Portal(int x, int y, Board board, Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}

}
