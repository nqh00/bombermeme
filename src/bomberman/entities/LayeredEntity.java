/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.entities;

import java.util.LinkedList;

import bomberman.entities.tile.Brick;
import bomberman.graphics.Screen;

/**
 * Chứa và quản lý nhiều thực thể tại cùng một vị trí,
 * ví dụ: tại vị trí có Item, có 3 thực thể khác nhau là [Grass, Item, Brick]
 */
public class LayeredEntity extends Entity {
	
	/**
	 * Danh sách các thực thể cùng vị trí
	 */
	protected LinkedList<Entity> _entities = new LinkedList<>();
	
	/**
	 * Hiển thị nhiều thực thể cùng một vị trí
	 * @param x
	 * @param y
	 * @param e (thực thể phía dưới)
	 */
	public LayeredEntity(int x, int y, Entity ... e) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < e.length; i++) {
			_entities.add(e[i]);
			
			// TODO: Tạo Sprite nền cho Brick khi Brick bị tiêu diệt
			if(i > 1) {
				if(e[i] instanceof Brick)
					((Brick)e[i]).addBelowSprite(e[i-1].getSprite());
			}
		}
	}

	@Override
	public void update() {
		// TODO: Cập nhật trạng thái của thực thể trên cùng
		clearRemoved();
		getTopEntity().update();
		
	}

	@Override
	public void render(Screen screen) {
		// TODO: Cập nhật hình ảnh của thực thể trên cùng
		getTopEntity().render(screen);
		
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: Lấy thực thể trên cùng ra để xử lý va chạm
		return getTopEntity().collide(e);
	}
	
	/**
	 * Getter: thực thể nằm trên cùng layer
	 */
	public Entity getTopEntity() {
		return _entities.getLast();
	}
	
	/**
	 * Setter: xóa thực thể trên cùng layer
	 */
	public void clearRemoved() {
		Entity top = getTopEntity();
		if(top.isRemoved())
			_entities.removeLast();
	}
}
