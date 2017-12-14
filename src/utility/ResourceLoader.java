package utility;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public abstract class ResourceLoader {

	public static Font diaLogFont = Font.font("Castellar");
	public static FontLoader fontLoader;

	public static Image mainImage;
	public static Image mainBtnImage;
	public static Image dialogFrame;
	public static Image dungeonBg;
	public static Image humanImage;
	public static Image[] monsterImage = new Image[5];

	// Navigation
	public static Image navigBar;
	public static Image inven;
	public static Image shop;
	public static Image setting;
	public static Image dead;

	// Item
	public static Image hpPotion;
	public static Image mpPotion;
	public static Image sword;
	public static Image sword2;
	public static Image bow;
	public static Image bow2;
	public static Image bow3;
	public static Image staff;
	public static Image staff2;

	public static Image sEffect;
	public static Image sEffect2;
	public static Image aEffect;
	public static Image aEffect2;
	public static Image aEffect3;
	public static Image aEffect4;
	public static Image mEffect;
	public static Image monsterEffect;

	public static Image strength;
	public static Image agility;
	public static Image intelligence;

	// BGM
	public static AudioClip clickSound;
	public static Media titleBgm;
	public static AudioClip coin;
	public static AudioClip heal;

	public static Thread loadThread;

	static {
		ResourceLoader.loadResource();
		initializeSecondLoad();
	}

	public static void loadResource() {
		try {
			fontLoader = Toolkit.getToolkit().getFontLoader();

			// res for ui
			mainImage = new Image(ClassLoader.getSystemResource("img/titleScene.jpg").toString());
			mainBtnImage = new Image(ClassLoader.getSystemResource("img/newButton.png").toString());
			dialogFrame = new Image(ClassLoader.getSystemResource("img/dialogFrame.png").toString());

			// Navigation
			navigBar = new Image(ClassLoader.getSystemResource("img/navig.png").toString());
			inven = new Image(ClassLoader.getSystemResource("img/inventory.png").toString());
			shop = new Image(ClassLoader.getSystemResource("img/shop.png").toString());
			setting = new Image(ClassLoader.getSystemResource("img/setting2.png").toString());
			dead = new Image(ClassLoader.getSystemResource("img/dead.png").toString());

			// Item
			strength = new Image(ClassLoader.getSystemResource("img/strength.png").toString());
			agility = new Image(ClassLoader.getSystemResource("img/agility.png").toString());
			intelligence = new Image(ClassLoader.getSystemResource("img/intelligence.png").toString());

			// res for game
			dungeonBg = new Image(ClassLoader.getSystemResource("img/dunBg2.png").toString());
			humanImage = new Image(ClassLoader.getSystemResource("8bit/human1.png").toString());

			// BGM
			clickSound = new AudioClip(ClassLoader.getSystemResource("snd/click.mp3").toString());
			titleBgm = new Media(ClassLoader.getSystemResource("snd/titlebgm.mp3").toString());
			coin = new AudioClip(ClassLoader.getSystemResource("snd/coin.mp3").toString());
			heal = new AudioClip(ClassLoader.getSystemResource("snd/heal.wav").toString());

		} catch (NullPointerException e) {
			System.out.println("resource not found");
		}
	}
	
	public static void initializeSecondLoad() {

		loadThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					hpPotion = new Image(ClassLoader.getSystemResource("img/hpPotion.png").toString());
					mpPotion = new Image(ClassLoader.getSystemResource("img/mpPotion.png").toString());
					sword = new Image(ClassLoader.getSystemResource("img/sword.png").toString());
					sword2 = new Image(ClassLoader.getSystemResource("img/sword2.png").toString());
					bow = new Image(ClassLoader.getSystemResource("img/bow.png").toString());
					bow2 = new Image(ClassLoader.getSystemResource("img/bow2.png").toString());
					bow3 = new Image(ClassLoader.getSystemResource("img/bow3.png").toString());
					staff = new Image(ClassLoader.getSystemResource("img/staff.png").toString());
					staff2 = new Image(ClassLoader.getSystemResource("img/staff2.png").toString());

					sEffect = new Image(ClassLoader.getSystemResource("img/sEffect.gif").toString());
					sEffect2 = new Image(ClassLoader.getSystemResource("img/sEffect2.gif").toString());
					aEffect = new Image(ClassLoader.getSystemResource("img/aEffect.gif").toString());
					aEffect2 = new Image(ClassLoader.getSystemResource("img/aEffect2.gif").toString());
					aEffect3 = new Image(ClassLoader.getSystemResource("img/aEffect3.gif").toString());
					aEffect4 = new Image(ClassLoader.getSystemResource("img/aEffect4.gif").toString());
					mEffect = new Image(ClassLoader.getSystemResource("img/mEffect.gif").toString());
					monsterEffect = new Image(ClassLoader.getSystemResource("img/monsterAttack.gif").toString());
					
					for(int i=0;i<5;i++) {
					monsterImage[i] = new Image(ClassLoader.getSystemResource("8bit/monster"+Integer.toString(i+1)+".png").toString());
					}
				}catch (NullPointerException e) {
					System.out.println("resource not found");
				}
			}
		});
		loadThread.setPriority(Thread.MAX_PRIORITY);		
	}
	
	public static void startSecondLoad() {
		try {
			loadThread.start();
		} catch (NullPointerException e) {
			System.out.println("not initialize");
		}
	}

	public static boolean isLoadFinish() {
		return !(loadThread.isAlive());
	}
}
