/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.Entity;
import bomberman.entities.character.Bomber;

/**
 * Xử lý render cho tất cả Entity và một số màn hình phụ ra Game Panel
 */
public class Screen {
	protected int _width, _height;
	public int[] _pixels;
	private int _transparentColor = 0xffff00ff;	//màu hồng
	public static int xOffset = 0, yOffset = 0;
	private Font font;

	/**
	 * Hiển thị màn hình
	 * @param width
	 * @param height
	 */
	public Screen(int width, int height) {
		_width = width;
		_height = height;
		_pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = 0;
		}
	}
	
	/**
	 * Cập nhật hình ảnh thực thể cho màn hình
	 */
	public void renderEntity(int xp, int yp, Entity entity) { //lưu các pixel thực thể
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp; //add offset
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp; //add offset
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;	//fix viền đen
				if(xa < 0) xa = 0; //start at 0 from left
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) _pixels[xa + ya * _width] = color;
			}
		}
	}
	
	/**
	 * Cập nhật hình ảnh thực thể trên cùng của layer cho màn hình
	 */
	public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp;
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break;	//fix viền đen
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) 
					_pixels[xa + ya * _width] = color;
				else
					_pixels[xa + ya * _width] = below.getPixel(x + y * below.getSize());
			}
		}
	}
	
	/**
	 * Setter: Offset
	 */
	public static void setOffset(int xO, int yO) {
		xOffset = xO;
		yOffset = yO;
	}
	
	/**
	 * Tính toán phần hoành độ chưa hiển thị trên màn hình
	 */
	public static int calculateXOffset(Board board, Bomber bomber) {
		if(bomber == null) return 0;
		int temp = xOffset;
		
		double BomberX = bomber.getX() / 16;
		double complement = 0.5;
		int firstBreakpoint = board.getWidth() / 4;
		int lastBreakpoint = board.getWidth() - firstBreakpoint;
		
		if(BomberX > firstBreakpoint + complement && BomberX < lastBreakpoint - complement)
			temp = (int)bomber.getX() - (Game.WIDTH / 2);

		return temp;
	}
	
	/**
	 * Màn hình EndGame
	 */
	public void drawEndGame(Graphics g, int points) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		font = new Font("Bomber Font", Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);
		
		font = new Font("Bomber Font", Font.BOLD, 5 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		drawCenteredString("You've earned " + points + " points!", getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE, g);
	}
	
	/**
	 * Màn hình Level
	 */
	public void drawChangeLevel(Graphics g, int level) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		font = new Font("Bomber Font", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("STAGE " + level, getRealWidth(), getRealHeight(), g);
	}
	
	/**
	 * Màn hình PauseGame
	 */
	public void drawPaused(Graphics g) {
		font = new Font("Bomber Font", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);
	}
	
	/**
	 * Hiển thị thông tin của màn hình
	 */
	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	 }
	
	/**
	 * Getter: chiều dài màn hình
	 */
	public int getWidth() {
		return _width;
	}
	
	/**
	 * Getter: chiều cao màn hình
	 */
	public int getHeight() {
		return _height;
	}
	
	/**
	 * Getter: chiều dài so với scale của game
	 */
	public int getRealWidth() {
		return _width * Game.SCALE;
	}
	
	/**
	 * Getter: chiều cao so với scale của game
	 */
	public int getRealHeight() {
		return _height * Game.SCALE;
	}
}
