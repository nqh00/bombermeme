/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character.enemy.ai;

import java.util.Random;

/**
 * Thuật toán tìm đường đi bao gồm tìm đường đi đơn giản và tìm đường đi nâng cao
 */
public abstract class AI {
	protected Random random = new Random();

	/**
	 * Thuật toán tìm đường đi
	 * @return hướng đi xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
	 */
	public abstract int calculateDirection();
}