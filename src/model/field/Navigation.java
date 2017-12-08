package model.field;

import java.awt.Event;
import java.awt.GradientPaint;
import java.awt.MouseInfo;

import javax.swing.plaf.basic.BasicDesktopIconUI.MouseInputHandler;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.effect.DropShadow;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import model.entity.Hero;
import model.items.Inventory;
import model.items.Item;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class Navigation extends Field {
	public static final double NAVIG_WIDTH = RenderableHolder.navigBar.getWidth();
	public static final double NAVIG_HEIGHT = RenderableHolder.navigBar.getHeight();
	public static final int BAR_HEIGHT = 30;
	public static final int BORDER_WIDTH = 200;
	public static final double sceneWidth = SceneManeger.WIDGTH;
	public static final double sceneHeight = SceneManeger.HEIGHT;
	
	private static String name;

	public Navigation() {
		super(RenderableHolder.navigBar, RenderableHolder.navigBar.getWidth(), RenderableHolder.navigBar.getHeight(), new Pair(0,sceneHeight-NAVIG_HEIGHT));
		this.z=2000;
	}

	public static void setName(String name) {
		Navigation.name = name;
	}

	public static String getName() {
		return name;
	}

	@Override
	public boolean isInBorderX(double x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInBorderY(double y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.navigBar,0,sceneHeight-NAVIG_HEIGHT,NAVIG_WIDTH,NAVIG_HEIGHT);
		
		gc.drawImage(RenderableHolder.hpPotion,481, 571);
		gc.drawImage(RenderableHolder.mpPotion, 541, 571);
		
		gc.setFont(Font.font("Castellar",15));
		gc.fillText(Integer.toString(((Item)Inventory.getBag()[0]).getAmount()), 481, 571);
		gc.fillText(Integer.toString(((Item)Inventory.getBag()[1]).getAmount()), 541, 571);
		
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		
		//name 
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar",25));
		gc.fillText(name, BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 3.5);
		
		//money
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar",20));
		gc.fillText(Integer.toString(GameLogic.hero.getMoney()),870,sceneHeight-fontHieght-10);
		
		//Hp and Mp
		double maxH = GameLogic.hero.getMaxHp();
		double currentH = GameLogic.hero.getCurrentHp();
		double maxM = GameLogic.hero.getMaxMp();
		double currentM = GameLogic.hero.getCurrentMp();
		
		gc.setFill(Color.CRIMSON);
		gc.fillRect(241,613.5,(currentH/maxH)*200,18);
		
		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(241, 653,(currentM/maxM)*200, 18);
		
		gc.drawImage(GameLogic.hero.getAtkType().getImage(),51,583,78,80);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if (xPos >= 704 && xPos <= 741 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick() ) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(1);
		}
		else if (xPos >= 762 && xPos <= 800 && yPos >=575 && yPos <= 615 && InputUtility.isMouseClick()) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(2);
		}	
		else if (xPos >= 823 && xPos <= 861 && yPos >= 575 && yPos <= 615 && InputUtility.isMouseClick()) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(3);
		}
		else if (xPos >= 484 && xPos <= 527 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			RenderableHolder.clickSound.play(100);
			Inventory.getBag()[0].use();
		}
		else if (xPos >= 545 && xPos <= 585 && yPos >= 576 && yPos <= 615 && InputUtility.isMouseClick()) {
			RenderableHolder.clickSound.play(100);
			Inventory.getBag()[1].use();
		}
	}
	
	
}
