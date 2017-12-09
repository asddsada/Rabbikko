package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.text.Font;
import model.GameObject;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.ResourceLoader;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public static Font diaLogFont = Font.font("Castellar");

	static {
		ResourceLoader.loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	// add to container with sort
	public void add(IRenderable entity) {
		entities.add(entity);
	}

	public void sort() throws IllegalArgumentException{		
		entities.sort(comparator);	
	}
	
	public void clear() {
		for (int i = entities.size() - 1; i >= 0; i--) {			
			if (entities.get(i) instanceof GameObject)
				entities.remove(i);
		}
	}

	public void update() throws IllegalArgumentException {
		for (int i = entities.size() - 1; i >= 0; i--) {			
			if (entities.get(i).isDestroyed()) 
				entities.remove(i);
		}
		this.sort();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public int size() {
		return entities.size();
	}
}
