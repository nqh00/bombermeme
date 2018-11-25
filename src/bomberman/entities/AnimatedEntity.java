/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities;

/**
 * Thực thể có hiệu ứng hoạt hình
 */
public abstract class AnimatedEntity extends Entity {
	protected int _animate = 0;
	protected final int MAX_ANIMATE = 7500;
	
	/**
	 * Tạo hiệu ứng hoạt hình cho thực thể
	 */
	protected void animate() {
		if(_animate < MAX_ANIMATE)
			_animate++;
		else
			_animate = 0;
	}
}
