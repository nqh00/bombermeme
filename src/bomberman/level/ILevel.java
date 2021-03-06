/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.level;

import bomberman.exceptions.LevelException;

public interface ILevel {
	
	public void loadLevel(String path) throws LevelException;
}
