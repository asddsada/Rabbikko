package model.inventory;

import javafx.scene.image.Image;
import sharedObj.IRenderable;

public abstract class Item implements Buyable , Useable , IRenderable{
	private int price;
	private Image img;
	
	public abstract void isBuyable();
	public abstract void isUsed();
}
