package model.inventory;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class Inventory {
	Map<Useable, Integer> inventory; 
	public int capacity;
	
	public Inventory() {
		Map<Useable,Integer> inventory = new HashMap(); 
		capacity = 6;
	}
	
	public void add(Useable item) {
		if (isFull()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Inventory Full");
			return;
			//cannot buy items
		}
		if (!inventory.containsKey(item)) {
			inventory.put(item, 1);
		}
		else {
			inventory.put(item,inventory.get(item)+1);
		}
	}
	
	public boolean isFull() {
		if (this.capacity == 6) {
			return true;
		}
		return false;
	}
	
	public void update() {
		
	}
}
