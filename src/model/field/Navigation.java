package model.field;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import model.entity.Entity;
import model.items.Health;
import model.items.Inventory;
import model.items.Item;
import model.items.Mana;
import utility.Constant;
import utility.InputUtility;
import utility.ResourceLoader;
import utility.Pair;
import view.SceneManeger;

public class Navigation extends Field {

	public static final double sceneWidth = Constant.SCENE_WIDTH;
	public static final double sceneHeight = Constant.SCENE_HEIGHT;

	private static String name;

	public Navigation() {
		super(ResourceLoader.navigBar, ResourceLoader.navigBar.getWidth(), ResourceLoader.navigBar.getHeight(),
				new Pair(0, sceneHeight - Constant.NAVIG_HEIGHT));
		this.z = 2000;
	}

	public static void setName(String name) {
		Navigation.name = name;
	}

	public static String getName() {
		return name;
	}

	public boolean isInBorder(double x, double y) {
		return isInBorderX(x) && isInBorderY(y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ResourceLoader.navigBar, 0, sceneHeight - Constant.NAVIG_HEIGHT, Constant.NAVIG_WIDTH, Constant.NAVIG_HEIGHT);
		if ((((Health) Inventory.getBag()[0]).getAmount()) != 0)
			gc.drawImage(ResourceLoader.hpPotion, 481, 571);
		if ((((Mana) Inventory.getBag()[1]).getAmount()) != 0)
			gc.drawImage(ResourceLoader.mpPotion, 541, 571);

		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar", 15));
		gc.fillText(Integer.toString(((Item) Inventory.getBag()[0]).getAmount()), 481, 571);
		gc.fillText(Integer.toString(((Item) Inventory.getBag()[1]).getAmount()), 541, 571);

		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();

		// name
		gc.setFont(Font.font("Castellar", 25));
		gc.fillText(name, Constant.NAVIG_BORDER_WIDTH, sceneHeight - Constant.NAVIG_BAR_HEIGHT * 3.5);

		// money
		gc.setFont(Font.font("Castellar", 20));
		gc.fillText(Integer.toString(GameLogic.hero.getMoney()), 870, sceneHeight - fontHieght - 10);

		// Hp and Mp
		double maxH = GameLogic.hero.getMaxHp();
		double currentH = GameLogic.hero.getCurrentHp();
		double maxM = GameLogic.hero.getMaxMp();
		double currentM = GameLogic.hero.getCurrentMp();

		gc.setFill(Color.CRIMSON);
		gc.fillRect(241, 613.5, (currentH / maxH) * 200, 18);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(241, 653, (currentM / maxM) * 200, 18);

		gc.drawImage(GameLogic.hero.getAtkType().getImage(), 51, 583, 78, 80);
	}

	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if ((xPos >= 704 && xPos <= 741 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.I))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(1);
		} else if ((xPos >= 762 && xPos <= 800 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.B))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(2);
		} else if ((xPos >= 823 && xPos <= 861 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick())
				|| (InputUtility.isKeyPressed(KeyCode.ESCAPE))) {
			InputUtility.mouseX = 0;
			InputUtility.mouseY = 0;
			ResourceLoader.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(3);
		} else if (xPos >= 484 && xPos <= 527 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			// Loader.clickSound.play(100);
			Inventory.getBag()[0].use();
		} else if (xPos >= 545 && xPos <= 585 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			// Loader.clickSound.play(100);
			Inventory.getBag()[1].use();
		}
	}

}
