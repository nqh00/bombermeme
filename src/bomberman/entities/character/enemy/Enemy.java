/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character.enemy;

import java.awt.Color;
import java.io.File;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.character.Bomber;
import bomberman.entities.character.Character;
import bomberman.entities.character.enemy.ai.AI;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;
import bomberman.sound.Sound;

/**
 * Bao gồm tất cả Enemy
 */
public abstract class Enemy extends Character {
	protected int _points;
	protected double _speed;
	protected AI _ai;
	
	protected final double MAX_STEPS;
	protected final double rest;
	protected double _steps;
	
	protected Sprite _deadSprite, _deadSprite1, _deadSprite2, _deadSprite3;
	protected int _finalAnimation = 28;	//thời gian xử lý hiệu ứng tiêu diệt (<timeAnimate để tránh lặp lại hiệu ứng)
	
	/**
	 * Hiển thị hình ảnh của thực thể Enemy trên board
	 * @param x
	 * @param y
	 * @param board
	 * @param speed (tốc độ mặc định)
	 * @param points (điểm mặc định)
	 */
	public Enemy(int x, int y, Board board, double speed, int points) {
		super(x, y, board);
		_points = points;
		_speed = speed;
		MAX_STEPS = Game.TILES_SIZE / _speed;
		rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
		_steps = MAX_STEPS;
	}
	
	@Override
	public void update() {
		// TODO: Cập nhật trạng thái của Enemy, xử lý hiệu ứng hoạt hình
		animate();
		if(isAlive())
			calculateMove();
		else {
			afterKill();
			return;
		}
	}
	
	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của Enemy theo trạng thái còn sống và bị tiêu diệt
		if(isAlive())
			chooseSprite();
		else {
			if(getTimeAfter() > 0) {
				setSprite(_deadSprite);
				_animate = 0;
			} else
				setSprite(Sprite.movingSprite(_deadSprite1, _deadSprite2, _deadSprite3, _animate, 30));
		}
		screen.renderEntity((int)_x, (int)_y - _sprite.getSize(), this);
	}
	
	@Override
	public void calculateMove() {
		// TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction

	}
	
	@Override
	public boolean canMove(double x, double y) {
		return true;
	}
	
	@Override
	public void move(double xAI, double yAI) {
	
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý va chạm với Bomber
		if(e instanceof Bomber) 
			((Bomber)e).kill();
		
		return true;
	}
	
	@Override
	public void kill() {

	}
	
	
	@Override
	protected void afterKill() {
		// TODO: Xử lý sau khi Enemy bị tiêu diệt
	}
	
	/**
	 * Tạo hình cho Enemy
	 */
	protected abstract void chooseSprite();
	
	/**
	 * Getter: kiểm tra enemy có đang di chuyển
	 */
	protected boolean isMoving() {
		return _moving;
	}
	
	/**
	 * Setter: sprite của enemy lúc bị tiêu diệt
	 */
	protected void setDeadSprite(Sprite dead, Sprite dead1, Sprite dead2, Sprite dead3) {
		_deadSprite = dead;
		_deadSprite1 = dead1;
		_deadSprite2 = dead2;
		_deadSprite3 = dead3;
	}
}
