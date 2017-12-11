package view.dungeon;

import Main.DungeonMain;
import Main.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
import model.GameObject;
import model.attribute.Agility;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.Hero;
import model.field.Navigation;
import model.items.Health;
import model.items.Inventory;
import model.items.Item;
import model.items.Mana;
import model.items.Shop;
import model.items.Weapons;
import utility.InputUtility;
import utility.ResourceLoader;
import view.SceneManeger;

public class DialogPane extends VBox {
	private ImageView potion1;
	private ImageView potion2;
	private ImageView sword;
	private ImageView bow;
	private ImageView staff;
	private DungeonScene scene;

	public DialogPane(DungeonScene dunScene) {
		super(10);
		this.scene = dunScene;
		defaultDraw(scene, ResourceLoader.dialogFrame);
	}

	public void defaultDraw(DungeonScene dunScene, Image image) {
		this.setMaxSize(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.setAlignment(Pos.CENTER);

		this.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}

	public void opening() {
//		Text head = new Text("Welcome!");
//		head.setFont(Font.font("Castellar", 40));
//
		Text sub = new Text("Please enter your name");
		sub.setFont(Font.font("Castellar", 25));

		TextField textField = new TextField("");
		textField.setFont(ResourceLoader.diaLogFont);
		textField.setMaxWidth(SceneManeger.WIDGTH / 3);
		textField.setPrefHeight(SceneManeger.HEIGHT / 10);
		textField.setFont(Font.font("Castellar", 20));
		textField.setStyle("-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1");

		// press esc to clear textfiled
		textField.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				textField.clear();
			}
		});
		Button okBtn = new Button("OK");
		okBtn.setFont(Font.font("Castellar", 25));

		this.getChildren().addAll( sub, textField, okBtn);

		okBtn.setOnMouseClicked((MouseEvent e) -> {
			// Loader.titleBgm.stop();
			ResourceLoader.clickSound.play(100);
			openAction(textField);
		});

		this.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				openAction(textField);
			} else if (e.getCode() == KeyCode.ESCAPE) {
				textField.setText("");
			}
		});
	}
	
	public void openingNext() {
		Text head = new Text("Welcome! "+Navigation.getName());
		head.setFont(Font.font("Castellar", 30));

		Text sub = new Text("Please select your initial stat");
		sub.setFont(Font.font("Castellar", 20));

		Button sword = new Button("strength");
		sword.setFont(Font.font("Castellar", 20));

		sword.setOnMouseClicked((MouseEvent e) -> {
			// Loader.titleBgm.stop();
			ResourceLoader.clickSound.play(100);
			nextAction(new Strength(),Weapons.SWORD);
		});
		
		Button staff = new Button("int");
		staff.setFont(Font.font("Castellar", 20));		

		staff.setOnMouseClicked((MouseEvent e) -> {
			// Loader.titleBgm.stop();
			ResourceLoader.clickSound.play(100);
			nextAction(new Intelligence(),Weapons.STAFF);
		});
		
		Button bow = new Button("agi");
		bow.setFont(Font.font("Castellar", 20));

		bow.setOnMouseClicked((MouseEvent e) -> {
			// Loader.titleBgm.stop();
			ResourceLoader.clickSound.play(100);
			nextAction(new Agility(),Weapons.BOW);
		});
		
		this.getChildren().addAll(head, sub, sword,bow,staff);
	}
	
	private <T extends Attribute>void nextAction(T atkType,int i){
		DungeonMain.getLogic().newHero(atkType);
		GameLogic.hero.inventory.add(i);
		scene.toDungeon();
	}

	private void openAction(TextField textField) {
		// check if player has entered their name
		// if (textField.getText().trim().isEmpty()) {
		// textField.setPromptText("Please enter your name!!!");
		// }
		// else {
		Navigation.setName(textField.getText().trim());
		scene.toDialog(5);
		// }
	}

	public GridPane generate(Image img) {
		potion1 = new ImageView(ResourceLoader.hpPotion);
		potion2 = new ImageView(ResourceLoader.mpPotion);
		sword = new ImageView(ResourceLoader.sword);
		bow = new ImageView(ResourceLoader.bow);
		staff = new ImageView(ResourceLoader.staff);

		GridPane gp = new GridPane();
		gp.setPadding(new Insets(100, 10, 10, 10));
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
			this.getChildren().clear();
			ResourceLoader.clickSound.play(100);
			scene.toDungeon();
		});
		gp.add(close, 2, 13);
		return gp;
	}

	public void inventory() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.inven);
		GridPane gp = generate(ResourceLoader.inven);

		Text t1 = new Text();
		t1.setFill(Color.ALICEBLUE);
		Text t2 = new Text();
		t2.setFill(Color.ALICEBLUE);
		Text t3 = new Text();
		t3.setFill(Color.ALICEBLUE);
		Button use = new Button("USE");
		use.setDisable(true);
		use.setStyle("-fx-color: red;-fx-border: none");
		TextField money = new TextField();
		money.setText(Integer.toString(GameLogic.hero.getMoney()) + " g");
		money.setEditable(false);
		money.setPrefSize(80, 20);

		gp.add(t1, 1, 3, 4, 4);
		gp.add(t2, 1, 6, 4, 4);
		gp.add(t3, 1, 9, 2, 2);
		gp.add(use, 3, 9, 2, 2);
		gp.add(money, 3, 12, 2, 2);

		potion1.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			use.setVisible(false);
			t3.setText("Amount : " + ((Health) Inventory.getBag()[0]).getAmount());
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : 500 g");
		});

		potion2.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			use.setVisible(false);
			t3.setText("Amount : " + ((Mana) Inventory.getBag()[1]).getAmount());
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : 500 g");
		});

		sword.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			use.setVisible(true);
			if (((Weapons) Inventory.getBag()[2]).getAmount() == 1) {
				use.setDisable(false);
				use.setId("2");
			} else {
				use.setId("");
				use.setDisable(true);
			}
			t3.setText("Amount : " + ((Weapons) Inventory.getBag()[2]).getAmount());
			t1.setText("Sword\n");
			t2.setText("Price : 5000 g");
			// t3.setText("Amount : " + ((Weapons)Inventory.getBag()[2]).getAmount());
		});

		bow.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			use.setVisible(true);
			if (((Weapons) Inventory.getBag()[3]).getAmount() == 1) {
				use.setDisable(false);
				use.setId("3");
			} else {
				use.setId("");
				use.setDisable(true);
			}
			t1.setText("Bow\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : " + ((Weapons) Inventory.getBag()[3]).getAmount());
		});

		staff.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			use.setVisible(true);
			if (((Weapons) Inventory.getBag()[4]).getAmount() == 1) {
				use.setDisable(false);
				use.setId("4");
			} else {
				use.setId("");
				use.setDisable(true);
			}
			t1.setText("Staff\n");
			t2.setText("Price : 5000 g");
			t3.setText("Amount : " + ((Weapons) Inventory.getBag()[4]).getAmount());
		});

		use.setOnMouseClicked((MouseEvent event0) -> {
			ResourceLoader.clickSound.play(100);
			if (Inventory.getBag()[Integer.valueOf(use.getId())] instanceof Item
					&& ((Item) Inventory.getBag()[Integer.valueOf(use.getId())]).isUsable()) {
				((Item) Inventory.getBag()[Integer.valueOf(use.getId())]).use();
				t3.setText("Amount : " + ((Item) Inventory.getBag()[Integer.valueOf(use.getId())]).getAmount());
			} else if (Inventory.getBag()[Integer.valueOf(use.getId())] instanceof Weapons) {
				((Weapons) (Inventory.getBag()[Integer.valueOf(use.getId())])).setWeapon();
				;
				t3.setText("Amount : " + ((Weapons) Inventory.getBag()[Integer.valueOf(use.getId())]).getAmount());
			}
			// else if (use.getId() == "") {
			// Alert alert = new Alert(AlertType.ERROR);
			// alert.setContentText("Cannot Use Item");
			// alert.setHeaderText("Inventory Error");
			// alert.showAndWait();
			// }
		});

		this.getChildren().add(gp);
	}

	public void shop() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.shop);
		GridPane gp = generate(ResourceLoader.shop);
		Shop shop = new Shop();

		Text t1 = new Text();
		t1.setFill(Color.ALICEBLUE);
		Text t2 = new Text();
		t2.setFill(Color.ALICEBLUE);

		TextField money = new TextField();
		money.setText(Integer.toString(GameLogic.hero.getMoney()) + " g");
		money.setEditable(false);
		money.setPrefSize(80, 20);

		Button buy = new Button("BUY");
		buy.setStyle("-fx-color: red;-fx-border: none");

		gp.add(t1, 1, 3, 4, 4);
		gp.add(t2, 1, 5, 4, 4);
		gp.add(buy, 3, 9, 2, 2);
		gp.add(money, 3, 12, 2, 2);

		potion1.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			buy.setId("0");
			t1.setText("Hp Potion\nHeal 100 points to Hp.");
			t2.setText("Price : " + ((Health) Inventory.getBag()[0]).getPrice() + " g");
		});

		potion2.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			buy.setId("1");
			t1.setText("Mp Potion\nHeal 100 points to Mp.");
			t2.setText("Price : " + ((Mana) Inventory.getBag()[1]).getPrice() + " g");
		});

		sword.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			buy.setId("2");
			t1.setText("Sword\n");
			t2.setText("Price : " + ((Weapons) Inventory.getBag()[2]).getPrice() + " g");
		});

		bow.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			buy.setId("3");
			t1.setText("Bow\n");
			t2.setText("Price : " + ((Weapons) Inventory.getBag()[3]).getPrice() + " g");
		});

		staff.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			buy.setId("4");
			t1.setText("Staff\n");
			t2.setText("Price : " + ((Weapons) Inventory.getBag()[4]).getPrice() + " g");
		});

		buy.setOnMouseClicked((MouseEvent event0) -> {
			shop.buy(Integer.valueOf(buy.getId()));
			money.setText(Integer.toString(GameLogic.hero.getMoney()) + " g");
		});

		this.getChildren().add(gp);
	}

	public void setting() {
		// TODO Auto-generated method stub
		defaultDraw(scene, ResourceLoader.setting);
		Button resume = new Button("RESUME");
		resume.setStyle("-fx-color: red;-fx-border: none ");
		resume.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			scene.toDungeon();
		});
		this.getChildren().add(resume);
	}

	public void dead() {
		// TODO Auto-generated method stub
		
		DungeonMain.getCanvas().canvasUpdate();
		defaultDraw(scene, ResourceLoader.dead);
		Button close = new Button("restart");
		close.setStyle("-fx-color: red;-fx-border: none ");
		close.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			GameLogic.dungeon.restart();
			DungeonMain.getLogic().newHero(GameLogic.hero.getAtkType());
			GameLogic.hero.inventory.reset();
			scene.toDungeon();
		});

		Button buyBack = new Button("Revive");
		buyBack.setStyle("-fx-color: red;-fx-border: none ");
		buyBack.setOnMouseClicked((MouseEvent e) -> {
			ResourceLoader.clickSound.play(100);
			GameLogic.hero.revive();
			scene.toDungeon();
		});
		this.getChildren().addAll(buyBack, close);
	}
}
