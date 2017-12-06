package view.dungeon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import model.entity.Hero;
import model.field.Navigation;
import model.field.Shop;
import model.items.Health;
import model.items.Inventory;
import model.items.Mana;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class DialogPane extends VBox {
	private ImageView potion1;
	private ImageView potion2;
	private ImageView sword;
	private ImageView bow;
	private ImageView staff;
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
		potion1 = new ImageView(RenderableHolder.hpPotion);
		potion2 = new ImageView(RenderableHolder.mpPotion);
		sword = new ImageView(RenderableHolder.sword);
		bow = new ImageView(RenderableHolder.bow);
		staff = new ImageView(RenderableHolder.staff);
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(100,10,10,10));
		gp.setHgap(5);
		gp.setVgap(20);
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
		Button use = new Button("USE");
		use.setStyle("-fx-color: red;-fx-border: none");
		
		gp.add(t1, 1, 3,4,4);
		gp.add(t2, 1, 6,4,4);
		gp.add(t3, 1, 9,2,2);
		gp.add(use, 3, 9,2,2);
		
		potion1.setOnMouseClicked((MouseEvent e) -> {
			if (((Health)Inventory.getBag()[0]).getAmount() != 0) {
				use.setOnMouseClicked((MouseEvent event0) -> {
					((Health)Inventory.getBag()[0]).use();
				});
			}
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : 500 g");
			t3.setText("Amount : "+ ((Health)Inventory.getBag()[0]).getAmount());
		});
		
		potion2.setOnMouseClicked((MouseEvent e) -> {
			if (((Mana)Inventory.getBag()[1]).getAmount() != 0) {
				use.setOnMouseClicked((MouseEvent event1) -> {
					((Mana)Inventory.getBag()[1]).use();
				});
			}
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : 500 g");
			t3.setText("Amount : " + ((Mana)Inventory.getBag()[1]).getAmount());
		});
		
		sword.setOnMouseClicked((MouseEvent e) -> {
			if (((Weapons)Inventory.getBag()[2]).getAmount() == 1) {
				use.setOnMouseClicked((MouseEvent event0) -> {
					((Weapons)Inventory.getBag()[2]).use();
				});
			}
			t1.setText("Sword\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : " + ((Weapons)Inventory.getBag()[2]).getAmount());
		});
		
		bow.setOnMouseClicked((MouseEvent e) -> {
			if (((Weapons)Inventory.getBag()[3]).getAmount() == 1) {
				use.setOnMouseClicked((MouseEvent event0) -> {
					((Weapons)Inventory.getBag()[3]).use();
				});
			}
			t1.setText("Bow\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : " + ((Weapons)Inventory.getBag()[3]).getAmount());
		});
		
		staff.setOnMouseClicked((MouseEvent e) -> {
			if (((Weapons)Inventory.getBag()[4]).getAmount() == 1) {
				use.setOnMouseClicked((MouseEvent event0) -> {
					((Weapons)Inventory.getBag()[4]).use();
				});
			}
			t1.setText("Staff\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : "  + ((Weapons)Inventory.getBag()[4]).getAmount());
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
		buy.setStyle("-fx-color: red;-fx-border: none");
		
		gp.add(t1, 1, 3,4,4);
		gp.add(t2, 1, 5,4,4);
		gp.add(buy, 3, 9,2,2);
		
		potion1.setOnMouseClicked((MouseEvent e) -> {
			buy.setOnMouseClicked((MouseEvent event0)->{
				shop.buy(0);
			});
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : " + ((Health)Inventory.getBag()[0]).getPrice() + " g");
		});
		
		potion2.setOnMouseClicked((MouseEvent e) -> {
			buy.setOnMouseClicked((MouseEvent event1)->{
				shop.buy(1);
			});
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : " + ((Mana)Inventory.getBag()[1]).getPrice() + " g");
		});
		
		sword.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Sword\n");
			t2.setText("Price : " + ((Weapons)Inventory.getBag()[2]).getPrice() + " g");
		});
		
		bow.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Bow\n");
			t2.setText("Price : " + ((Weapons)Inventory.getBag()[3]).getPrice() + " g");
		});
		
		staff.setOnMouseClicked((MouseEvent e) -> {
			t1.setText("Staff\n");
			t2.setText("Price : " + ((Weapons)Inventory.getBag()[4]).getPrice() + " g");
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
