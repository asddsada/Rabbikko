package logic;

import java.util.ArrayList;
import java.util.List;

import model.attribute.strength;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.field.Navigation;
import sharedObj.RenderableHolder;
import view.dungeon.DialogPane;

public class GameLogic {
	//constructor  to initialize obj
	//local container with add method
	//start (+stop) loop -> update
	private static List<Entity> localContainer;
	public static Dungeon dungeon;
	public static Hero hero;
	private Navigation navig;
	
	public GameLogic(){
		this.localContainer = new ArrayList<Entity>();
		dungeon = new Dungeon();
		
		hero = new Hero(Entity.FRONT, new strength());
		addNewObject(hero);
		
		RenderableHolder.getInstance().add(dungeon);
		navig = new Navigation();
		RenderableHolder.getInstance().add(navig);		
	}
	
	protected void addNewObject(Entity entity){
		localContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		dungeon.update();
		for(Entity entity:localContainer) {
			entity.update();			
		}
	}
}
