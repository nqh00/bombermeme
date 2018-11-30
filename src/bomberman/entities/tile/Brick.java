/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.tile;

import bomberman.entities.Entity;
import bomberman.entities.bomb.Flame;
import bomberman.entities.character.enemy.Bat;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;

/**
 * Thể hiện của thực thể cố định Brick
 */
public class Brick extends Tile {
	private final int MAX_ANIMATE = 7500;	//số lượng hiệu ứng
	private int _animate = 0;
	private boolean _destroyed;
	private int _timeToDisappear = 50;	//thời gian trước khi Brick biến mất
	private Sprite _belowSprite = Sprite.grass;	//Sprite nền mặc định
	
	/**
	 * Hiển thị hình ảnh của thực thể Brick
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Brick(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		// TODO: Cập nhật trạng thái của Brick
		if(isDestroyed()) {
			if(_animate < MAX_ANIMATE)
				_animate++;
			else
				_animate = 0;
			if(_timeToDisappear > 0)
				_timeToDisappear--;
			else
				remove();
		}
	}
	
	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của Brick theo trạng thái
		int x = Coordinates.tileToPixel(_x);
		int y = Coordinates.tileToPixel(_y);
		
		if(isDestroyed()) {
			setSprite(movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, Sprite.brick_exploded3, Sprite.brick_exploded4, Sprite.brick_exploded5, 56));
			screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
		}
		else
			screen.renderEntity(x, y, this);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý khi va chạm với Flame
		if(e instanceof Flame)
			destroy();
		
		if(e instanceof Bat)
			return true;
		
		return false;
	}
	
	/**
	 * Tạo hình chuyển động của Brick khi bị phá hủy 
	 */
	private Sprite movingSprite(Sprite x0, Sprite x1, Sprite x2, Sprite x3, Sprite x4, Sprite x5, int time) {
		int caculate = _animate % time;
		int diff = time / 5;
		if(caculate < diff) return x0;
		if(caculate < diff * 2) return x1;
		if(caculate < diff * 3) return x2;
		if(caculate < diff * 4) return x3;
		if(caculate < diff * 5) return x4;
		return x5;
	}
	
	/**
	 * Sprite nền
	 */
	public void addBelowSprite(Sprite sprite) {
		_belowSprite = sprite;
	}
	
	/**
	 * Getter: kiểm tra đã bị phá hủy
	 */
	private boolean isDestroyed() {
		return _destroyed;
	}
	
	/**
	 * Setter: phá hủy
	 */
	private void destroy() {
		_destroyed = true;
	}
}
