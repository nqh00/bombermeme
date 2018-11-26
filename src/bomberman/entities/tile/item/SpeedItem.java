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
 * Thể hiện của vật phẩm SpeedItem
 */
public class SpeedItem extends Item {
	
	/**
	 * Hiển thị hình ảnh thực thể SpeedItem
	 */
	public SpeedItem(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}

	@Override
	public void setValue() {
		_active = true;
		Game.addBomberSpeed(0.1);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý khi Bomber ăn vật phẩm SpeedItem
		if(e instanceof Bomber) {
			((Bomber)e).addItem(this);
			remove();
			return true;
		}
		return false;
	}
}