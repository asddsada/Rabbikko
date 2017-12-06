package model.inventory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import logic.GameLogic;

public class Mana extends Item {
	public Text description;

	@Override
	public void buy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		GameLogic.hero.healMp(100);
	}

	@Override
	public void isBuyable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isUsed() {
		// TODO Auto-generated method stub
		
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
	

}
