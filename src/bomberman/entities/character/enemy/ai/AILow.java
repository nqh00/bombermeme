/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character.enemy.ai;

/**
 * Thuật toán tìm đường đi đơn giản
 */
public class AILow extends AI {
	
	@Override
	public int calculateDirection() {
		// TODO: Cài đặt thuật toán tìm đường đi
		return random.nextInt(4);
	}
}
