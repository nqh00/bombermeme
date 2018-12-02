/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile.item;

import bomberman.Game;
import bomberman.entities.Entity;
import bomberman.entities.character.Bomber;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của vật phẩm BombItem
 */
public class BombItem extends Item {
	
	/**
	 * Hiển thị hình ảnh thực thể BombItem
	 */
	public BombItem(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}

	@Override
	public void setValue() {
		_active = true;
		Game.addBombRate(1);
	}
}
