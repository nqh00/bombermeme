/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.bomb;

import bomberman.Board;
import bomberman.entities.Entity;
import bomberman.entities.character.Character;
import bomberman.graphics.Screen;

/**
 * Thể hiện của thực thể Flame
 */
public class Flame extends Entity {
	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments;
	
	
	/**
	 * Hiển thị flame từ quả bomb
	 * @param x (hoành độ của Flame)
	 * @param y (tung độ của Flame)
	 * @param direction (vị trí của Flame)
	 * @param radius (độ dài cực đại của Flame)
	 */
	public Flame(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		_flameSegments = new FlameSegment[permitedRadius()];
		createFlame();
	}

	@Override
	public void update() {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].update();
		}	
	}

	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của thực thể Flame và Segment theo trạng thái
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		return false;
	}
	
	/**
	 * Tạo các phân đoạn của Flame, mỗi phân đoạn ứng với một đơn vị độ dài
	 */
	private void createFlame() {
		int x = (int)_x;
		int y = (int)_y;
		
		// TODO: Mặc định độ dài của Flame là 1, chính là tâm của vụ nổ, vị trí đặt bomb
		_flameSegments[0] = new FlameSegment(x, y, 0, false, _board);

		// TODO: Biến last dùng để đánh dấu cho phân đoạn cuối cùng
		boolean _last;
		
		// TODO: Tính toán độ dài Flame, tương ứng với số lượng phân đoạn và tạo các phân đoạn
		for (int i = 1; i < _flameSegments.length; i++) {
			_last = i == _flameSegments.length - 1 ? true : false;
			
			switch(_direction) {
				case 1:	y--; break;
				case 2:	x++; break;
				case 3:	y++; break;
				case 4:	x--; break;
			}
			_flameSegments[i] = new FlameSegment(x, y, _direction, _last, _board);
		}

	}
	
	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
	 */
	private int permitedRadius() {
		//TODO: Tính toán độ dài của Flame
		int radius;
		int x = (int)_x;
		int y = (int)_y;
		for (radius = 1; radius <= _radius; radius++) {
			if(_direction == 1) y--;
			if(_direction == 2) x++;
			if(_direction == 3) y++;
			if(_direction == 4) x--;
			
			Entity a = _board.getEntity(x, y, null);
			
			// TODO: Xử lý khi gặp 01 Thực thể là Character
			if(a instanceof Character)
				((Character)a).kill();
			
			if(a instanceof Bomb)
				radius++;
				
			if(a.collide(this) == false)
				break;	//nếu gặp thực thể không thể đi qua, độ dài sẽ bị cắt ngắn
		}
		
		return radius;
	}
	
	/**
	 * Vị trí của các phân đoạn Flame
	 */
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}
}