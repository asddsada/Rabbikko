package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import model.attribute.Attribute;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class Hero extends DungeonableEntity {

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH/2,(SceneManeger.HEIGHT-100)/2, RenderableHolder.humanImage, 0, 3, direction,7, atkType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(InputUtility.isKeyPressed(KeyCode.W)) move(Entity.BACK);
		else if(InputUtility.isKeyPressed(KeyCode.S)) move(Entity.FRONT);
		else if(InputUtility.isKeyPressed(KeyCode.A)) move(Entity.LEFT);
		else if(InputUtility.isKeyPressed(KeyCode.D)) move(Entity.RIGHT);
		
	}

}
