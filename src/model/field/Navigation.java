package model.field;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import model.entity.Hero;
import sharedObj.RenderableHolder;
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
	private static String name;

	public Navigation() {
		super(RenderableHolder.navigBar, RenderableHolder.navigBar.getWidth(), RenderableHolder.navigBar.getHeight(), new Pair(0,SceneManeger.HEIGHT-NAVIG_HEIGHT));
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
		
		//set color and font
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		gc.setFont(Font.font("Castellar",20));
		
		//draw icons
		gc.fillRect(25, SceneManeger.HEIGHT - ICON_SIZE - 25 , ICON_SIZE, ICON_SIZE);
		gc.fillRect(SceneManeger.WIDGTH - SETTING - 25  , SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*2 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*3 - 25 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		gc.fillRect(SceneManeger.WIDGTH - 25 - SETTING*4 - 25 - 25 - 25, SceneManeger.HEIGHT - SETTING - 40, SETTING, SETTING);
		
		//name 
		gc.fillText(name, BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 3);
		
		//Hp
		gc.fillText("HP", BORDER_WIDTH - hpWidth - 10, SceneManeger.HEIGHT - BAR_HEIGHT * 3 + fontHieght);
		gc.fillRect(BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 3, BAR_WIDTH, BAR_HEIGHT);
		//Mp
		gc.fillText("MP", BORDER_WIDTH - mpWidth - 10, SceneManeger.HEIGHT - BAR_HEIGHT * 1.5 + fontHieght);
		gc.fillRect(BORDER_WIDTH, SceneManeger.HEIGHT - BAR_HEIGHT * 1.5, BAR_WIDTH, BAR_HEIGHT);
		
		//Hp and Mp gauge
		gc.setFill(Color.RED);
		gc.setGlobalAlpha(0.7);
		gc.fillRect(BORDER_WIDTH+2, SceneManeger.HEIGHT - (BAR_HEIGHT * 3) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setFill(Color.DODGERBLUE);
		gc.fillRect(BORDER_WIDTH+2, SceneManeger.HEIGHT - (BAR_HEIGHT * 1.5) + 2 , BAR_WIDTH-4, BAR_HEIGHT-4);
		gc.setGlobalAlpha(1.0);
	}

}
