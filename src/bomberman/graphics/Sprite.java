/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.graphics;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;
	
	/*
	|--------------------------------------------------------------------------
	| Sprite vật liệu
	|--------------------------------------------------------------------------
	 */
		public static Sprite grass = new Sprite(16, 5, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite brick = new Sprite(16, 6, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite wall = new Sprite(16, 5, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite portalIn = new Sprite(16, 4, 0, SpriteSheet.tiles, 16, 7);
		public static Sprite portalOut = new Sprite(16, 4, 1, SpriteSheet.tiles, 16, 7);

	/*
	|--------------------------------------------------------------------------
	| Sprite người chơi
	|--------------------------------------------------------------------------
	 */
		public static Sprite player_up = new Sprite(16, 0, 0, SpriteSheet.tiles, 12, 16);
		public static Sprite player_down = new Sprite(16, 2, 0, SpriteSheet.tiles, 12, 15);
		public static Sprite player_left = new Sprite(16, 3, 0, SpriteSheet.tiles, 10, 15);
		public static Sprite player_right = new Sprite(16, 1, 0, SpriteSheet.tiles, 10, 16);
		
		public static Sprite player_up1 = new Sprite(16, 0, 1, SpriteSheet.tiles, 12, 16);
		public static Sprite player_up2 = new Sprite(16, 0, 2, SpriteSheet.tiles, 12, 15);
		
		public static Sprite player_down1 = new Sprite(16, 2, 1, SpriteSheet.tiles, 12, 15);
		public static Sprite player_down2 = new Sprite(16, 2, 2, SpriteSheet.tiles, 12, 16);
		
		public static Sprite player_left1 = new Sprite(16, 3, 1, SpriteSheet.tiles, 11, 16);
		public static Sprite player_left2 = new Sprite(16, 3, 2, SpriteSheet.tiles, 12 ,16);
		
		public static Sprite player_right1 = new Sprite(16, 1, 1, SpriteSheet.tiles, 11, 16);
		public static Sprite player_right2 = new Sprite(16, 1, 2, SpriteSheet.tiles, 12, 16);
		
		public static Sprite player_dead = new Sprite(16, 0, 3, SpriteSheet.tiles, 14, 14);
		public static Sprite player_dead1 = new Sprite(16, 1, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite player_dead2 = new Sprite(16, 2, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite player_dead3 = new Sprite(16, 3, 3, SpriteSheet.tiles, 16, 16);
		
	/*
	|--------------------------------------------------------------------------
	| Sprite quái
	|--------------------------------------------------------------------------
	 */
		
	//PEPE
		public static Sprite pepe_up = new Sprite(16, 8, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_down = new Sprite(16, 10, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_left = new Sprite(16, 11, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_right = new Sprite(16, 9, 0, SpriteSheet.tiles, 16, 16);
		
		public static Sprite pepe_up1 = new Sprite(16, 8, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_up2 = new Sprite(16, 8, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite pepe_down1 = new Sprite(16, 10, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_down2 = new Sprite(16, 10, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite pepe_left1 = new Sprite(16, 11, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_left2 = new Sprite(16, 11, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite pepe_right1 = new Sprite(16, 9, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_right2 = new Sprite(16, 9, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite pepe_dead = new Sprite(16, 8, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_dead1 = new Sprite(16, 9, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite pepe_dead2 = new Sprite(16, 10, 3, SpriteSheet.tiles, 15, 15);
		public static Sprite pepe_dead3 = new Sprite(16, 11, 3, SpriteSheet.tiles, 10, 9);
	
	//Scorpion
		public static Sprite scorpion_up = new Sprite(16, 12, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_down = new Sprite(16, 14, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_left = new Sprite(16, 15, 0, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_right = new Sprite(16, 13, 0, SpriteSheet.tiles, 16, 16);
		
		public static Sprite scorpion_up1 = new Sprite(16, 12, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_up2 = new Sprite(16, 12, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite scorpion_down1 = new Sprite(16, 14, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_down2 = new Sprite(16, 14, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite scorpion_left1 = new Sprite(16, 15, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_left2 = new Sprite(16, 15, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite scorpion_right1 = new Sprite(16, 13, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_right2 = new Sprite(16, 13, 2, SpriteSheet.tiles, 16, 16);
		
		public static Sprite scorpion_dead = new Sprite(16, 12, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_dead1 = new Sprite(16, 13, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_dead2 = new Sprite(16, 14, 3, SpriteSheet.tiles, 16, 16);
		public static Sprite scorpion_dead3 = new Sprite(16, 15, 3, SpriteSheet.tiles, 16, 16);
		
	//Alien
		public static Sprite alien_up = new Sprite(16, 4, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_down = new Sprite(16, 6, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_left = new Sprite(16, 7, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_right = new Sprite(16, 5, 4, SpriteSheet.tiles, 16, 16);
		
		public static Sprite alien_up1 = new Sprite(16, 4, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_up2 = new Sprite(16, 4, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite alien_down1 = new Sprite(16, 6, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_down2 = new Sprite(16, 6, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite alien_left1 = new Sprite(16, 7, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_left2 = new Sprite(16, 7, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite alien_right1 = new Sprite(16, 5, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_right2 = new Sprite(16, 5, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite alien_dead = new Sprite(16, 4, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_dead1 = new Sprite(16, 5, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_dead2 = new Sprite(16, 6, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite alien_dead3 = new Sprite(16, 7, 7, SpriteSheet.tiles, 16, 16);
		
	//Bat
		public static Sprite bat_up = new Sprite(16, 8, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_down = new Sprite(16, 10, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_left = new Sprite(16, 11, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_right = new Sprite(16, 9, 4, SpriteSheet.tiles, 16, 16);
		
		public static Sprite bat_up1 = new Sprite(16, 8, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_up2 = new Sprite(16, 8, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite bat_down1 = new Sprite(16, 10, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_down2 = new Sprite(16, 10, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite bat_left1 = new Sprite(16, 11, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_left2 = new Sprite(16, 11, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite bat_right1 = new Sprite(16, 9, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_right2 = new Sprite(16, 9, 6, SpriteSheet.tiles, 16, 16);
		
		public static Sprite bat_dead = new Sprite(16, 8, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_dead1 = new Sprite(16, 9, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_dead2 = new Sprite(16, 10, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite bat_dead3 = new Sprite(16, 11, 7, SpriteSheet.tiles, 16, 16);
		
		
	//Bomber Negative
		public static Sprite negative_up = new Sprite(16, 0, 12, SpriteSheet.tiles, 12, 16);
		public static Sprite negative_down = new Sprite(16, 2, 12, SpriteSheet.tiles, 12, 15);
		public static Sprite negative_left = new Sprite(16, 3, 12, SpriteSheet.tiles, 10, 15);
		public static Sprite negative_right = new Sprite(16, 1, 12, SpriteSheet.tiles, 10, 16);
		
		public static Sprite negative_up1 = new Sprite(16, 0, 13, SpriteSheet.tiles, 12, 16);
		public static Sprite negative_up2 = new Sprite(16, 0, 14, SpriteSheet.tiles, 12, 15);
		
		public static Sprite negative_down1 = new Sprite(16, 2, 13, SpriteSheet.tiles, 12, 15);
		public static Sprite negative_down2 = new Sprite(16, 2, 14, SpriteSheet.tiles, 12, 16);
		
		public static Sprite negative_left1 = new Sprite(16, 3, 13, SpriteSheet.tiles, 11, 16);
		public static Sprite negative_left2 = new Sprite(16, 3, 14, SpriteSheet.tiles, 12 ,16);
		
		public static Sprite negative_right1 = new Sprite(16, 1, 13, SpriteSheet.tiles, 11, 16);
		public static Sprite negative_right2 = new Sprite(16, 1, 14, SpriteSheet.tiles, 12, 16);
		
		public static Sprite negative_dead = new Sprite(16, 0, 15, SpriteSheet.tiles, 14, 14);
		public static Sprite negative_dead1 = new Sprite(16, 1, 15, SpriteSheet.tiles, 16, 16);
		public static Sprite negative_dead2 = new Sprite(16, 2, 15, SpriteSheet.tiles, 16, 16);
		public static Sprite negative_dead3 = new Sprite(16, 3, 15, SpriteSheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Sprite quả bomb
	|--------------------------------------------------------------------------
	 */
		public static Sprite bomb = new Sprite(16, 4, 3, SpriteSheet.tiles, 15, 15);
		public static Sprite bomb_1 = new Sprite(16, 5, 3, SpriteSheet.tiles, 13, 15);
		public static Sprite bomb_2 = new Sprite(16, 6, 3, SpriteSheet.tiles, 12, 14);
	
	/*
	|--------------------------------------------------------------------------
	| Sprite tia lửa
	|--------------------------------------------------------------------------
	 */
		public static Sprite bomb_exploded = new Sprite(16, 0, 7, SpriteSheet.tiles, 16, 16);
		public static Sprite bomb_exploded1 = new Sprite(16, 0, 8, SpriteSheet.tiles, 16, 16);
		public static Sprite bomb_exploded2 = new Sprite(16, 0, 9, SpriteSheet.tiles, 16, 16);
		public static Sprite bomb_exploded3 = new Sprite(16, 0, 10, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_vertical = new Sprite(16, 0, 5, SpriteSheet.tiles, 8, 16);
		public static Sprite explosion_vertical1 = new Sprite(16, 1, 5, SpriteSheet.tiles, 10, 15);
		public static Sprite explosion_vertical2 = new Sprite(16, 2, 5, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_vertical3 = new Sprite(16, 3, 5, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_horizontal = new Sprite(16, 2, 7, SpriteSheet.tiles, 16, 8);
		public static Sprite explosion_horizontal1 = new Sprite(16, 2, 8, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal2 = new Sprite(16, 2, 9, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal3 = new Sprite(16, 2, 10, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_horizontal_left_last = new Sprite(16, 1, 7, SpriteSheet.tiles, 12, 8);
		public static Sprite explosion_horizontal_left_last1 = new Sprite(16, 1, 8, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal_left_last2 = new Sprite(16, 1, 9, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal_left_last3 = new Sprite(16, 1, 10, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_horizontal_right_last = new Sprite(16, 3, 7, SpriteSheet.tiles, 12, 8);
		public static Sprite explosion_horizontal_right_last1 = new Sprite(16, 3, 8, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal_right_last2 = new Sprite(16, 3, 9, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_horizontal_right_last3 = new Sprite(16, 3, 10, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_vertical_top_last = new Sprite(16, 0, 4, SpriteSheet.tiles, 8, 12);
		public static Sprite explosion_vertical_top_last1 = new Sprite(16, 1, 4, SpriteSheet.tiles, 10, 15);
		public static Sprite explosion_vertical_top_last2 = new Sprite(16, 2, 4, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_vertical_top_last3 = new Sprite(16, 3, 4, SpriteSheet.tiles, 16, 16);
		
		public static Sprite explosion_vertical_down_last = new Sprite(16, 0, 6, SpriteSheet.tiles, 8, 12);
		public static Sprite explosion_vertical_down_last1 = new Sprite(16, 1, 6, SpriteSheet.tiles, 10, 15);
		public static Sprite explosion_vertical_down_last2 = new Sprite(16, 2, 6, SpriteSheet.tiles, 16, 16);
		public static Sprite explosion_vertical_down_last3 = new Sprite(16, 3, 6, SpriteSheet.tiles, 16, 16);
		


		
	/*
	|--------------------------------------------------------------------------
	| Sprite gạch bị nổ
	|--------------------------------------------------------------------------
	 */
		public static Sprite brick_exploded = new Sprite(16, 6, 1, SpriteSheet.tiles, 16, 16);
		public static Sprite brick_exploded1 = new Sprite(16, 6, 2, SpriteSheet.tiles, 16, 15);
		public static Sprite brick_exploded2 = new Sprite(16, 7, 0, SpriteSheet.tiles, 16, 14);
		public static Sprite brick_exploded3 = new Sprite(16, 7, 1, SpriteSheet.tiles, 16, 13);
		public static Sprite brick_exploded4 = new Sprite(16, 7, 2, SpriteSheet.tiles, 16, 12);
		public static Sprite brick_exploded5 = new Sprite(16, 7, 3, SpriteSheet.tiles, 16, 11);
		
	/*
	|--------------------------------------------------------------------------
	| Sprite vật phẩm
	|--------------------------------------------------------------------------
	 */
		public static Sprite powerup_bombs = new Sprite(16, 0, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_flames = new Sprite(16, 1, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_speed = new Sprite(16, 2, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_wallpass = new Sprite(16, 3, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_detonator = new Sprite(16, 4, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_bombpass = new Sprite(16, 5, 11, SpriteSheet.tiles, 16, 16);
		public static Sprite powerup_flamepass = new Sprite(16, 6, 11, SpriteSheet.tiles, 16, 16);
		
	//Sprite void
		public static Sprite voidSprite = new Sprite(16, 15, 15, SpriteSheet.tiles, 16, 16);
		
	/**
	 * Hiển thị hình ảnh của thực thể
	 * @param size (kích thước thực thể, đơn vị: pixel) 
	 * @param x (tung độ thực thể trong ảnh)
	 * @param y (hoành độ thực thể trong ảnh)
	 * @param sheet (hình ảnh của thực thể)
	 * @param rw (chiều dài thật của thực thể)  
	 * @param rh (chiều cao thật của thực thể)
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}
	
	/**
	 * Nạp dữ liệu và tạo khối hình ảnh
	 */
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}

	/**
	 * Tạo hình chuyển động cho thực thể với n hình ảnh
	 */
	public static Sprite movingSprite(int time, int animate, Sprite ... sprites) {
		int calculate = animate % time;
		int diff = time / sprites.length;
		for (int i = 0; i < sprites.length; i++) {
			if(calculate < diff * (i + 1))
				return sprites[i];
		}
		return sprites[sprites.length - 1];
	}

	/**
	 * Getter: kích thước của khối hình
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * Getter: điểm ảnh của khối hình
	 */
	public int getPixel(int i) {
		return _pixels[i];
	}
}
