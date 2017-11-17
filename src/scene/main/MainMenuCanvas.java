package scene.main;

import drawing.SceneManeger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainMenuCanvas extends Canvas {
	private GraphicsContext gc;
	
	public MainMenuCanvas() {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		
		gc = this.getGraphicsContext2D();
		//set gc
		
		this.addKeyEventHandler();
	}

	//listener method
	private void addKeyEventHandler() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					Platform.exit();
				}
				if (event.getCode() == KeyCode.ENTER) {
					//change scene
				}
			}
		});
	}

}
