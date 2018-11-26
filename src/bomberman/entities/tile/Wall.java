/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Wall
 */
public class Wall extends Tile {

	/**
	 * Hiển thị hình ảnh của thực thể Wall
	 */
	public Wall(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
}
