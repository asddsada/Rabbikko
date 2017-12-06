package model.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import javafx.scene.control.Button;

public class Inventory implements IRenderable {
	private static final Useable[] BAG = {
			new	Weapons(500, RenderableHolder.sword2),
			new	Weapons(500, RenderableHolder.sword2),
			new	Weapons(500, RenderableHolder.sword2)
		};
	public int bagCapacity;
	
	public Inventory() {
		this.bagCapacity = 1;
	}
	
	public void add(Useable item) {
		if (isFull()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Inventory Full");
			alert.showAndWait();
			return;
			//cannot buy items
		}
		inventory.put(item,inventory.get(item)+1);
		bagCapacity += 1;
	}
	
	public boolean isFull() {
		if (this.bagCapacity == 50) {
			return true;
		}
		return false;
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

	public static Useable[] getBag() {
		return BAG;
	}
}
