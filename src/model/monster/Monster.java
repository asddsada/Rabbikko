package model.monster;

import javafx.scene.image.Image;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;

public class Monster extends DungeonableEntity<Attribute> {

	

	public Monster(double x, double y, Image img, int row, int column, int direction,int maxHp, int movespeed, Attribute atkType
			) {
		super(x, y, img, row, column, direction, movespeed, movespeed, atkType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
