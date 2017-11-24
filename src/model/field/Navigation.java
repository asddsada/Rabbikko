package model.field;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.entity.Hero;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Navigation extends Field {
	public static final double NAVIG_WIDTH = RenderableHolder.navigBar.getWidth();
	public static final double NAVIG_HEIGHT = RenderableHolder.navigBar.getHeight()*1.5;
	public static final int BAR_WIDTH = 400;
	public static final int BAR_HEIGHT = 30;
	public static final int BORDER_WIDTH = 150;
	public static final int ICON_SIZE = 100;
	public static final int SETTING = 70;
	public Hero hero;

	public Navigation(Hero hero) {
		super(RenderableHolder.navigBar, RenderableHolder.navigBar.getWidth(), RenderableHolder.navigBar.getHeight(), new Pair(0,SceneManeger.HEIGHT-NAVIG_HEIGHT));
	}

	@Override
	public boolean isInBorder(Pair p) {
		return false;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		gc.setFont(Font.font("Castellar",20));
		
		gc.fillRect(BORDER_WIDTH - ICON_SIZE - 25, SceneManeger.HEIGHT - ICON_SIZE - 25 , ICON_SIZE, ICON_SIZE);
		
		gc.fillRect(SceneManeger.WIDGTH - SETTING - 25  , SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*2 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*3 - 25 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*4 - 25 - 25 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		
		gc.fillText("HP", BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 4 -5);
		gc.fillText("MP", BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 2 -5);
		//Hp
		gc.fillRect(BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 4, BAR_WIDTH, BAR_HEIGHT);
		//Mp
		gc.fillRect(BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 2, BAR_WIDTH, BAR_HEIGHT);
		
		gc.setFill(Color.RED);
		gc.setGlobalAlpha(0.7);
		gc.fillRect(BORDER_WIDTH+2, SceneManeger.HEIGHT - (BAR_HEIGHT * 4) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(BORDER_WIDTH+2, SceneManeger.HEIGHT - (BAR_HEIGHT * 2) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setGlobalAlpha(1.0);
	}

}
