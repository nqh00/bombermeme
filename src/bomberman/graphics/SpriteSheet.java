/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {
	private String _path;
	public final int SIZE;
	public int[] _pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("res/textures/classic.png", 256);
	
	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(_path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
