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
import bomberman.sound.Sound;

/**
 * Thể hiện của thực thể Portal
 */
public class Portal extends Tile {
	protected Board _board;
	private Sound portalIn = new Sound("res/sounds/portalin.wav");
	private Sound portalOut = new Sound("res/sounds/portalout.wav");
	private Sound portalTravel = new Sound("res/sounds/portaltravel.wav");
	
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
		if(_board.detectNoEnemies()) {
			portalOut.play();
			setSprite(Sprite.portalOut);
		}
		else
			portalIn.play();
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý khi Bomber đi vào
		if(e instanceof Bomber) {
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies()) {
					portalTravel.play();
					_board.nextLevel();
				}
			}
		}
		
		return true;
	}
}
