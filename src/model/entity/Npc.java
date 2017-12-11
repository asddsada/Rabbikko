package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.Obstructable;
import model.attribute.Attribute;
import utility.Constant;
import utility.Pair;

public class Npc extends Entity implements Obstructable {

	public Npc(double x, double y, Image img, int row, int column, int direction, int movespeed) {
		super(x, y, img, row, column, direction, movespeed, 50);
		race = Constant.ENTITY_HUMANITY;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isBlock(double x, double y) {
		for (DungeonableEntity<Attribute> other : GameLogic.dungeon.getEntitiesHolder()) {
			if (other.hashCode() != this.hashCode() && this.race == other.race && super.isCollide(other, x, y))
				return true;
		}
		return false;
	}
}
