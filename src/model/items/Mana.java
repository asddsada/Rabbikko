package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
import sharedObj.RenderableHolder;

public class Mana extends Item {
	private final int healPoint = 100;
	
	public Mana() {
		this.price = 500;
		this.img = RenderableHolder.mpPotion;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		GameLogic.hero.healMp(healPoint);
	}

	@Override
	public boolean isBuyable() {
		// TODO Auto-generated method stub
		return true;
	}
}
