package model.items;

import javafx.scene.canvas.GraphicsContext;
import sharedObj.RenderableHolder;

public class Staff extends Weapons {

	public Staff() {
		this.price = 500;
		this.img = RenderableHolder.staff;
		this.description = "Staff";
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return false;
	}
}
