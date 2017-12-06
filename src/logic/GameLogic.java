package logic;

import java.util.ArrayList;
import java.util.List;

import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.field.Navigation;
import sharedObj.RenderableHolder;

public class GameLogic {
	// constructor to initialize obj
	// local container with add method
	// start (+stop) loop -> update
	public static Dungeon dungeon;
	public static Hero hero;
	private Navigation navig;

	public GameLogic() {
		dungeon = new Dungeon();

		hero = new Hero(Entity.FRONT, new Intelligence());
		RenderableHolder.getInstance().add(hero);
		dungeon.getENTITIES_HOLDER().add(hero);
		hero.setAtktype(new Strength());

		RenderableHolder.getInstance().add(dungeon);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);
	}

	protected void addNewObject(Entity entity) {
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		dungeon.update();
		navig.update();
	}
}
