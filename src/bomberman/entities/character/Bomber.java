/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities.character;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import bomberman.Board;
import bomberman.Game;
import bomberman.entities.Entity;
import bomberman.entities.Message;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.character.enemy.Enemy;
import bomberman.entities.tile.item.Item;
import bomberman.graphics.Screen;
import bomberman.graphics.Sprite;
import bomberman.input.Keyboard;
import bomberman.level.Coordinates;
import bomberman.sound.Sound;

/**
 * Nhân vật của người chơi
 */
public class Bomber extends Character {
	private ArrayList<Bomb> _bombs = new ArrayList<>();
	public static ArrayList<Item> _items = new ArrayList<>();
	protected Keyboard _input;
	protected int _timeBetweenPutBombs = 0;	//thời gian giữa 2 bomb được đặt
	protected int _finalAnimation = 25;	//thời gian xử lý hiệu ứng (<timeAnimate để tránh lặp lại hiệu ứng)

	private Sound bomberDie = new Sound("res/sounds/bomberdie.wav");

	
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
        	bomberDie.play();
        	if(_timeAfter > 0) {
        		_animate = 0;
        		setSprite(Sprite.player_dead);
        	}
        	else
        		setSprite(Sprite.movingSprite(27, _animate, Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3));
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.getSize(), this);		
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: Xử lý va chạm với Enemy
		if(e instanceof Enemy) {
			this.kill();
			return false;
		}
		return true;
	}
	
	/**
	 * Tính phần bù của màn hình dựa vào vị trí Bomber
	 */
    public void calculateOffset() {
        int xScroll = Screen.calculateXOffset(_board, this);
        Screen.setOffset(xScroll, 0);
	}

	@Override
	protected void calculateMove() {
        // TODO: Xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: Cập nhật lại giá trị của _moving khi thay đổi trạng thái di chuyển	
		int xIn = 0, yIn = 0;
		if(_input.up) yIn--;
		if(_input.down) yIn++;
		if(_input.left) xIn--;
		if(_input.right) xIn++;
		
		
		if(xIn != 0 || yIn != 0) {
			move(xIn * Game.getBomberSpeed(), yIn * Game.getBomberSpeed());
			_moving = true;
		}
		else {
			_moving = false;
		}
	}

	@Override
	protected boolean canMove(double x, double y) {
        // TODO: Kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
		for (int i = 0; i < 4; i++) {	//kiểm tra va chạm ở 4 góc người chơi
			double xt = ((_x + x) + i % 2 * 11) / Game.TILES_SIZE;	//chia cho kích thước khối để di chuyển tới tọa độ từng khối
			double yt = ((_y + y) + i / 2 * 12 - 13) / Game.TILES_SIZE;
			
			Entity a = _board.getEntity(xt, yt, this);
			
			if(!a.collide(this))
				return false;
		}
		return true;
	}
	
	@Override
	protected void move(double xIn, double yIn) {
        // TODO: Sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: Cập nhật giá trị _direction sau khi di chuyển
		if(yIn < 0) _direction = 0;
		if(xIn > 0) _direction = 1;
		if(yIn > 0) _direction = 2;
		if(xIn < 0) _direction = 3;

		if(canMove(0, yIn))
			_y += yIn;

		if(canMove(xIn, 0))
			_x += xIn;
	}

	@Override
	public void kill() {
		// TODO: Xử lý khi Bomber bị tiêu diệt
        if (!isAlive()) return;
        _alive = false;
        _board.addLives(-1);
        
        Message msg0 = new Message("YOU GOT KILLED", getXMessage() + Game.TILES_SIZE, getYMessage() - Game.TILES_SIZE * 3, 2, Color.BLACK, 15);        
        Message msg = new Message(" ↓♥", getXMessage(), getYMessage(), 2, Color.RED, 30);
        _board.addMessage(msg0);
        _board.addMessage(msg);
	}

	@Override
	protected void afterKill() {
		// TODO: Xử lý sau khi Bomber bị tiêu diệt
		clearBombs();
        if (_timeAfter > 0) _timeAfter--;
        else {
        	if(_finalAnimation > 0) _finalAnimation--;
        	else {
        		_board.respawn();
	    		if(_board.getLives() == 0)
	    			_board.endGame();
        	}
        }	
	}
	
	/**
	 * Thêm Item cho Bomber
	 */
	public void addItem(Item i) {
		if(i.isRemoved())
			return;
		_items.add(i);
		i.setValue();
	}

	/**
     * Kiểm tra xem có đặt được bom hay không? Nếu có thì đặt bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        // TODO: Kiểm tra xem phím điều khiển đặt bom có được gõ và giá trị _timeBetweenPutBombs, Game.getBombRate() có thỏa mãn hay không
        // TODO: Game.getBombRate() sẽ trả về số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
        // TODO: _timeBetweenPutBombs dùng để ngăn chặn Bomber đặt 2 Bomb cùng tại 1 vị trí trong 1 khoảng thời gian quá ngắn
    	
    	if(_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {
    		int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);
    		int yt = Coordinates.pixelToTile(_y + _sprite.getSize() / 2 - _sprite.getSize());	//trừ đi một nửa chiều cao của bomber và trừ đi tọa độ y
    		
            // TODO: Nếu 3 điều kiện trên thỏa mãn thì thực hiện đặt bom bằng placeBomb()
            // TODO: Sau khi đặt, giảm số lượng Bomb Rate và reset _timeBetweenPutBombs về 0
    		placeBomb(xt, yt);
    		Game.addBombRate(-1);
    		_timeBetweenPutBombs = 30;
    	}
    }

    /**
     * Đặt bom
     */
    protected void placeBomb(int x, int y) {
        // TODO: Thực hiện tạo đối tượng bom, đặt vào vị trí (x, y)
    	Bomb b = new Bomb(x, y, _board);
    	_board.addBomb(b);
    }
    
    /**
     * Xử lý khi bom đã nổ xong
     */
    private void clearBombs() {
    	Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }
    }
	
	/**
	 * Tạo hình cho Bomber
	 */
	private void chooseSprite() {
		switch (getDirection()) {
	        case 0:
	        	setSprite(Sprite.player_up);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(9, _animate, Sprite.player_up, Sprite.player_up1, Sprite.player_up2));
	            break;
	        case 1:
	        	setSprite(Sprite.player_right);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(9, _animate, Sprite.player_right, Sprite.player_right1, Sprite.player_right2));
	            break;
	        case 2:
	        	setSprite(Sprite.player_down);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(9, _animate, Sprite.player_down, Sprite.player_down1, Sprite.player_down2));
	            break;
	        case 3:
	        	setSprite(Sprite.player_left);
	            if(isMoving())
	            	setSprite(Sprite.movingSprite(9, _animate, Sprite.player_left, Sprite.player_left1, Sprite.player_left2));
	            break;
		}
	}
}
