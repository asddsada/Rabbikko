package view.dungeon;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
import model.field.Navigation;
import sharedObj.RenderableHolder;
import view.SceneManeger;

public class DialogPane extends VBox {
	private DungeonScene scene;
	
	public DialogPane(DungeonScene dunScene){
		super(10);
		this.scene = dunScene;
		
		this.setMaxSize(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.setAlignment(Pos.CENTER);
		
		//this.setStyle("-fx-background-image : white");
		this.setBackground(new Background( new BackgroundImage(RenderableHolder.dialogFrame,
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT)));	
	}
	
	public void opening() {
		Text head = new Text("Welcome!");
		head.setFont(Font.font("Castellar",40));
		
		Text sub = new Text("Please enter your name");
		sub.setFont(Font.font("Castellar",20));
		
		TextField textField = new TextField("");
		textField.setFont(RenderableHolder.diaLogFont);
		textField.setMaxWidth(SceneManeger.WIDGTH/3);
		textField.setPrefHeight(SceneManeger.HEIGHT/10);
		textField.setFont(Font.font("Castellar",20));
		
		//set textfiled style
		textField.setStyle("-fx-border-color: transparent;");
		textField.setStyle("-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1");
		
		//press esc to clear textfiled
		textField.setOnKeyPressed((KeyEvent e)->{
			if (e.getCode() == KeyCode.ESCAPE) {
				textField.clear();
			}
		});
		
        ImageView okButton = new ImageView(RenderableHolder.dialogBtnImage);
       
        Button okBtn = new Button("OK");
        okBtn.setFont(Font.font("Castellar",25));
        
        
		this.getChildren().addAll(head,sub,textField,okBtn);
		
		okBtn.setOnMouseClicked((MouseEvent e)->{
			//RenderableHolder.titleBgm.stop();
			RenderableHolder.clickSound.play(100);
			openAction(textField);
		});
		
		this.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode()==KeyCode.ENTER) {
				openAction(textField);
			}else if (e.getCode()==KeyCode.ESCAPE) {
				textField.setText("");
			}
		});
	}
	private void openAction(TextField textField) {
//		check if player has entered their name
//		if (textField.getText().trim().isEmpty()) {
//			textField.setPromptText("Please enter your name!!!");
//		}
//		else {
			
			Navigation.setName(textField.getText().trim());
			scene.toDungeon();
			
//		}
	}
	
	public void item() {
		Button close = new Button("Close");
		this.getChildren().add(close);
		close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.clickSound.play();
				scene.toDungeon();
			}
		});
	}
	
	public void weapon() {
		Button close = new Button("Close");
		this.getChildren().add(close);
		close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.clickSound.play();
				scene.toDungeon();
			}
		});
	}
	
	public void setting() {
		Button close = new Button("Close");
		this.getChildren().add(close);
		close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.clickSound.play();
				scene.toDungeon();
			}
		});
	}
	
	public void menu() {
		Button item = new Button("Item");
		Button weapon = new Button("Weapons");
		Button setting = new Button("Setting");
		Button close = new Button("Close");
		this.getChildren().addAll(item,weapon,setting,close);
		
		item.setOnMouseClicked((MouseEvent e)->{
			scene.toDialog(1);
		});
		
		weapon.setOnMouseClicked((MouseEvent e)->{
			scene.toDialog(2);
		});
		
		setting.setOnMouseClicked((MouseEvent e)->{
			scene.toDialog(3);
		});
		
		close.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				RenderableHolder.clickSound.play();
				scene.toDungeon();
			}
		});
	}
}
