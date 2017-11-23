package model.entity;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.attribute.Attribute;
import sharedObj.RenderableHolder;
import view.SceneManeger;

public class Hero extends DungeonableEntity {

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH/2,SceneManeger.HEIGHT/2, RenderableHolder.humanImage, 1, 3, direction, atkType);
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
