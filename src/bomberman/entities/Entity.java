/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities;

import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;

/**
 * Lớp đại diện cho tất cả thực thể trong game(Bomber, Enemy, Wall, Brick,...)
 */
public abstract class Entity {
	protected double _x, _y;
	protected boolean _removed;
	protected Sprite _sprite;
	
	/**
	 * Phương thức này được gọi liên tục trong Gameloop,
	 * mục đích: xử lý sự kiện, cập nhật trang thái cho thực thể
	 */
	@Override
	public abstract void update();
	
	/**
	 * Phương thức này được gọi liên tục trong Gameloop,
	 * mục đích: cập nhật hình ảnh của thực thể theo trạng thái
	 */
	@Override
	public abstract void render(Screen screen);
	
	/**
	 * Getter: kiểm tra thực thể đã được xóa
	 */
	public boolean isRemoved() {
		return _removed;
	}
	
	/**
	 * Setter: xóa thực thể
	 */
	public void remove() {
		_removed = true;
	}
	
	/**
	 * Getter: hình ảnh mặc định của thực thể
	 */
	public Sprite getSprite() {
		return _sprite;
	}
	
	/**
	 * Setter: hình ảnh mặc định của thực thể
	 */
	public void setSprite(Sprite sprite) {
		_sprite = sprite;
	}
	
	/**
	 * Getter: giá trị của x
	 */
	public double getX() {
		return _x;
	}
	
	/**
	 * Getter: giá trị của y
	 */
	public double getY() {
		return _y;
	}
	
	/**
	 * Getter: khối tại tọa độ x
	 */
	public int getXTile() {
		return Coordinates.pixelToTile(_x + _sprite.getSize() / 2);	//cộng với một nửa khối cho chính xác
	}
	
	/**
	 * Getter: khối tại tọa độ y
	 */
	public int getYTile() {
		return Coordinates.pixelToTile(_y - _sprite.getSize() / 2);	//trừ đi một nửa khối cho chính xác
	}
	
	/**
	 * Phương thức này được gọi để xử lý khi hai thực thể tương tác với nhau
	 * @param e
	 * @return true (đi qua) hoặc false (chặn lại)
	 */
	public abstract boolean collide(Entity e);
}
