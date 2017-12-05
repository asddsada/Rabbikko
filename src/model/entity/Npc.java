package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.attribute.Attribute;
import model.field.Obstructable;
import utility.Pair;

public class Npc extends Entity implements Obstructable {

	public Npc(double x,double y,Image img, int row, int column, int direction,int movespeed) {
		super(x,y,img, row, column, direction,movespeed);
		race = DungeonableEntity.HUMANITY;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isBlock(double x, double y) {
		for (DungeonableEntity<Attribute> other : GameLogic.dungeon.getENTITIES_HOLDER()) {			
			if (other.hashCode() != this.hashCode() && this.race==other.race&& 
					super.isCollide(other ,x, y)) {
				if(this.direction==BACK&&other.getY() - getHeight() / 3 <= y) this.z++;
				return true;
			}
		}
		return false;
	}
}
