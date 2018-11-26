/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile.item;

import bomberman.entities.tile.Tile;
import bomberman.graphics.Sprite;

/** 
 * Bao gồm các vật phẩm: BombItem, FlameItem và SpeedItem
 */
public abstract class Item extends Tile {
	protected int _duration = -1;	//-1 là luôn có hiệu lực
	protected boolean _active = false;
	protected int _level;
	
	/**
	 * Hiển thị hình ảnh thực thể Item cố định
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Item(int x, int y, int level, Sprite sprite) {
		super(x, y, sprite);
		_level = level;
	}
	
	/**
	 * Setter: giá trị của Item
	 */
	public abstract void setValue();
	
	public void removeLive() {
		if(_duration > 0) 
			_duration--;
		if(_duration == 0)
			_active = false;
	}

	/**
	 * Getter: thời gian hiệu lực
	 */
	public int getDuration() {
		return _duration;
	}

	/**
	 * Getter: kiểm tra hiệu lực
	 */
	public boolean isActive() {
		return _active;
	}

	/**
	 * Getter: thông tin màn chơi
	 */
	public int getLevel() {
		return _level;
	}
}
