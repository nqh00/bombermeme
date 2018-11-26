/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import bomberman.exceptions.GameException;
import bomberman.graphics.Screen;
import bomberman.gui.Frame;
import bomberman.input.Keyboard;

/**
 * Tạo Gameloop, lưu trữ một vài tham số cấu hình toàn cục,
 * gọi phương thức render(), update() cho tất cả các thực thể
 */
public class Game extends Canvas {
	public static final int TILES_SIZE = 16, WIDTH = TILES_SIZE * (31 / 2), HEIGHT = 13 * TILES_SIZE;

	public static int SCALE = 3;
	
	public static final double VERSION = 1.0;
	
	public static final String TITLE = "BOMBERMAN " + VERSION;
	
	//Thông số mặc định
	private static final int BOMBRATE = 1;
	private static final int BOMBRADIUS = 1;
	private static final double BOMBERSPEED = 1.0;
	
	public static final int TIME = 200;
	public static final int POINTS = 0;
	public static final int LIVES = 3;
	
	protected static int SCREENDELAY = 3;

	//Thông số có thể thay đổi khi người chơi ăn vật phẩm
	protected static int bombRate = BOMBRATE;
	protected static int bombRadius = BOMBRADIUS;
	protected static double bomberSpeed = BOMBERSPEED;
	
	protected int _screenDelay = SCREENDELAY;
	
	private Keyboard _input;
	private boolean _running = false;
	private boolean _paused = true;
	
	private Board _board;
	private Screen screen;
	private Frame _frame;
	
	//image này sẽ được sử dụng để render game, mỗi render là một ảnh được tính toán trước và lưu vào đây
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	/**
	 * Hiển thị trò chơi bằng Java Swing
	 */
	public Game(Frame frame) throws GameException {
		_frame = frame;
		_frame.setTitle(TITLE);
		
		screen = new Screen(WIDTH, HEIGHT);
		_input = new Keyboard();
		
		_board = new Board(this, _input, screen);
		addKeyListener(_input);
	}
	
	
	private void renderGame() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		_board.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen._pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		_board.renderMessages(g);
		
		g.dispose();
		bs.show();
	}
	
	private void renderScreen() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		Graphics g = bs.getDrawGraphics();
		
		_board.drawScreen(g);

		g.dispose();
		bs.show();
	}

	private void update() {
		_input.update();
		_board.update();
	}
	
	/**
	 * Thuật toán Gameloop
	 */
	public void start() {
		_running = true;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double amountOfFrame = 60.0;
		final double ns = 1000000000 / amountOfFrame;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(_running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			if(_paused) {
				if(_screenDelay <= 0) {
					_board.setShow(-1);
					_paused = false;
				}
				renderScreen();
			}
			else
				renderGame();
			
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				_frame.setTime(_board.subtractTime());
				_frame.setPoints(_board.getPoints());
				_frame.setLive(_board.getLives());
				timer += 1000;
				_frame.setTitle(TITLE + " | " + updates + " FPS");
				updates = 0;
				frames = 0;
				
				if(_board.getShow() == 2)
					_screenDelay--;
			}
		}
	}
	
	/**
	 * Getter: tốc độ của Bomber
	 */
	public static double getBomberSpeed() {
		return bomberSpeed;
	}
	
	/**
	 * Getter: số bomb mà Bomber có
	 */
	public static int getBombRate() {
		return bombRate;
	}
	
	/**
	 * Getter: bán kính bomb nổ 
	 */
	public static int getBombRadius() {
		return bombRadius;
	}
	
	/**
	 * Setter: tốc độ của Bomber
	 */
	public static void addBomberSpeed(double i) {
		bomberSpeed += i;
	}
	
	/**
	 * Setter: số bomb mà Bomber có
	 */
	public static void addBombRadius(int i) {
		bombRadius += i;
	}
	
	/**
	 * Setter: bán kính bomb nổ
	 */
	public static void addBombRate(int i) {
		bombRate += i;
	}

	/**
	 * Setter: độ trễ màn hình
	 */
	public void resetScreenDelay() {
		_screenDelay = SCREENDELAY;
	}

	/**
	 * Getter: màn chơi
	 */
	public Board getBoard() {
		return _board;
	}

	/**
	 * Getter: kiểm tra có đang tạm dừng trò chơi
	 */
	public boolean isPaused() {
		return _paused;
	}
	
	/**
	 * Setter: tạm dừng trò chơi
	 */
	public void pause() {
		_paused = true;
	}
	
	/**
	 * Getter: kiểm tra trò chơi có đang chạy
	 */
	public boolean isRunning() {
		return _running;
	}
	
	/**
	 * Setter: chạy trò chơi
	 */
	public void run() {
		_running = true;
		_paused = false;
	}
	
	/**
	 * Setter: dừng trò chơi
	 */
	public void stop() {
		_running = false;
	}
}
