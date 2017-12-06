package model.monster;

import java.util.ArrayList;

import javafx.scene.image.Image;
import logic.GameLogic;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.field.Dungeon;
import model.field.Obstructable;
import utility.InputUtility;

public class Monster extends DungeonableEntity<Attribute> implements Obstructable{

	public Monster(double x, double y, Image img, int row, int column, int direction, int movespeed,int mass,int maxHp,int baseAtk,
			Attribute atkType) {
		super(x, y, img, row, column, direction, movespeed, mass,maxHp,baseAtk, atkType);
		this.race=DungeonableEntity.MONSTER;
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	protected boolean isBlock(double x, double y) {
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(this, x, y);
		for (DungeonableEntity<Attribute> other : inArea) {
			if (other instanceof Obstructable)
				return true;
		}
		return false;
	}
}
