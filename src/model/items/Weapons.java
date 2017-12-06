package model.items;

import javafx.scene.image.Image;
import model.GameObject;
import sharedObj.IRenderable;

public abstract class Weapons extends GameObject implements Useable {
	public Weapons(double x, double y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	protected int price;
	protected Image img;
	protected String description;
	protected int additionalStat;
	
	public abstract boolean isBuyable();
	
	public int getPrice() {
		return price;
	}
}
