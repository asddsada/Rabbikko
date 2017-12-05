package model.field;

import java.awt.Event;
import java.awt.GradientPaint;
import java.awt.MouseInfo;

import javax.swing.plaf.basic.BasicDesktopIconUI.MouseInputHandler;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import model.entity.Hero;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;

public class Navigation extends Field {
	public static final double NAVIG_WIDTH = RenderableHolder.navigBar.getWidth();
	public static final double NAVIG_HEIGHT = RenderableHolder.navigBar.getHeight();
	public static final int BAR_WIDTH = 400;
	public static final int BAR_HEIGHT = 30;
	public static final int BORDER_WIDTH = 200;
	public static final int ICON_SIZE = 100;
	public static final int SETTING = 70;
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
		
		//font width and height
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double hpWidth = fontLoader.computeStringWidth("HP", gc.getFont());
		double mpWidth = fontLoader.computeStringWidth("MP", gc.getFont()); 
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		
		//name 
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar",25));
		gc.fillText(name, BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 3.5);
		
		
		//set color
//		gc.setFill(Color.BLACK);
//		gc.setStroke(Color.BLACK);
		
		//draw icons
//		gc.drawImage(RenderableHolder.atSlot, 25, sceneHeight - ICON_SIZE - 25);
//		gc.fillRect(25, sceneHeight - ICON_SIZE - 25 , ICON_SIZE, ICON_SIZE);
		
		
//		gc.drawImage(RenderableHolder.menuIcn, sceneWidth - SETTING - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - SETTING - 25  , sceneHeight - SETTING - 40, SETTING, SETTING);
		
//		gc.drawImage(RenderableHolder.slot, sceneWidth - 25 - SETTING*2 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*2 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
//		gc.drawImage(RenderableHolder.slot, sceneWidth - 25 - SETTING*3 - 25 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*3 - 25 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
//		gc.drawImage(RenderableHolder.slot, sceneWidth - 25 - SETTING*4 - 25 - 25 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*4 - 25 - 25 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
//		gc.setFont(Font.font("Castellar",20));
		
		//Hp Mp
//		gc.fillText("HP", BORDER_WIDTH - hpWidth - 10, sceneHeight - BAR_HEIGHT * 3 + fontHieght);
//		gc.fillText("MP", BORDER_WIDTH - mpWidth - 10, sceneHeight - BAR_HEIGHT * 1.5 + fontHieght);
		
//		gc.setFill(Color.BLACK);
//		gc.fillRect(BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 3, BAR_WIDTH, BAR_HEIGHT);
//		gc.fillRect(BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 1.5, BAR_WIDTH, BAR_HEIGHT);
//		
//		//Hp and Mp gauge
//		gc.setFill(Color.RED);
//		gc.setGlobalAlpha(0.7);
//		gc.fillRect(BORDER_WIDTH+2, sceneHeight - (BAR_HEIGHT * 3) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
//		gc.setFill(Color.DODGERBLUE);
//		gc.fillRect(BORDER_WIDTH+2, sceneHeight - (BAR_HEIGHT * 1.5) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
//		gc.setGlobalAlpha(1.0);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;
//		if (xPos >= 623 && xPos <= 685 && yPos >= 590 && yPos <= 660 && InputUtility.isMousePressed()) {
//			RenderableHolder.clickSound.play(100);
//			SceneManeger.dungeonScene.toDialog(1);
//		}
//		else if (xPos >= 715 && xPos <= 780 && yPos >= 590 && yPos <= 660 && InputUtility.isMousePressed()) {
//			RenderableHolder.clickSound.play(100);
//			SceneManeger.dungeonScene.toDialog(2);
//		}
//		else if (xPos >= 810 && xPos <= 878 && yPos >= 590 && yPos <= 660 && InputUtility.isMousePressed()) {
//			RenderableHolder.clickSound.play(100);
//			SceneManeger.dungeonScene.toDialog(3);
//		}
		if (xPos >= 905 && xPos <= 973 && yPos >= 590 && yPos <= 660 && InputUtility.isMousePressed()) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(4);
		}
	}
	
	
}
