/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import bomberman.Board;
import bomberman.entities.LayeredEntity;
import bomberman.entities.tile.Brick;
import bomberman.entities.tile.Grass;
import bomberman.entities.tile.Portal;
import bomberman.entities.tile.Wall;
import bomberman.exceptions.LevelException;
import bomberman.graphics.Sprite;


public class FileLevel extends Level {
	
	/**
	 * Khởi tạo tệp cấu hình
	 * @param board
	 * @param path
	 * @throws LevelException
	 */
	public FileLevel(Board board, String path) throws LevelException {
		super(board, path);
	}

	@Override
	public void loadLevel(String path) throws LevelException {
		// TODO: Đọc dữ liệu từ tệp res/levels/Level{level}.txt
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String data = br.readLine();
			StringTokenizer tokens = new StringTokenizer(data);
			
			// TODO: Cập nhật các giá trị đọc được vào _width, _height, _level, _map
			
			_level = Integer.parseInt(tokens.nextToken());
			_height = Integer.parseInt(tokens.nextToken());
			_width = Integer.parseInt(tokens.nextToken());
			
			_map = new char[_height][_width];
			
			for (int i = 0; i < _height; i++) {
				_map[i] = br.readLine().toCharArray();
			}
			br.close();
			
		} catch (IOException e) {
			throw new LevelException("Error loading level", e);
		}
		
	}

	@Override
	public void createEntities() {
		// TODO: Tạo các Entity dựa vào map
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				addLevelEntity(_map[y][x], x, y);
			}
		}
	}
	
	/**
	 * Thêm tọa độ của từng loại thực thể
	 * @param c (loại thực thể)
	 * @param x (hoành độ)
	 * @param y (tung độ)
	 */
	public void addLevelEntity(char c, int x, int y) {
		// TODO: Sau khi tạo xong, gọi _board.addEntity() để thêm thực thể vào game
		int pos = x + y * getWidth();
		
		switch(c) {
			case '#':
				_board.addEntity(pos, new Wall(x, y, Sprite.wall));
				break;
			case '*':
				_board.addEntity(pos, new LayeredEntity(x, y, 
						new Grass(x, y, Sprite.grass),
						new Brick(x, y, Sprite.brick)));
				break;
			case 'x':
				_board.addEntity(pos, new LayeredEntity(x, y, 
						new Grass(x, y, Sprite.grass),
						new Portal(x, y, _board, Sprite.portalIn),
						new Brick(x, y, Sprite.brick)));
				break;
			case ' ':
				_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				break;
			default:
				_board.addEntity(pos, new Grass(x, y, Sprite.grass));
				break;
		}
	}
}
