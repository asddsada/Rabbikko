package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import model.entity.Entity;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Dungeon extends Field {
	private int lvl;

	public Dungeon() {
		super(RenderableHolder.dungeonBg, SceneManeger.WIDGTH, SceneManeger.HEIGHT-Navigation.NAVIG_HEIGHT,
				new Pair(0,0));
		this.lvl = 1;
		this.z=-99999;
	}
	
	public boolean isInBoarder(Entity e,double x,double y) {
		return (0<=x && x<=this.width-e.getWidth()/2-8) && ( 0<=y && y<=this.height-e.getH());
	}
	public void update() {
		
		
	}

}
