/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.Board;
import bomberman.entities.Entity;
import bomberman.entities.character.Bomber;
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

	@Override
	public void update() {
		// TODO: Cập nhật lại trạng thái của Portal khi không còn enemy trên board
		if(_board.detectNoEnemies())
			setSprite(Sprite.portalOut);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý khi Bomber đi vào
		if(e instanceof Bomber) {
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies())
					_board.nextLevel();
			}
		}
		
		return true;
	}
}
