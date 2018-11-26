/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character.enemy.ai;

import bomberman.entities.character.Bomber;
import bomberman.entities.character.enemy.Enemy;

/**
 * Thuật toán tìm đường đi nâng cao
 */
public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	/**
	 * Thuật toán tìm đường đi dựa trên người chơi
	 */
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;

	}

	/**
	 * Tìm đường đi theo hàng dọc
	 */
	protected int calculateColumnDirection() {
		if(_bomber.getXTile() < _e.getXTile())
			return 3;
		else if(_bomber.getXTile() > _e.getXTile())
			return 1;
		
		return -1;
	}

	/**
	 * Tìm đường đi theo hàng ngang
	 */
	protected int calculateRowDirection() {
		if(_bomber.getYTile() < _e.getYTile())
			return 0;
		else if(_bomber.getYTile() > _e.getYTile())
			return 2;
		
		return -1;
	}
	
	@Override
	public int calculateDirection() {
		// TODO: Cài đặt thuật toán tìm đường đi
		
		if(_bomber == null)
			return random.nextInt(4);
		
		int vertical = random.nextInt(2);
		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColumnDirection();
		}
		else {
			int h = calculateColumnDirection();
			
			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}
	}
}
