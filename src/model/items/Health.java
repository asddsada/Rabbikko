package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObj.RenderableHolder;

public class Health extends Item{
	private final int healPoint = 100;
	
	public Health() {
		this.price = 600;
		this.imgWeapon = RenderableHolder.hpPotion;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		GameLogic.hero.healHp(healPoint);
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return true;
	}
}
