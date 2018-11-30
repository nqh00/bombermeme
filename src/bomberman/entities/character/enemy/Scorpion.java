package bomberman.entities.character.enemy;

import bomberman.Board;
import bomberman.Game;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Scorpion
 */
public class Scorpion extends Enemy {

	/**
	 * Hiển thị hình ảnh của Scorpion
	 */
	public Scorpion(int x, int y, Board board) {
		super(x, y, board, Game.getBomberSpeed() / 3, 500);
		setSprite(Sprite.scorpion_down);
		setIdleSprite(Sprite.scorpion_down, Sprite.scorpion_down1, Sprite.scorpion_down2);
		setDeadSprite(Sprite.scorpion_dead, Sprite.scorpion_dead1, Sprite.scorpion_dead2, Sprite.scorpion_dead3);
		implementAIMedium(this);
	}
	
	@Override
	protected void chooseSprite() {
		switch(getDirection()) {
			case 0:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.scorpion_up, Sprite.scorpion_up1, Sprite.scorpion_up2, _animate, 20));
				break;
			case 1:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.scorpion_right, Sprite.scorpion_right1, Sprite.scorpion_right2, _animate, 20));
				break;
			case 2:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.scorpion_down, Sprite.scorpion_down1, Sprite.scorpion_down2, _animate, 20));
				break;
			case 3:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.scorpion_left, Sprite.scorpion_left1, Sprite.scorpion_left2, _animate, 20));
				break;
		}
	}
}
