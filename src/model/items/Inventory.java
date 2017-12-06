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
import model.field.Shop;

public class Inventory {
	private static final Useable[] BAG = {
			new	Weapons(500, RenderableHolder.sword2, RenderableHolder.sword2),
			new	Weapons(500, RenderableHolder.sword2, RenderableHolder.sword2),
			new	Weapons(500, RenderableHolder.sword2, RenderableHolder.sword2),
		};
	public int bagCapacity;
	
	public Inventory() {
		this.bagCapacity = 1;
		add(1); //need 1 sword
	}
	
	public void add(int i) {
		bagCapacity++;
		BAG[i].add();
	}
	
	public boolean isFull() {
		if (this.bagCapacity == 50) {
			return true;
		}
		return false;
	}
	
	public void update() {
	}

	public static Useable[] getBag() {
		return BAG;
	}
}
