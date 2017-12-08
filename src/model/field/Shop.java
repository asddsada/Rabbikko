package model.field;

import Main.DungeonMain;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.GameLogic;
import model.entity.Hero;
import model.items.Inventory;
import model.items.Item;
import model.items.Weapons;
import utility.ResourceLoader;

public class Shop {
	private final static int SHELF_SIZE = 5; // change to match with the shelf

	public void shop() {

	}
	
	public void buy(int index) {
		if (Inventory.getBag()[index] instanceof Item) {
			if (((Item)Inventory.getBag()[index]).isBuyable()) {
				ResourceLoader.coin.play(100);
				GameLogic.hero.useMoney(((Item)Inventory.getBag()[index]).getPrice());
				Hero.inventory.add(index);
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Cannot Buy item , Check your inventory or money");
				alert.setHeaderText("Shop Error");
				alert.showAndWait();
			}
		}
		else if (Inventory.getBag()[index] instanceof Weapons) {
			if (((Weapons)Inventory.getBag()[index]).isBuyable()) {
				ResourceLoader.coin.play(100);
				GameLogic.hero.useMoney(((Weapons)Inventory.getBag()[index]).getPrice());
				Hero.inventory.add(index);
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Cannot Buy item , Check your inventory or money");
				alert.showAndWait();
			}
		}
		DungeonMain.getCanvas().canvasUpdate();
	}
}
