package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.GameLogic;
import model.entity.Hero;
import model.items.Bow;
import model.items.Health;
import model.items.Inventory;
import model.items.Item;
import model.items.Mana;
import model.items.Staff;
import model.items.Sword;
import model.items.Useable;
import model.items.Weapons;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;

public class Shop {
	private final static int SHELF_SIZE = 3; //change to match with the shelf
	public void shop() {
		
	}
	
	//Not completed
	public void buy(int index) {
		Inventory.getBag()[index].buy();
	}
	
	//Not completed
	public boolean canBuy(Useable item) {		
		return true;
	}
}
