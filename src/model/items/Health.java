package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObj.RenderableHolder;

public class Health extends Item{
	private final int POINT = 100;
	
	public Health() {
		this.amount = 0;
		this.price = 600;
		this.imgWeapon = RenderableHolder.hpPotion;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		GameLogic.hero.healHp(POINT);
		amount--;
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return true;
	}
}
