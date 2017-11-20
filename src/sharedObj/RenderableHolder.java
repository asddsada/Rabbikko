package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static Image mainImage;
	public static Image mainBtnImage;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}	

	public static void loadResource() {
		//load resource here
		//e.g. i = new Image(ClassLoader.getSystemResource("path.png").toString());
		mainImage = new Image("file:res/img/title_scene.jpg");
		mainBtnImage = new Image("file:res/img/new_button.png");
	}
	
	//add to container with sort
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
