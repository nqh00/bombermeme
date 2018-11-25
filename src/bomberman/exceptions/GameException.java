/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.exceptions;

/**
 * Ngoại lệ khi load game
 */
public class GameException extends Exception {
	
	public GameException() {}
	
	public GameException(String str) {
		super(str);
	}
	
	public GameException(String str, Throwable cause) {
		super(str, cause);
	}
	
	public GameException(Throwable cause) {
		super(cause);
	}
}