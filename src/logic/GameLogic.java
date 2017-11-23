package logic;

import java.util.ArrayList;
import java.util.List;

import model.GameModel;
import model.attribute.strength;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.field.Navigation;
import sharedObj.RenderableHolder;

public class GameLogic {
	//constructor  to initialize obj
	//local container with add method
	//start (+stop) loop -> update
	private List<Entity> localContainer;
	private Dungeon dungeon;
	private Hero hero;
	private Navigation navig;
	
	public GameLogic(){
		this.localContainer = new ArrayList<Entity>();
		dungeon = new Dungeon();
		RenderableHolder.getInstance().add(dungeon);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);
		
		hero = new Hero(Entity.FRONT, new strength());
		addNewObject(hero);
	}
	
	protected void addNewObject(Entity entity){
		localContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		for(Entity entity:localContainer) {
			entity.update();
			
		}
	}
}
