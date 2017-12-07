package model.items;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.entity.Hero;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import view.SceneManeger;
import javafx.scene.control.Button;
import logic.GameLogic;
import model.field.Shop;

public class Inventory {
	private static final Useable[] BAG = {
			new Health(),new Mana(),
			new	Weapons(500, RenderableHolder.sword2, RenderableHolder.sword),
			new	Weapons(500, RenderableHolder.bow2, RenderableHolder.bow),
			new	Weapons(500, RenderableHolder.staff2, RenderableHolder.staff),
		};
	public int bagCapacity;
	
	public Inventory() {
		this.bagCapacity = 1;
		add(2); //need 1 sword
	}
	
	public void add(int i) {
		BAG[i].add();
		bagCapacity++;
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
