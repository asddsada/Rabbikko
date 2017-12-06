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
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;

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
		
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double fontHieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		
		//name 
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar",25));
		gc.fillText(name, BORDER_WIDTH, sceneHeight - BAR_HEIGHT * 3.5);
		
		//money
		gc.setFill(Color.ALICEBLUE);
		gc.setFont(Font.font("Castellar",20));
		gc.fillText(Integer.toString(Hero.getMoney()),890,sceneHeight-fontHieght-7);
		
		//Hp and Mp
		gc.setFill(Color.CRIMSON);
		gc.fillRect(241,613.5,GameLogic.hero.getCurrentHp()/5,18);
		
		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(241, 653,GameLogic.hero.getCurrentMp()*0.4, 18);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		double xPos = InputUtility.mouseX;
		double yPos = InputUtility.mouseY;

		if (xPos >= 704 && xPos <= 741 && yPos >= 575 && yPos <= 615 && InputUtility.isMousePressed() ) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(1);
		}
		else if (xPos >= 762 && xPos <= 800 && yPos >=575 && yPos <= 615 && InputUtility.isMousePressed()) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(2);
		}	
		else if (xPos >= 823 && xPos <= 861 && yPos >= 575 && yPos <= 615 && InputUtility.isMousePressed()) {
			RenderableHolder.clickSound.play(100);
			SceneManeger.dungeonScene.toDialog(3);
		}
	}
	
	
}
