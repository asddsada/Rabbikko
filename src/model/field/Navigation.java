package model.field;

import javafx.scene.image.Image;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Navigation extends Field {
	public static final double NAVIG_WIDTH = RenderableHolder.navigBar.getWidth();
	public static final double NAVIG_HEIGHT = RenderableHolder.navigBar.getHeight();

	public Navigation() {
		super(RenderableHolder.navigBar, RenderableHolder.navigBar.getWidth(), RenderableHolder.navigBar.getHeight(), new Pair(0,SceneManeger.HEIGHT-NAVIG_HEIGHT));
	}

	@Override
	public boolean isInBorder(Pair p) {
		return false;
	}
	

}
