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

public class Shop implements IRenderable{
	public Inventory inventory;
	public Health potion1;
	public Mana potion2;
	public Sword sword;
	public Bow bow;
	public Staff staff;
	
	public void shop() {
		this.inventory = new Inventory();
		this.potion1 = new Health();
		this.potion2 = new Mana();
		this.sword = new Sword();
		this.bow = new Bow();
		this.staff = new Staff();
	}
	
	//Not completed
	public void buy(Useable item) {
		if (item instanceof Item) {
			canBuy(item);
		}
		else if (item instanceof Weapons) {
			
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("Cannot buy item. Not enough money");
		alert.showAndWait();
	}
	
	//Not completed
	public boolean canBuy(Useable item) {
		
		return true;
	}
	
	public void update() {
		
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
}
