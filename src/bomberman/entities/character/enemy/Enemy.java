/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character.enemy;

import java.awt.Color;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.character.Bomber;
import bomberman.entities.character.Character;
import bomberman.entities.character.enemy.ai.AI;
import bomberman.entities.character.enemy.ai.AILow;
import bomberman.entities.character.enemy.ai.AIMedium;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.level.Coordinates;

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
	
	protected Sprite _idleSprite, _idleSprite1, _idleSprite2;
	protected Sprite _deadSprite, _deadSprite1, _deadSprite2, _deadSprite3;
	
	protected int _finalAnimation = 18;	//thời gian xử lý hiệu ứng tiêu diệt (<timeAnimate để tránh lặp lại hiệu ứng)
	
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
		if(isAlive()) {
			if(isMoving())
				chooseSprite();
			else
				setSprite(Sprite.movingSprite(_idleSprite, _idleSprite1, _idleSprite2, _animate, 40));
		}
		
		else {
			if(getTimeAfter() > 0) {
				setSprite(_deadSprite);
				_animate = 0;
			} else
				setSprite(Sprite.movingSprite(_deadSprite1, _deadSprite2, _deadSprite3, _animate, 20));
		}
		screen.renderEntity((int)_x, (int)_y - _sprite.getSize(), this);
	}
	
	@Override
	public void calculateMove() {
		// TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
		int xAI = 0, yAI = 0;
		if(_steps <= 0) {
			_direction = _ai.calculateDirection();
			_steps = MAX_STEPS;
		}
		
		if(getDirection() == 0) yAI--;
		if(getDirection() == 1) xAI++;
		if(getDirection() == 2) yAI++;
		if(getDirection() == 3) xAI--;
		
		// TODO: Sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
		// TODO: Sử dụng move() để di chuyển
		// TODO: Cập nhật lại giá trị của _moving khi thay đổi trạng thái di chuyển
		if(canMove(xAI, yAI)) {
			_steps -= 1 + rest;
			move(xAI * _speed, yAI * _speed);
			_moving = true;
		}
		else {
			_steps = 0;
			_moving = false;
		}
	}
	
	@Override
	public boolean canMove(double x, double y) {
		double xr = _x, yr = _y - 16;
		if(getDirection() == 0) {yr += _sprite.getSize() - 1; xr += _sprite.getSize() / 2;} 
		if(getDirection() == 1) {yr += _sprite.getSize() / 2; xr += 1;}
		if(getDirection() == 2) {xr += _sprite.getSize() / 2; yr += 1;}
		if(getDirection() == 3) {xr += _sprite.getSize() - 1; yr += _sprite.getSize() / 2;}
		
		int xx = Coordinates.pixelToTile(xr) + (int)x;
		int yy = Coordinates.pixelToTile(yr) + (int)y;

		Entity a = _board.getEntity(xx, yy, this);	//thực thể của vị trí ta muốn đi tới
		
		return a.collide(this);
	}
	
	@Override
	public void move(double xAI, double yAI) {
		if(!isAlive()) return;
		_x += xAI;
		_y += yAI;
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
		if(!isAlive()) return;
		_alive = false;
		_board.addPoints(_points);
		Message msg = new Message(" +" + _points, getXMessage(), getYMessage(), 2, Color.WHITE, 16);
		_board.addMessage(msg);
	}
	
	
	@Override
	protected void afterKill() {
		// TODO: Xử lý sau khi Enemy bị tiêu diệt
		if(getTimeAfter() > 0)
			_timeAfter--;
		else {
			if(_finalAnimation > 0)
				_finalAnimation--;
			else 
				remove();
		}
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
	 * Setter: sprite của enemy lúc không di chuyển
	 */
	protected void setIdleSprite(Sprite idle, Sprite idle1, Sprite idle2) {
		_idleSprite = idle;
		_idleSprite1 = idle1;
		_idleSprite2 = idle2;
	}
	
	/**
	 * Setter: sprite của enemy lúc không di chuyển
	 */
	protected void setDeadSprite(Sprite dead, Sprite dead1, Sprite dead2, Sprite dead3) {
		_deadSprite = dead;
		_deadSprite1 = dead1;
		_deadSprite2 = dead2;
		_deadSprite3 = dead3;
	}
	
	
	/**
	 * Triển khai thuật toán AILow
	 */
	protected void implementAILow() {
		_ai = new AILow();
		_direction = _ai.calculateDirection();
	}
	
	/**
	 * Triển khai thuật toán AIMedium
	 */
	protected void implementAIMedium(Enemy e) {
		_ai = new AIMedium(_board.getBomber(), e);
		_direction = _ai.calculateDirection();
	}
}
