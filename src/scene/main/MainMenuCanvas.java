package scene.main;


import drawing.SceneManeger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObj.RenderableHolder;

public class MainMenuCanvas extends Canvas {
	private GraphicsContext gc;
	private Font f;
	
	public MainMenuCanvas() {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		gc = this.getGraphicsContext2D();
		
		this.drawMainMenu();		
		this.addKeyEventHandler();
	}
	
	private void drawMainMenu() {
		
		//draw bg
		gc.drawImage(RenderableHolder.mainImage, 0, 0,1280,780);
		
		//draw title
		gc.setFill(Color.WHITE);
		f = Font.font("Castellar",FontWeight.BOLD,90);
		gc.setFont(f);
		gc.fillText("RABBIKKO RPG",260,300);					
		
		//draw btn
		gc.drawImage(RenderableHolder.mainBtnImage, 460, 350);
		
		//draw credit text
		f = Font.font("Century Schoolbook",20);
		gc.setFont(f);
		gc.fillText("Credit : 5930188521 (Natthawan) , 5931044021 (Penpicha)",345,500);
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
					System.out.println("Enter!");
				}
			}
		});
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
//				System.out.println(event.getSceneX());
//				System.out.println(event.getSceneY());
				if (event.getSceneX() >= 465.0 && event.getSceneX() <= 754.0
					&& event.getSceneY() >= 351.0 && event.getSceneY() <= 436.0 ) {
						gc.setStroke(Color.RED);
						gc.setLineWidth(5);
						gc.strokeRect(460, 350, 300, 87);
				}
				else {
					drawMainMenu();
				}
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getSceneX() >= 465.0 && event.getSceneX() <= 754.0) {
					if (event.getSceneY() >= 351.0 && event.getSceneY() <= 436.0 ) {
						gc.setStroke(Color.SILVER);
						gc.setLineWidth(5);
						gc.strokeRect(460, 350, 300, 87);
						
						//go to dungeon scene
					}	
				}
			}
		});
	}

}
