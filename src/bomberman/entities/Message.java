/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities;

import java.awt.Color;

import bomberman.graphics.Screen;

/**
 * Hiển thị thông điệp
 */
public class Message extends Entity {
	protected String _message;
	protected int _duration;
	protected Color _color;
	protected int _size;
	
	@Override
	public void update() {}

	@Override
	public void render(Screen screen) {}

	@Override
	public boolean collide(Entity e) {return true;}
	
	/**
	 * Hiển thị message khi tiêu diệt được Enemy, ví dụ (+100)
	 * @param message (thông điệp)
	 * @param x
	 * @param y
	 * @param duration (đơn vị giây)
	 * @param color (màu chữ)
	 * @param size (kích cỡ chữ)
	 */
	public Message(String message, double x, double y, int duration, Color color, int size) {
		_x = x;
		_y = y;
		_message = message;
		_duration = duration * 60;
		_color = color;
		_size = size;
	}
	
	/**
	 * Getter: thời gian hiện message
	 */
	public int getDuration() {
		return _duration;
	}

	/**
	 * Setter: thời gian hiện message
	 */
	public void setDuration(int _duration) {
		this._duration = _duration;
	}

	/**
	 * Getter: nội dung message
	 */
	public String getMessage() {
		return _message;
	}

	/**
	 * Getter: màu chữ message
	 */
	public Color getColor() {
		return _color;
	}

	/**
	 * Getter: kích thước message
	 */
	public int getSize() {
		return _size;
	}
}