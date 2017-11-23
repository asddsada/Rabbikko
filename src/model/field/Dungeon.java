package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Dungeon extends Field{
	private int lvl;
	
	public Dungeon() {
		super(RenderableHolder.dungeonBg , RenderableHolder.dungeonBg.getWidth(), RenderableHolder.dungeonBg.getHeight(), new Pair());
		this.lvl=1;
	}	

	@Override
	public boolean isInBorder(Pair p) {
		return false;
	}

	

}
