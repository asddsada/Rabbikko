package view.dungeon;


import java.awt.MouseInfo;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
import model.field.Navigation;
import model.field.Shop;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class DialogPane extends VBox {
	ImageView potion1 = new ImageView(RenderableHolder.hpPotion);
	ImageView potion2 = new ImageView(RenderableHolder.mpPotion);
	ImageView sword = new ImageView(RenderableHolder.sword);
	ImageView bow = new ImageView(RenderableHolder.bow);
	ImageView staff = new ImageView(RenderableHolder.staff);
	private DungeonScene scene;
	
	public DialogPane(DungeonScene dunScene){
		super(10);
		this.scene = dunScene;
		defaultDraw(scene,RenderableHolder.dialogFrame);
	}
	
	public void defaultDraw(DungeonScene dunScene,Image image) {
		this.setMaxSize(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.setAlignment(Pos.CENTER);

		this.setBackground(new Background( new BackgroundImage(image,
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
	
	public GridPane generate(Image img) {
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(100,10,10,10));
//		gp.setGridLinesVisible(true);
		gp.setHgap(5);
		gp.setVgap(20);
		gp.setPrefSize(img.getWidth(),img.getHeight());
		gp.setAlignment(Pos.TOP_CENTER);
		
		gp.add(potion1, 0, 1);
		gp.add(potion2, 1, 1);
		gp.add(sword, 2, 1);
		gp.add(bow, 3, 1);
		gp.add(staff, 4, 1);
		
		Button close = new Button("X");
		close.setStyle("-fx-color: red;-fx-border: none ");
		close.setOnMouseClicked((MouseEvent e) -> {
			scene.toDungeon();
		});
		gp.add(close,2,13);
		return gp;
	}

	public void inventory() {
		// TODO Auto-generated method stub
		defaultDraw(scene, RenderableHolder.inven);
		GridPane gp = generate(RenderableHolder.inven);
		
		Text t1 = new Text();
		t1.setFill(Color.ALICEBLUE);
		Text t2 = new Text();
		t2.setFill(Color.ALICEBLUE);
		Text t3 = new Text();
		t3.setFill(Color.ALICEBLUE);
		
		gp.add(t1, 1, 3,4,4);
		gp.add(t2, 1, 6,4,4);
		gp.add(t3, 1, 9,2,2);
		
		potion1.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : 500 g");
			t3.setText("Amount : ");
		});
		
		potion2.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : 500 g");
			t3.setText("Amount : ");
		});
		
		sword.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Sword\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : ");
		});
		
		bow.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Bow\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : ");
		});
		
		staff.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Staff\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : ");
		});
		this.getChildren().add(gp);
	}

	public void shop() {
		// TODO Auto-generated method stub
		defaultDraw(scene, RenderableHolder.shop);
		GridPane gp = generate(RenderableHolder.shop);
		Shop shop = new Shop();
		
		Text t1 = new Text();
		t1.setFill(Color.ALICEBLUE);
		Text t2 = new Text();
		t2.setFill(Color.ALICEBLUE);
		Button buy = new Button("BUY");
		buy.setDisable(true);
		buy.setStyle("-fx-color: red;-fx-border: none");
		
		gp.add(t1, 1, 3,4,4);
		gp.add(t2, 1, 5,4,4);
		gp.add(buy, 3, 9,2,2);
		
		potion1.setOnMouseClicked((MouseEvent e) -> {
			buy.setDisable(false);
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : 500 g");
		});
		
		potion2.setOnMouseClicked((MouseEvent e) -> {
			buy.setDisable(false);
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : 500 g");
		});
		
		sword.setOnMouseClicked((MouseEvent e) -> {
			buy.setDisable(false);
			t1.setText("Sword\n");
			t2.setText("Price : 5000 g");
		});
		
		bow.setOnMouseClicked((MouseEvent e) -> {
			buy.setDisable(false);
			t1.setText("Bow\n");
			t2.setText("Price : 5000 g");
		});
		
		staff.setOnMouseClicked((MouseEvent e) -> {
			buy.setDisable(false);
			t1.setText("Staff\n");
			t2.setText("Price : 5000 g");
		});
		
		this.getChildren().add(gp);
	}

	public void setting() {
		// TODO Auto-generated method stub
		defaultDraw(scene, RenderableHolder.setting);
		Button close = new Button("X");
		close.setStyle("-fx-color: red;-fx-border: none ");
		close.setOnMouseClicked((MouseEvent e) -> {
			scene.toDungeon();
		});
		
		this.getChildren().add(close);
	}
}
