package model.monster;

import javafx.scene.image.Image;
import logic.GameLogic;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;

public class Monster extends DungeonableEntity<Attribute> {

	public Monster(double x, double y, Image img, int row, int column, int direction, int movespeed,int mass,int maxHp,int baseAtk,
			Attribute atkType) {
		super(x, y, img, row, column, direction, movespeed, mass,maxHp,baseAtk, atkType);
		this.race=DungeonableEntity.MONSTER;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isBlock(double x, double y) {
		for (DungeonableEntity<Attribute> other : GameLogic.dungeon.getENTITIES_HOLDER()) {			
			if (other.hashCode() != this.hashCode() && 
					super.isCollide(other ,x, y)) return true;
		}
		return false;
	}
}
