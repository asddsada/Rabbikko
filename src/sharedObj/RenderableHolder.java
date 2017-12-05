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
	public static Image navigBar;
	public static Image questIcn;
	public static Image itemIcn;
	public static Image weaponIcn;
	public static Image atSlot;
	public static Image slot;
	public static Image menuIcn;

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
			navigBar = new Image(ClassLoader.getSystemResource("img/newnavig.png").toString());
			atSlot = new Image(ClassLoader.getSystemResource("img/atSlot.png").toString());
			slot = new Image(ClassLoader.getSystemResource("img/slot.png").toString());
			questIcn = new Image(ClassLoader.getSystemResource("img/quest.png").toString());
			itemIcn = new Image(ClassLoader.getSystemResource("img/item.png").toString());
			weaponIcn = new Image(ClassLoader.getSystemResource("img/weapon.png").toString());
			menuIcn = new Image(ClassLoader.getSystemResource("img/setting.png").toString());

			clickSound = new AudioClip(ClassLoader.getSystemResource("snd/click.mp3").toString());
			titleBgm = new AudioClip(ClassLoader.getSystemResource("snd/titlebgm.mp3").toString());
			titleBgm.setCycleCount(MediaPlayer.INDEFINITE);

			// res for game
			dungeonBg = new Image(ClassLoader.getSystemResource("img/dunBg2.png").toString());

			humanImage = new Image(ClassLoader.getSystemResource("8bit/human1.png").toString());
			monsterImage02 = new Image(ClassLoader.getSystemResource("8bit/monster2.png").toString());

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
