/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character;

import java.util.ArrayList;

import bomberman.Board;
import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.character.enemy.Enemy;
import bomberman.entities.tile.item.Item;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.input.Keyboard;

/**
 * Nhân vật của người chơi
 */
public class Bomber extends Character {
	private ArrayList<Bomb> _bombs = new ArrayList<>();
	public static ArrayList<Item> _items = new ArrayList<>();
	protected Keyboard _input;
	protected int _timeBetweenPutBombs = 0;	//thời gian giữa 2 bomb được đặt
	protected int _finalAnimation = 25;	//thời gian xử lý hiệu ứng (<timeAnimate để tránh lặp lại hiệu ứng)


	
	/**
	 * Hiển thị hình ảnh của thực thể Bomber
	 * @param x
	 * @param y
	 * @param board
	 */
	public Bomber(int x, int y, Board board) {
		super(x, y, board);
		_bombs = _board.getBombs();
		_input = _board.getInput();
		_sprite = Sprite.player_down;
		setTimeAfter(220);

	}

	@Override
	public void update() {
		// TODO: Cập nhật trạng thái của Bomber, xử lý hiệu ứng hoạt hình, xử lý đặt bom
		animate();
		
        if(isAlive()) {
    		clearBombs();
	        if(_timeBetweenPutBombs < -7500)
	        	_timeBetweenPutBombs = 0;
	        else 
	        	_timeBetweenPutBombs--;

	        calculateMove();
	        detectPlaceBomb();
        }
        else {
            afterKill();
            return;
        }
	}

	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của Bomber theo trạng thái còn sống và bị tiêu diệt
		calculateOffset();
		
        if(isAlive())
            chooseSprite();
        else {
        	if(_timeAfter > 0) {
        		_animate = 0;
        		setSprite(Sprite.player_dead);
        	}
        	else
        		setSprite(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, 27));
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.getSize(), this);		
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý va chạm với Enemy
		
		return true;
	}
	
	/**
	 * Tính phần bù của màn hình dựa vào vị trí Bomber
	 */
    public void calculateOffset() {
    	
	}

	@Override
	protected void calculateMove() {
        // TODO: Xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: Cập nhật lại giá trị của _moving khi thay đổi trạng thái di chuyển	

	}

	@Override
	protected boolean canMove(double x, double y) {
        // TODO: Kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
		return true;
	}
	
	@Override
	protected void move(double xIn, double yIn) {
        // TODO: Sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: Cập nhật giá trị _direction sau khi di chuyển		
	}

	@Override
	public void kill() {
		// TODO: Xử lý khi Bomber bị tiêu diệt

	}

	@Override
	protected void afterKill() {
		// TODO: Xử lý sau khi Bomber bị tiêu diệt
	}
	
	/**
	 * Thêm Item cho Bomber
	 */
	public void addItem(Item i) {

	}

	/**
     * Kiểm tra xem có đặt được bom hay không? Nếu có thì đặt bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        // TODO: Kiểm tra xem phím điều khiển đặt bom có được gõ và giá trị _timeBetweenPutBombs, Game.getBombRate() có thỏa mãn hay không
        // TODO: Game.getBombRate() sẽ trả về số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
        // TODO: _timeBetweenPutBombs dùng để ngăn chặn Bomber đặt 2 Bomb cùng tại 1 vị trí trong 1 khoảng thời gian quá ngắn

    }

    /**
     * Đặt bom
     */
    protected void placeBomb(int x, int y) {
        // TODO: Thực hiện tạo đối tượng bom, đặt vào vị trí (x, y)
    }
    
    /**
     * Xử lý khi bom đã nổ xong
     */
    private void clearBombs() {

    }
	
	/**
	 * Tạo hình cho Bomber
	 */
	private void chooseSprite() {
		switch (getDirection()) {
	        case 0:
	        	setSprite(Sprite.player_up);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(Sprite.player_up, Sprite.player_up1, Sprite.player_up2, _animate, 9));
	            break;
	        case 1:
	        	setSprite(Sprite.player_right);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(Sprite.player_right, Sprite.player_right1, Sprite.player_right2, _animate, 9));
	            break;
	        case 2:
	        	setSprite(Sprite.player_down);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(Sprite.player_down, Sprite.player_down1, Sprite.player_down2, _animate, 9));
	            break;
	        case 3:
	        	setSprite(Sprite.player_left);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(Sprite.player_left, Sprite.player_left1, Sprite.player_left2, _animate, 9));
	            break;
	        default:
	        	setSprite(Sprite.player_down);
	            break;
		}
	}
}
