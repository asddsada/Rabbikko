package model.items;

import javafx.scene.image.Image;
import sharedObj.IRenderable;

public abstract class Weapons implements Useable {
	protected int price;
	protected Image img;
	protected String description;
	protected int additionalStat;
	
	public abstract boolean isBuyable();
	
	public int getPrice() {
		return price;
	}
}
