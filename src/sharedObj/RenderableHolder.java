package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static Font f;
	
	public static Image mainImage;
	public static Image mainBtnImage;
	public static Image dialogImage;
	public static Image dialogBtnImage;
	public static Image dialogFrame;

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
		mainImage = new Image("file:res/img/titleScene.jpg");
		mainBtnImage = new Image("file:res/img/newButton.png");
		dialogImage = new Image("file:res/img/dialogScene.jpg");
		dialogBtnImage = new Image("file:res/img/okButton.png");
		dialogFrame = new Image("file:res/img/dialogFrame.png");
		
		f = Font.font("Castellar");
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
