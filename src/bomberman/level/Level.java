/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.level;

import bomberman.Board;
import bomberman.exceptions.LevelException;

/**
 * Load và lưu trữ thông tin bản đồ các màn chơi
 */
public abstract class Level implements ILevel {
	protected int _level, _height, _width;
	protected Board _board;

	/**
	 * Ma trận chứa thông tin bản đồ
	 * Mỗi phần tử lưu giá trị kí tự đọc được từ ma trận bản đồ trong tệp cấu hình
	 */
	protected char[][] _map;
	
	/**
	 * Khởi tạo màn chơi
	 * @param board
	 * @param path
	 * @throws LevelException
	 */
	public Level(Board board, String path) throws LevelException{
		_board = board;
		loadLevel(path);
	}
	
	@Override
	public abstract void loadLevel(String path) throws LevelException;
	
	/**
	 * Phương thức tạo các thực thể
	 */
	public abstract void createEntities();
	
	/**
	 * Getter cho số thứ tự màn chơi
	 */
	public int getLevel() {
		return _level;
	}

	/**
	 * Getter cho chiều cao của màn chơi
	 */
	public int getHeight() {
		return _height;
	}

	/**
	 * Getter cho chiều dài của màn chơi
	 */
	public int getWidth() {
		return _width;
	}
}
