package model.field;

import java.awt.Event;
import java.awt.GradientPaint;
import java.awt.MouseInfo;

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
	public static final double NAVIG_HEIGHT = RenderableHolder.navigBar.getHeight()*1.5;
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
		super.draw(gc);
		
		//font width and height
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double hpWidth = fontLoader.computeStringWidth("HP", gc.getFont());
		double mpWidth = fontLoader.computeStringWidth("MP", gc.getFont()); 
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		
		//set color
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		
		//draw icons
		gc.fillRect(25, sceneHeight - ICON_SIZE - 25 , ICON_SIZE, ICON_SIZE);
		
		
		gc.drawImage(RenderableHolder.settingIcn, sceneWidth - SETTING - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - SETTING - 25  , sceneHeight - SETTING - 40, SETTING, SETTING);
		
		gc.drawImage(RenderableHolder.weaponIcn, sceneWidth - 25 - SETTING*2 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*2 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
		gc.drawImage(RenderableHolder.itemIcn, sceneWidth - 25 - SETTING*3 - 25 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*3 - 25 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
		gc.drawImage(RenderableHolder.questIcn, sceneWidth - 25 - SETTING*4 - 25 - 25 - 25, sceneHeight - SETTING - 40);
//		gc.fillRect(sceneWidth - 25 - SETTING*4 - 25 - 25 - 25, sceneHeight - SETTING - 40, SETTING, SETTING);
		
		//name 
		gc.setFont(Font.font("Castellar",25));
		gc.fillText(name, BORDER_WIDTH + 2, sceneHeight - BAR_HEIGHT * 3.5);
		
		gc.setFont(Font.font("Castellar",20));
		//Hp
		gc.fillText("HP", BORDER_WIDTH - hpWidth - 10, sceneHeight - BAR_HEIGHT * 3 + fontHieght);
		gc.fillRect(BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 3, BAR_WIDTH, BAR_HEIGHT);
		//Mp
		gc.fillText("MP", BORDER_WIDTH - mpWidth - 10, sceneHeight - BAR_HEIGHT * 1.5 + fontHieght);
		gc.fillRect(BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 1.5, BAR_WIDTH, BAR_HEIGHT);
		
		//Hp and Mp gauge
		gc.setFill(Color.RED);
		gc.setGlobalAlpha(0.7);
		gc.fillRect(BORDER_WIDTH+2, sceneHeight - (BAR_HEIGHT * 3) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(BORDER_WIDTH+2, sceneHeight - (BAR_HEIGHT * 1.5) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setGlobalAlpha(1.0);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		int xPos = MouseInfo.getPointerInfo().getLocation().x;
		int yPos = MouseInfo.getPointerInfo().getLocation().y;
		if (xPos >= 709 && xPos <= 772 && yPos >= 680 && yPos <= 740) {
			System.out.println("1");
			SceneManeger.dungeonScene.toDialog(1);
		}
		else if (xPos >= 804 && xPos <= 865 && yPos >= 680 && yPos <= 740 ) {
			System.out.println("2");
			SceneManeger.dungeonScene.toDialog(2);
		}
		else if (xPos >= 900 && xPos <= 963 && yPos >= 680 && yPos <= 740) {
			System.out.println("3");
			SceneManeger.dungeonScene.toDialog(3);
		}
		else if (xPos >= 995 && xPos <= 1055 && yPos >= 680 && yPos <= 740) {
			System.out.println("4");
			SceneManeger.dungeonScene.toDialog(4);
		}
	}
	
	
}
