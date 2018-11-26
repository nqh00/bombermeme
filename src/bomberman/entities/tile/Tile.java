/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.entities.Entity;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;

/**
 * Những thực thể cố định, không di chuyển
 */
public abstract class Tile extends Entity {
	
	/**
	 * Hiển thị hình ảnh của một thực thể cố định
	 */
	public Tile(int x, int y, Sprite sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}
	
	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity(Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Mặc định không cho bất cứ một đối tượng nào đi qua
		return false;
	}
}
