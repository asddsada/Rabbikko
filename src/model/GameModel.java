package Model;

import sharedObj.RenderableHolder;

public class GameModel {
	public GameModel() {
		DungeonField field = new DungeonField();
		RenderableHolder.getInstance().add(field);
	}
}
