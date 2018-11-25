/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.exceptions;

/**
 * Ngoại lệ khi load level
 */
public class LevelException extends GameException {
	
	public LevelException() {}
	
	public LevelException(String str) {
		super(str);
	}
	
	public LevelException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public LevelException(Throwable cause) {
		super(cause);
	}
}
