package bomberman.entities.character.enemy;

import bomberman.Board;
import bomberman.Game;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Bat
 */
public class Bat extends Enemy {
	
	/**
	 * Hiển thị hình ảnh thực thể Bat
	 * @param x
	 * @param y
	 * @param board
	 */
	public Bat(int x, int y, Board board) {
		super(x, y, board, Game.getBomberSpeed() / 3, 800);
		setSprite(Sprite.bat_down1);
		setIdleSprite(Sprite.bat_down, Sprite.bat_down1, Sprite.bat_down2);
		setDeadSprite(Sprite.bat_dead, Sprite.bat_dead1, Sprite.bat_dead2, Sprite.bat_dead3);
		implementAIMedium(this);
	}
	
	@Override
	protected void chooseSprite() {
		switch(getDirection()) {
			case 0:
				if(isMoving())
					setSprite(Sprite.movingSprite(40, _animate, Sprite.bat_up, Sprite.bat_up1, Sprite.bat_up2));
				break;
			case 1:
				if(isMoving())
					setSprite(Sprite.movingSprite(40, _animate, Sprite.bat_right1, Sprite.bat_right2, Sprite.bat_right1, Sprite.bat_right, Sprite.bat_right1));
				break;
			case 2:
				if(isMoving())
					setSprite(Sprite.movingSprite(40, _animate, Sprite.bat_down, Sprite.bat_down1, Sprite.bat_down2));
				break;
			case 3:
				if(isMoving())
					setSprite(Sprite.movingSprite(40, _animate, Sprite.bat_left1, Sprite.bat_left2, Sprite.bat_left1, Sprite.bat_left, Sprite.bat_left1));
				break;
		}		
	}
}