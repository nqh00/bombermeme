/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.bomb;

import bomberman.Board;
import bomberman.entities.AnimatedEntity;
import bomberman.entities.Entity;
import bomberman.entities.character.Character;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của các phân đoạn thực thể Flame
 */
public class FlameSegment extends AnimatedEntity {
	protected boolean _last;
	protected int _direction;
	protected Board _board;
	
	/**
	 * Hiển thị các phân đoạn Flame khi Flame xuất hiện
	 * @param x (hoành độ phân đoạn Flame)
	 * @param y (tung độ phân đoạn Flame)
	 * @param direction (vị trí của phân đoạn)
	 * @param last (Cho biết phân đoạn này là cuối cùng của Flame hay không,
	 * phân đoạn cuối có sprite khác so với các phân đoạn còn lại)
	 */
	public FlameSegment(int x, int y, int direction, boolean last, Board board) {
		_x = x;
		_y = y;
		_direction = direction;
		_last = last;
		_board = board;	
		chooseSprite();
	}

	@Override
	public void update() {
		animate();
	}

	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của FlameSegment theo trạng thái
		chooseSprite();
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		screen.renderEntity(xt, yt , this);		
	}

	@Override
	public boolean collide(Entity e) {
		if(e instanceof Character)
			((Character)e).kill();

		return false;
	}
	
	protected void chooseSprite() {
		switch(_direction) {
		case 0:
			setSprite(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, Sprite.bomb_exploded3, Sprite.bomb_exploded2, Sprite.bomb_exploded1, Sprite.bomb_exploded, _animate, 56));
			break;
		case 1:
			setSprite(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, Sprite.explosion_vertical3, Sprite.explosion_vertical2, Sprite.explosion_vertical1, Sprite.explosion_vertical, _animate, 56));
			if(_last) 
				setSprite(Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical_top_last3, Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last, _animate, 56));
			break;
		case 2:
			setSprite(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, Sprite.explosion_horizontal3, Sprite.explosion_horizontal2, Sprite.explosion_horizontal1, Sprite.explosion_horizontal, _animate, 56));
			if(_last) 
				setSprite(Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, Sprite.explosion_horizontal_right_last3, Sprite.explosion_horizontal_right_last2, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last, _animate, 56));
			break;
		case 3:
			setSprite(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, Sprite.explosion_vertical3, Sprite.explosion_vertical2, Sprite.explosion_vertical1, Sprite.explosion_vertical, _animate, 56));
			if(_last) 
				setSprite(Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, Sprite.explosion_vertical_down_last3,  Sprite.explosion_vertical_down_last2, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last, _animate, 56));
			break;
		case 4:
			setSprite(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, Sprite.explosion_horizontal3, Sprite.explosion_horizontal2, Sprite.explosion_horizontal1, Sprite.explosion_horizontal, _animate, 56));
			if(_last) 
				setSprite(Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, Sprite.explosion_horizontal_left_last3, Sprite.explosion_horizontal_left_last2, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last, _animate, 56));
			break;
		}
	}
}

