package model.items;

import javafx.scene.canvas.GraphicsContext;
import sharedObj.RenderableHolder;

public class Bow extends Weapons {

	public Bow() {
		this.price = 500;
		this.img = RenderableHolder.bow;
		this.description = "Bow";
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
