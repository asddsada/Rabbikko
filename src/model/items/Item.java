package model.items;

import javafx.scene.image.Image;
import javafx.scene.text.Text;
import sharedObj.IRenderable;

public abstract class Item implements Useable{
	protected int price;
	protected Image imgWeapon;
	protected int amount;
	
	public abstract boolean isBuyable();
	public abstract boolean isUsable();
	
	public int getAmount() {
		return amount;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void add() {
		this.amount++;
	}
}
