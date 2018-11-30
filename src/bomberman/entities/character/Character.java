/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.AnimatedEntity;
import bomberman.graphics.Screen;

/**
 * Bao gồm Bomber và Enemy
 */
public abstract class Character extends AnimatedEntity {
	protected Board _board;
	protected int _direction = -1;
	protected boolean _alive = true;
	protected boolean _moving = false;
	protected int _timeAfter = 10;
	

	/**
	 * Hiển thị hình ảnh của một thực thể Character chuyển động trên Board
	 */
	public Character(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
	}
	
	/**
	 * Cập nhật trạng thái cho nhân vật
	 */
	@Override
	public abstract void update();
	
	/**
	 * Cập nhật hình ảnh cho nhân vật theo trạng thái
	 */
	@Override
	public abstract void render(Screen screen);
	
	/**
	 * Tính toán hướng đi
	 */
	protected abstract void calculateMove();
	
	/**
	 * Kiểm tra xem đối tượng có di chuyển tới vị trí đã tính toán hay không
	 */
	protected abstract boolean canMove(double x, double y);
	
	/**
	 * Di chuyển nhân vật
	 */
	protected abstract void move(double xIn, double yIn);
	
	/**
	 * Được gọi khi đối tượng bị tiêu diệt
	 */
	public abstract void kill();

	/**
	 * Xử lý hiệu ứng bị tiêu diệt
	 */
	protected abstract void afterKill();
	
	/**
	 * Getter: kiểm tra nhân vật còn sống
	 */
	protected boolean isAlive() {
		return _alive;
	}
	
	/**
	 * Getter: kiểm tra nhân vật đang chuyển động
	 */
	protected boolean isMoving() {
		return _moving;
	}
	
	/**
	 * Getter: hướng đi nhân vật
	 */
	protected int getDirection() {
		return _direction;
	}
	
	/**
	 * Getter: thời gian trước khi Character chạy hiệu ứng tiêu diệt (lúc chạy sprite dead)
	 */
	protected int getTimeAfter() {
		return _timeAfter;
	}

	/**
	 * Setter: thời gian trước khi Character chạy hiệu ứng tiêu diệt (lúc chạy sprite dead)
	 */
	protected void setTimeAfter(int _timeAfter) {
		this._timeAfter = _timeAfter;
	}
	
	/**
	 * Getter: thông báo tại tọa độ x
	 */
	protected double getXMessage() {
		return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
	}
	
	/**
	 * Getter: thông báo tại tọa độ y
	 */
	protected double getYMessage() {
		return (_y * Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
	}
}
