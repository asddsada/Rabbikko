package model.inventory;

import javafx.scene.image.Image;
import sharedObj.IRenderable;

public abstract class Weapons implements Buyable , Useable , IRenderable {
	private int price;
	private Image img;
	private int additionalStat;
	
	public abstract void isBuyable();
	public abstract void isUsed();
}
