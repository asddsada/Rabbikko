package model.items;

import javafx.scene.canvas.GraphicsContext;
import sharedObj.RenderableHolder;

public class Sword extends Weapons{
	
	public Sword() {
		this.price = 500;
		this.img = RenderableHolder.sword;
		this.description = "Sword";
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
