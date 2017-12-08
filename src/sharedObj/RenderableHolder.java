package sharedObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

//	public static void loadResource() {
//		try {
//			// res for ui
//			mainImage = new Image(ClassLoader.getSystemResource("img/titleScene.jpg").toString());
//			mainBtnImage = new Image(ClassLoader.getSystemResource("img/newButton.png").toString());
//			dialogFrame = new Image(ClassLoader.getSystemResource("img/dialogFrame.png").toString());
//
//			// Navigation
//			navigBar = new Image(ClassLoader.getSystemResource("img/navig.png").toString());
//			inven = new Image(ClassLoader.getSystemResource("img/inventory.png").toString());
//			shop = new Image(ClassLoader.getSystemResource("img/shop.png").toString());
//			setting = new Image(ClassLoader.getSystemResource("img/setting2.png").toString());
////			menuIcn = new Image(ClassLoader.getSystemResource("img/setting.png").toString());
//			dead = new Image(ClassLoader.getSystemResource("img/dead.png").toString());
//
//			// Item
//			hpPotion = new Image(ClassLoader.getSystemResource("img/hpPotion.png").toString());
//			mpPotion = new Image(ClassLoader.getSystemResource("img/mpPotion.png").toString());
//			sword = new Image(ClassLoader.getSystemResource("img/sword.png").toString());
//			sword2 = new Image(ClassLoader.getSystemResource("img/sword2.png").toString());
//			bow = new Image(ClassLoader.getSystemResource("img/bow.png").toString());
//			bow2 = new Image(ClassLoader.getSystemResource("img/bow2.png").toString());
//			bow3 = new Image(ClassLoader.getSystemResource("img/bow3.png").toString());
//			staff = new Image(ClassLoader.getSystemResource("img/staff.png").toString());
//			staff2 = new Image(ClassLoader.getSystemResource("img/staff2.png").toString());
//			
//			sEffect = new Image(ClassLoader.getSystemResource("img/sEffect.gif").toString());
//			sEffect2 = new Image(ClassLoader.getSystemResource("img/sEffect2.gif").toString());
//			aEffect = new Image(ClassLoader.getSystemResource("img/aEffect.gif").toString());
//			aEffect2 = new Image(ClassLoader.getSystemResource("img/aEffect2.gif").toString());
//			aEffect3 = new Image(ClassLoader.getSystemResource("img/aEffect3.gif").toString());
//			aEffect4 = new Image(ClassLoader.getSystemResource("img/aEffect4.gif").toString());
//			mEffect = new Image(ClassLoader.getSystemResource("img/mEffect.gif").toString());
//			monsterEffect = new Image(ClassLoader.getSystemResource("img/monsterAttack.gif").toString());
//			
//			strength = new Image(ClassLoader.getSystemResource("img/strength.png").toString());
//			agility = new Image(ClassLoader.getSystemResource("img/agility.png").toString());
//			intelligence = new Image(ClassLoader.getSystemResource("img/intelligence.png").toString());
//		
//			// res for game
//			dungeonBg = new Image(ClassLoader.getSystemResource("img/dunBg2.png").toString());
//
//			humanImage = new Image(ClassLoader.getSystemResource("8bit/human1.png").toString());
//			monsterImage02 = new Image(ClassLoader.getSystemResource("8bit/monster2.png").toString());
//
//			// BGM
//			clickSound = new AudioClip(ClassLoader.getSystemResource("snd/click.mp3").toString());
//			titleBgm = new AudioClip(ClassLoader.getSystemResource("snd/titlebgm.mp3").toString());
//			titleBgm.setCycleCount(MediaPlayer.INDEFINITE);
//			coin = new AudioClip(ClassLoader.getSystemResource("snd/coin.mp3").toString());
//			heal = new AudioClip(ClassLoader.getSystemResource("snd/heal.wav").toString());
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//	}

	// add to container with sort
	public void add(IRenderable entity) {
		entities.add(entity);
	}

	public void sort() {
		
		Collections.sort(entities, comparator);
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
		Collections.sort(entities, comparator);
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
