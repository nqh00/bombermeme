package bomberman.entities.character.enemy;

import bomberman.Board;
import bomberman.Game;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Alien
 */
public class Alien extends Enemy {

	/**
	 * Hiển thị hình ảnh thực thể Alien
	 */
	public Alien(int x, int y, Board board) {
		super(x, y, board, Game.getBomberSpeed(), 1000);
		setSprite(Sprite.alien_down);
		setIdleSprite(Sprite.alien_down, Sprite.alien_down1, Sprite.alien_down2);
		setDeadSprite(Sprite.alien_dead, Sprite.alien_dead1, Sprite.alien_dead2, Sprite.alien_dead3);
		implementAIMedium(this);
	}

	@Override
	protected void chooseSprite() {
		switch(getDirection()) {
			case 0:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.alien_up, Sprite.alien_up1, Sprite.alien_up2, _animate, 10));
				break;
			case 1:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.alien_right, Sprite.alien_right1, Sprite.alien_right2, _animate, 10));
				break;
			case 2:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.alien_down, Sprite.alien_down1, Sprite.alien_down2, _animate, 10));
				break;
			case 3:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.alien_left, Sprite.alien_left1, Sprite.alien_left2, _animate, 10));
				break;
		}
	}
}
