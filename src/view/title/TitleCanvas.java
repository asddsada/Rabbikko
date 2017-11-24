package view.title;

import javax.swing.GroupLayout.Alignment;

import Main.DungeonMain;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sharedObj.RenderableHolder;
import view.SceneManeger;

public class TitleCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;

	public TitleCanvas() {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		gc = this.getGraphicsContext2D();

		this.drawMainMenu();
		this.addKeyEventHandler();
	}

	private void drawMainMenu() {
		// draw bg
		gc.drawImage(RenderableHolder.mainImage, 0, 0, SceneManeger.WIDGTH, SceneManeger.HEIGHT);

		// draw title
		gc.setFill(Color.WHITE);
		f = Font.font("Castellar", 90);
		gc.setFont(f);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("RABBIKKO RPG", SceneManeger.WIDGTH / 2, SceneManeger.HEIGHT / 3);

		// draw btn
		gc.drawImage(RenderableHolder.mainBtnImage, SceneManeger.WIDGTH / 3, SceneManeger.HEIGHT / 2);

		// draw credit text
		f = Font.font("Century Schoolbook", 20);
		gc.setFont(f);
		gc.fillText("Credit : 5930188521 (Natthawan) , 5931044021 (Penpicha)", SceneManeger.WIDGTH / 2,
				SceneManeger.HEIGHT / 4 * 3);
	}

	private void onButton(MouseEvent event,boolean isGoNext) {
		if (event.getSceneX() >= SceneManeger.WIDGTH / 3 && event.getSceneX() <= SceneManeger.WIDGTH / 3 + 300
				&& event.getSceneY() >= SceneManeger.HEIGHT / 2 && event.getSceneY() <= SceneManeger.HEIGHT / 2 + 87) {
			if(isGoNext) {
				//RenderableHolder.clickSound.play(50);
				DungeonMain dunMain = new DungeonMain();
				SceneManeger.gotoScene(SceneManeger.dungeonScene);
			}else {
				gc.setStroke(Color.GHOSTWHITE);
				gc.setLineWidth(2);
				gc.strokeRect(SceneManeger.WIDGTH / 3, SceneManeger.HEIGHT / 2, 300, 87);
			}
		} else {
			drawMainMenu();
		}
	}

	// listener method
	private void addKeyEventHandler() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					Platform.exit();
				}
				if (event.getCode() == KeyCode.ENTER) {
					// change scene
					System.out.println("Enter!");
				}
			}
		});

		setOnMouseMoved((MouseEvent event) -> {onButton(event,false);});
		setOnMouseClicked((MouseEvent event) -> {onButton(event,true);});		
		setOnMouseDragEntered((MouseEvent event)->{onButton(event,true);});
	}

}
