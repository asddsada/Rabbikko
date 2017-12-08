package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
import sharedObj.RenderableHolder;

public class Mana extends Item {
	private final int POINT = 100;
	
	public Mana() {
		this.price = 500;
		this.imgWeapon = RenderableHolder.mpPotion;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		if (isUsable()) {
			GameLogic.hero.healMp(POINT);
			amount--;
		}
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		if (!Hero.inventory.isFull() && GameLogic.hero.getMoney()>=this.price)  return true;
		return false;
	}

	@Override
	public boolean isUsable() {
		// TODO Auto-generated method stub
		return this.amount > 0;
	}
	
	public void reset() {
		this.amount=0;
	}
}
