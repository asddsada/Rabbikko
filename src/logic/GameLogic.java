package logic;

import model.attribute.Attribute;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.field.Navigation;
import sharedObj.RenderableHolder;
import utility.Constant;

public class GameLogic {
	public static Dungeon dungeon;
	public static Hero hero;
	public static Navigation navig;

	public GameLogic() {
		dungeon = new Dungeon();

		RenderableHolder.getInstance().add(dungeon);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);
	}
	
	public <T extends Attribute>void newHero(T atkType) {
		hero = new Hero(Constant.ENTITY_FRONT, atkType);
		Dungeon.addEntities(hero);
	}

	protected void addNewObject(Entity entity) {
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		dungeon.update();
		navig.update();
	}
}
