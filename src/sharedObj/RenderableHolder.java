package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
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
	public static Image monsterImage02;
	
	//Navigation
	public static Image navigBar;
	public static Image inven;
	public static Image shop;
	public static Image setting;
	public static Image menuIcn;
	
	//Item
	public static Image hpPotion;
	public static Image mpPotion;
	public static Image sword;
	public static Image sword2;
	public static Image bow;
	public static Image staff;
	
	//BGM
	public static AudioClip clickSound;
	public static AudioClip titleBgm;

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
			// res for ui
			mainImage = new Image(ClassLoader.getSystemResource("img/titleScene.jpg").toString());
			mainBtnImage = new Image(ClassLoader.getSystemResource("img/newButton.png").toString());
			dialogFrame = new Image(ClassLoader.getSystemResource("img/dialogFrame.png").toString());
			
			//Navigation
			navigBar = new Image(ClassLoader.getSystemResource("img/navig.png").toString());
			inven = new Image(ClassLoader.getSystemResource("img/inventory.png").toString());
			shop = new Image(ClassLoader.getSystemResource("img/shop.png").toString());
			setting = new Image(ClassLoader.getSystemResource("img/setting2.png").toString());
			menuIcn = new Image(ClassLoader.getSystemResource("img/setting.png").toString());
			
			//Item
			hpPotion = new Image(ClassLoader.getSystemResource("img/hpPotion.png").toString());
			mpPotion = new Image(ClassLoader.getSystemResource("img/mpPotion.png").toString());
			sword = new Image(ClassLoader.getSystemResource("img/sword.png").toString());
			sword2 = new Image(ClassLoader.getSystemResource("img/sword2.png").toString());
			bow = new Image(ClassLoader.getSystemResource("img/bow.png").toString());
			staff = new Image(ClassLoader.getSystemResource("img/staff.png").toString());
			
			// res for game
			dungeonBg = new Image(ClassLoader.getSystemResource("img/dunBg2.png").toString());
			
			humanImage = new Image(ClassLoader.getSystemResource("8bit/human1.png").toString());
			monsterImage02 = new Image(ClassLoader.getSystemResource("8bit/monster2.png").toString());
			
			//BGM
			clickSound = new AudioClip(ClassLoader.getSystemResource("snd/click.mp3").toString());
			titleBgm = new AudioClip(ClassLoader.getSystemResource("snd/titlebgm.mp3").toString());
			titleBgm.setCycleCount(MediaPlayer.INDEFINITE);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	// add to container with sort
	public void add(IRenderable entity) {
		// System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}
	
	public void sort() {
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
