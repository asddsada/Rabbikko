package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
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
		if (isUsable()) {
			RenderableHolder.heal.play();
			GameLogic.hero.healHp(POINT);
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

	@Override
	public void reset() {
		this.amount=0;
	}
}
