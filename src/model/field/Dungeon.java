package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Dungeon extends Field{
	private int lvl;
	
	public Dungeon() {
		super(RenderableHolder.dungeonBg , SceneManeger.WIDGTH, SceneManeger.HEIGHT-100, new Pair());
		this.lvl=1;
	}	

	@Override
	public boolean isInBorder(Pair p) {
		return false;
	}

	

}
