package model.items;

import javafx.scene.image.Image;
import javafx.scene.text.Text;
import sharedObj.IRenderable;

public abstract class Item implements Useable{
	protected int price;
	protected Image img;
	
	public abstract boolean isBuyable();
//	public abstract boolean isUsed();
	
	public int getPrice() {
		return price;
	}
}
