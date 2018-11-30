/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.bomb;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.AnimatedEntity;
import bomberman.entities.Entity;
import bomberman.entities.character.Bomber;
import bomberman.entities.character.Character;
import bomberman.entities.character.enemy.Enemy;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;
import bomberman.sound.Sound;

/**
 * Thể hiện của thực thể bomb
 */
public class Bomb extends AnimatedEntity {
	protected Board _board;
	protected Flame[] _flames;
	private double _timeToExplode = 120; //2 giây thì nổ
	private int _timeAfter = 50;	//thời gian lửa cháy
	private boolean _exploded = false;	//bom chưa nổ
	private boolean _allowedToPassThru = true;	//cho phép Bomber đi qua khi quả bomb được đặt
		
	private Sound bombExplode = new Sound("res/sounds/bombexplode.wav");

	/**
	 * Hiển thị quả bomb do Bomber(người chơi) đặt
	 * @param x
	 * @param y
	 * @param board
	 */
	public Bomb(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
		setSprite(Sprite.bomb);
	}
	
	@Override
	public void update() {
		// TODO: Cập nhật trạng thái quả bomb, xử lý quả bomb nổ
		if(getTimeToExplode() > 0) 
			_timeToExplode--;
		else if(getTimeToExplode() == 0){
			if(!isExplode())
				explode();
			else {
				updateExplosion();
				if(getTimeAfter() > 0)
					_timeAfter--;
				else
					remove();
			}
		}
		
		animate();		
	}
	
	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của quả bomb và vụ nổ theo trạng thái
		if(!isExplode())
			setSprite(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 20));
		else {
			bombExplode.play();
			setSprite(Sprite.voidSprite);
			renderExplosion(screen);
		}
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);		
	}
	
	@Override
	public boolean collide(Entity e) {
        // TODO: Xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
		if(e instanceof Bomber) {
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());
			
			// TODO: Kiểm tra xem bomber đã ra khỏi Tile quả bom chưa, nếu rồi thì không cho đi qua lại nữa
			if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28))	//diffX, diffY tự test 
				_allowedToPassThru = false;
			
			return _allowedToPassThru;
		}
		
        // TODO: Xử lý va chạm với Flame của Bomb khác
		if(e instanceof Flame)
			_timeToExplode = 0;
		
		return false;
	}
	
	/**
	 * Cập nhật trạng thái vụ nổ
	 */
	protected void updateExplosion() {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].update();
		}
	}
	
	/**
	 * Cập nhật hình ảnh vụ nổ
	 */
	protected void renderExplosion(Screen screen) {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].render(screen);
		}
	}
	
	/**
	 * Xử lý bom nổ
	 */
	protected void explode() {
		_exploded = true;
		_allowedToPassThru = true;
		
		// TODO: Xử lý khi 01 Character đứng tại vị trí đặt Bomb
		Character a = _board.getCharacterAt(_x, _y);
		
		if(a instanceof Character)
			a.kill();
		
		// TODO: Tạo các Flame
		_flames = new Flame[5];
		for (int i = 0; i < _flames.length; i++) {
			_flames[i] = new Flame((int)_x, (int)_y, i, Game.getBombRadius(), _board);
		}
	}
	
	/**
	 * Vị trí của các flame
	 */
	public FlameSegment flameSegmentAt(int x, int y) {
		if(!_exploded)
			return null;
		
		for (int i = 0; i < _flames.length; i++) {
			if(_flames[i] == null)
				return null;
			FlameSegment e = _flames[i].flameSegmentAt(x, y);
			if(e != null)
				return e;
		}
		return null;
	}
	
	/**
	 * Getter: thời gian quả bom được nấu
	 */
	private double getTimeToExplode() {
		return _timeToExplode;
	}
	
	/**
	 * Getter: thời gian lửa cháy
	 */
	public int getTimeAfter() {
		return _timeAfter;
	}
	
	/**
	 * Getter: kiểm tra bomb đã nổ
	 */
	private boolean isExplode() {
		return _exploded;
	}
}
