/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.level;

import bomberman.Game;

/**
 * Lấy tọa độ của thực thể cố định trên bản đồ
 */
public class Coordinates {
	
	/**
	 * Chuyển đổi tọa độ pixel thành các khối
	 */
	public static int pixelToTile(double i) {
		return (int)(i / Game.TILES_SIZE);
	}
	
	/**
	 * Chuyển đổi tọa độ các khối thành pixel
	 */
	public static int tileToPixel(int i) {
		return i * Game.TILES_SIZE;
	}
	
	/**
	 * Chuyển đổi tọa các khối thành pixel
	 */
	public static int tileToPixel(double i) {
		return (int)(i * Game.TILES_SIZE);
	}
}
