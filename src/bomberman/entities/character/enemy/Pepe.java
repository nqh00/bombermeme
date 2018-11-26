package bomberman.entities.character.enemy;

import bomberman.Board;
import bomberman.Game;
import bomberman.graphics.Sprite;

/**
 * Thể hiện của thực thể Pepe
 */
public class Pepe extends Enemy {
	
	/**
	 * Hiển thị hình ảnh của Pepe
	 */
	public Pepe(int x, int y, Board board) {
		super(x, y, board, Game.getBomberSpeed() / 5, 100);
		setSprite(Sprite.pepe_down);
		setDeadSprite(Sprite.pepe_dead, Sprite.pepe_dead1, Sprite.pepe_dead2, Sprite.pepe_dead3);
		implementAILow();
	}

	@Override
	protected void chooseSprite() {
		switch(getDirection()) {
			case 0:
				if(isMoving())	
					setSprite(Sprite.movingSprite(Sprite.pepe_up, Sprite.pepe_up1, Sprite.pepe_up2, _animate, 20));
				break;
			case 1:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.pepe_right, Sprite.pepe_right1, Sprite.pepe_right2, _animate, 20));
				break;
			case 2:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.pepe_down, Sprite.pepe_down1, Sprite.pepe_down2, _animate, 20));
				break;
			case 3:
				if(isMoving())
					setSprite(Sprite.movingSprite(Sprite.pepe_left, Sprite.pepe_left1, Sprite.pepe_left2, _animate, 20));
				break;
			default:
				setSprite(Sprite.pepe_down);
				break;
		}
	}
}
