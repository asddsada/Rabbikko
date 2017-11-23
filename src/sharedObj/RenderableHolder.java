package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import model.entity.Hero;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static Font diaLogFont = Font.font("Castellar");
	
	public static Image mainImage;
	public static Image mainBtnImage;
	public static Image dialogBtnImage;
	public static Image dialogFrame;
	public static Image dungeonBg;
	public static Image humanImage;
	//public static AudioClip clickSound;

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
		try {
			
			//res for ui
			mainImage = new Image(ClassLoader.getSystemResource("img/titleScene.jpg").toString());
			mainBtnImage = new Image(ClassLoader.getSystemResource("img/newButton.png").toString());
			
			//dialogBtnImage = new Image(ClassLoader.getSystemResource("img/okButton.png").toString());
			dialogFrame = new Image(ClassLoader.getSystemResource("img/dialogFrame.png").toString());			

			//clickSound = new AudioClip(ClassLoader.getSystemResource("snd/click.mp3").toString());
			
			//res for game
			dungeonBg = new Image(ClassLoader.getSystemResource("img/dunBg.png").toString());
			
			humanImage = new Image(ClassLoader.getSystemResource("8bit/human1.png").toString());	
			
					
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
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
