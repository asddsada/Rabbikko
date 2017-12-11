package view.title;

import javax.swing.GroupLayout.Alignment;

import Main.DungeonMain;
import Main.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import utility.Constant;
import utility.ResourceLoader;
import view.SceneManeger;

public class TitleCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;

	public TitleCanvas() {
		super(Constant.WIDTH, Constant.HEIGHT);
		gc = this.getGraphicsContext2D();
		
		this.drawMainMenu();
		this.addKeyEventHandler();
	}

	private void drawMainMenu() {
		// draw bg
		gc.drawImage(ResourceLoader.mainImage,0, 0,Constant.WIDTH, Constant.HEIGHT);

		// draw title
		gc.setFill(Color.WHITE);
		f = Font.font("Castellar", 90);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("RABBIKKO RPG", Constant.WIDTH / 2, Constant.HEIGHT / 3);

		// draw btn
		gc.drawImage(ResourceLoader.mainBtnImage, Constant.WIDTH / 3, Constant.HEIGHT / 2);

		// draw credit text
		f = Font.font("Century Schoolbook", 20);
		gc.setFont(f);
		gc.fillText("Credit : 5930188521 (Natthawan) , 5931044021 (Penpicha)", Constant.WIDTH / 2,
				Constant.HEIGHT / 4 * 3);
	}

	private void goToDun() {
		ResourceLoader.clickSound.play(100);
		ResourceLoader.startSecondLoad();
		DungeonMain dunMain = new DungeonMain();		
		SceneManeger.gotoScene(SceneManeger.dungeonScene);
	}

	private void onButton(MouseEvent event, boolean isGoNext) {
		if (event.getSceneX() >= Constant.WIDTH / 3 && event.getSceneX() <= Constant.WIDTH / 3 + 300
				&& event.getSceneY() >= Constant.HEIGHT / 2 && event.getSceneY() <= Constant.HEIGHT / 2 + 87) {
			if (isGoNext) {				
				goToDun();
			} else {
				gc.drawImage(ResourceLoader.mainBtnImage, Constant.WIDTH / 3.05 , Constant.HEIGHT / 2 ,ResourceLoader.mainBtnImage.getWidth()*1.05,ResourceLoader.mainBtnImage.getHeight()*1.05);
			}
		} 
		else {
			drawMainMenu();
		}
	}

	private void addKeyEventHandler() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					DungeonMain.stop();
					Runtime.getRuntime().exit(0);
				}
				if (event.getCode() == KeyCode.ENTER) {
					goToDun();
				}
			}
		});

		setOnMouseMoved((MouseEvent event) -> {
			onButton(event, false);
		});
		setOnMouseClicked((MouseEvent event) -> {
			onButton(event, true);
		});
		setOnMouseDragEntered((MouseEvent event) -> {
			onButton(event, true);
		});
	}

}
