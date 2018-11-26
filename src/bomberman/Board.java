/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.bomb.FlameSegment;
import bomberman.entities.character.Bomber;
import bomberman.entities.character.Character;
import bomberman.exceptions.LevelException;
import bomberman.graphics.IRender;
import bomberman.graphics.Screen;
import bomberman.input.Keyboard;
import bomberman.level.FileLevel;
import bomberman.level.Level;

/**
 * Quản lý thao tác điều khiển, load level, render các màn hình của game
 */
public class Board implements IRender {
	protected Level _levelLoader;
	public Game _game;
	protected Keyboard _input;
	protected Screen _screen;
	
	public Entity[] _entities;
	public ArrayList<Character> _characters = new ArrayList<Character>();
	public ArrayList<Bomb> _bombs = new ArrayList<Bomb>();
	public ArrayList<Message> _messages = new ArrayList<Message>();
	
	/**
	 * 1: quit, 2: change level, 3: paused
	 */
	public int _screenToShow = -1;	
	
	private int _time = Game.TIME;
	private int _points = Game.POINTS;
	private int _lives = Game.LIVES;
	
	/**
	 * Hiển thị màn chơi
	 */
	public Board(Game game, Keyboard input, Screen screen) {
		_game = game;
		_input = input;
		_screen = screen;
		
		loadLevel(1); 	//chạy level 1
	}
	
	@Override
	public void update() {
		// TODO: Cập nhật trạng thái game
		if(_game.isPaused())
			return;
	
		updateEntities();
		updateCharacters();
		updateBombs();
		updateMessages();
		detectEndGame();
		
		for (int i = 0; i < _characters.size(); i++) {
			Character a = _characters.get(i);
			if(a.isRemoved()) _characters.remove(i);
		}
	}
	
	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh game
		if(_game.isPaused()) return;
		
		//chỉ render những gì màn hình hiển thị
		int x0 = Screen.xOffset >> 4; //tile precision, -> left X
		int x1 = (Screen.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
		int y0 = Screen.yOffset >> 4;
		int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILES_SIZE; //render one tile plus to fix black margins
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				_entities[x + y * _levelLoader.getWidth()].render(screen);
			}
		}
		
		renderBombs(screen);
		renderCharacter(screen);
	}
	
	/**
	 * Hiển thị loại màn hình
	 */
	public void drawScreen(Graphics g) {
		switch (_screenToShow) {
			case 1:
				_screen.drawEndGame(g, _points);
				break;
			case 2:
				_screen.drawChangeLevel(g, _levelLoader.getLevel());
				break;
			case 3:
				_screen.drawPaused(g);
				break;
		}
	}
	
	/**
	 * Nạp level từ tệp cấu hình
	 */
	public void loadLevel(int level) {
		_time = Game.TIME;
		_screenToShow = 2;
		_game.resetScreenDelay();
		_game.pause();
		_characters.clear();
		_bombs.clear();
		_messages.clear();
		
		try {
			_levelLoader = new FileLevel(this, "res/levels/Level" + level + ".txt");
			_entities = new Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];
			_levelLoader.createEntities();
		} catch (LevelException e) {
			endGame();
		}
	}
	
	/**
	 * Nạp level tiếp theo
	 */
	public void nextLevel() {
		loadLevel(_levelLoader.getLevel() + 1);
	}
	
	/**
	 * Hồi sinh tại level đang chơi
	 */
	public void respawn() {
		loadLevel(_levelLoader.getLevel());
	}
	
	/**
	 * New game
	 */
	public void newGame() {
		resetStat();
		loadLevel(1);
	}
	
	@SuppressWarnings("static-access")
	private void resetStat() {
		_points = Game.POINTS;
		_lives = Game.LIVES;
		Bomber._items.clear();
		
		_game.bomberSpeed = 1;
		_game.bombRadius = 1;
		_game.bombRate = 1;	
	}
	
	/**
	 * Pause game
	 */
	public void pauseGame() {

	}
	
	/**
	 * End game
	 */
	public void endGame() {
		_screenToShow = 1;
		_game.resetScreenDelay();
		_game.pause();
	}
	
	/*
	|--------------------------------------------------------------------------
	| KIỂM TRA
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Kiểm tra thời gian còn lại
	 */
	protected void detectEndGame() {
		if(_time <= 0)
			endGame();
	}
	
	/**
	 * Kiểm tra đã tiêu diệt hết Enemy 
	 */
	public boolean detectNoEnemies() {
		int total = 0;
		for (int i = 0; i < _characters.size(); i++) {
			if(_characters.get(i) instanceof Bomber == false)
				total++;
		}
		
		return total == 0;
	}
	
	/*
	|--------------------------------------------------------------------------
	|UPDATE
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Cập nhật trạng thái tất cả các entity 
	 */
	protected void updateEntities() {
		if(_game.isPaused()) return;
		for (int i = 0; i < _entities.length; i++) {
			_entities[i].update();
		}
	}
	
	/**
	 * Cập nhật trạng thái tất cả các characters
	 */
	protected void updateCharacters() {
		if(_game.isPaused()) return;
		Iterator<Character> itr = _characters.iterator();
		
		while(itr.hasNext() && !_game.isPaused())
			itr.next().update();
	}
	
	/**
	 * Cập nhật trạng thái tất cả các bombs 
	 */
	protected void updateBombs() {
		if(_game.isPaused()) return;
		Iterator<Bomb> itr = _bombs.iterator();
		
		while(itr.hasNext())
			itr.next().update();
	}
	
	/**
	 * Cập nhật trạng thái tất cả các messages 
	 */
	protected void updateMessages() {
		if(_game.isPaused()) return;
		Message m;
		int left;
		for (int i = 0; i < _messages.size(); i++) {
			m = _messages.get(i);
			left = m.getDuration();
			
			if(left > 0) 
				m.setDuration(--left);
			else
				_messages.remove(i);
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| RENDER
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Render tất cả các thực thể 
	 */
	protected void renderEntities(Screen screen) {
		for (int i = 0; i < _entities.length; i++) {
			_entities[i].render(screen);
		}
	}
	
	/**
	 * Render tất cả các nhân vật 
	 */
	protected void renderCharacter(Screen screen) {
		Iterator<Character> itr = _characters.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}
	
	/**
	 * Render tất cả các bombs 
	 */
	protected void renderBombs(Screen screen) {
		Iterator<Bomb> itr = _bombs.iterator();
		
		while(itr.hasNext())
			itr.next().render(screen);
	}
	
	/**
	 * Render tất cả các messages 
	 */
	public void renderMessages(Graphics g) {
		Message m;
		for (int i = 0; i < _messages.size(); i++) {
			m = _messages.get(i);
			
			g.setFont(new Font("Bomber Font", Font.PLAIN, m.getSize()));
			g.setColor(m.getColor());
			g.drawString(m.getMessage(), (int)m.getX() - Screen.xOffset  * Game.SCALE, (int)m.getY() - Screen.yOffset * Game.SCALE);
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| ADD
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Thêm các thực thể
	 */
	public void addEntity(int pos, Entity e) {
		_entities[pos] = e;
	}
	
	/**
	 * Thêm các characters
	 */
	public void addCharacter(Character e) {
		_characters.add(e);
	}
	
	/**
	 * Thêm các bomb
	 */
	public void addBomb(Bomb e) {
		_bombs.add(e);
	}
	
	/**
	 * Thêm các message
	 */
	public void addMessage(Message e) {
		_messages.add(e);
	}
	
	/*
	|--------------------------------------------------------------------------
	| GETTER CHO THỰC THỂ
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Getter: thực thể
	 */
	public Entity getEntity(double x, double y, Character m) {
		Entity res = null;
		
		res = getFlameSegmentAt((int)x, (int)y);
		if(res != null) return res;
		
		res = getBombAt(x, y);
		if(res != null) return res;
		
		res = getCharacterAtExcluding((int)x, (int)y, m);
		if(res != null) return res;
		
		res = getEntityAt((int)x, (int)y);
		
		return res;	
	}
	
	/**
	 * Getter: tọa độ của thực thể 
	 */
	public Entity getEntityAt(double x, double y) {
		return _entities[(int)x + (int)y * _levelLoader.getWidth()];
	}
	
	/**
	 * Getter: bombs
	 */
	public ArrayList<Bomb> getBombs(){
		return _bombs;
	}
	
	/**
	 * Getter: tọa độ bombs
	 */
	public Bomb getBombAt(double x, double y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.getX() == (int)x && b.getY() == (int)y)
				return b;
		}
		
		return null;
	}
	
	/**
	 * Getter: tọa độ character
	 */
	public Character getCharacterAt(double x, double y) {
		Iterator<Character> itr = _characters.iterator();
		
		Character cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur.getXTile() == x && cur.getYTile() == y)
				return cur;
		}
		
		return null;
	}
	
	/**
	 * Getter: bomber(người chơi)
	 */
	public Bomber getBomber() {
		Iterator<Character> itr = _characters.iterator();
		
		Character cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur instanceof Bomber)
				return (Bomber) cur;
		}
		
		return null;
	}
	
	/**
	 * Getter: nhiều character tại cùng vị trí
	 */
	public Character getCharacterAtExcluding(int x, int y, Character a) {
		Iterator<Character> itr = _characters.iterator();
		
		Character cur;
		while(itr.hasNext()) {
			cur = itr.next();
			if(cur == a)
				continue;
			if(cur.getXTile() == x && cur.getYTile() == y)
				return cur;
		}
		
		return null;
	}
	
	/**
	 * Getter: tọa độ flame segment
	 */
	public FlameSegment getFlameSegmentAt(int x, int y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			
			FlameSegment e = b.flameSegmentAt(x, y);
			if(e != null) {
				return e;
			}
		}
		
		return null;
	}

	/*
	|--------------------------------------------------------------------------
	| GETTER & SETTER
	|--------------------------------------------------------------------------
	 */
	
	/**
	 * Getter: dữ liệu nhập từ bàn phím
	 */
	public Keyboard getInput() {
		return _input;
	}

	/**
	 * Getter: thông tin màn chơi
	 */
	public Level getLevel() {
		return _levelLoader;
	}

	/**
	 * Getter: Gameloop
	 */
	public Game getGame() {
		return _game;
	}

	/**
	 * Getter: hiển thị màn hình
	 */
	public int getShow() {
		return _screenToShow;
	}
	
	/**
	 * Setter: hiển thị màn hình
	 */
	public void setShow(int i) {
		_screenToShow = i;
	}

	/**
	 * Getter: thời gian
	 */
	public int getTime() {
		return _time;
	}
	

	/**
	 * Getter: điểm người chơi
	 */
	public int getPoints() {
		return _points;
	}
	
	/**
	 * Setter: điểm người chơi
	 */
	public void addPoints(int points) {
		this._points += points;
	}
	
	/**
	 * Getter: mạng người chơi
	 */
	public int getLives() {
		return _lives;
	}
	
	/**
	 * Setter: mạng người chơi
	 */
	public void addLives(int lives) {
		this._lives += lives;
	}
	
	/**
	 * Getter: chiều dài của màn chơi
	 */
	public int getWidth() {
		return _levelLoader.getWidth();
	}

	/**
	 * Getter: chiều cao của màn chơi
	 */
	public int getHeight() {
		return _levelLoader.getHeight();
	}
	
	/**
	 * Getter: thời gian trò chơi khi tạm dừng
	 */
	public int subtractTime() {
		if(_game.isPaused())
			return this._time;
		else
			return this._time--;
	}
}

