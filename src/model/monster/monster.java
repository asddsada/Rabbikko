package model.monster;

import javafx.scene.image.Image;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;

public abstract class monster extends DungeonableEntity {

	public monster(Image img, int direction, Attribute atkType) {
		super(5	,5,img, direction, direction, direction, atkType);
		//ran x,y
	}

}
