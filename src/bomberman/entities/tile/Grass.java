/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.entities.Entity;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Grass
 */
public class Grass extends Tile {
	
	/**
	 * Hiển thị hình ảnh của thực thể Grass
	 */
	public Grass(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Cho mọi đối tượng khác đi qua
		return true;
	}
}
