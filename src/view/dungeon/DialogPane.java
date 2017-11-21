package view.dungeon;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.SceneManeger;

public class DialogPane extends VBox {
	DungeonScene scene;
	
	public DialogPane(DungeonScene dunScene){
		super(10);
		this.scene = dunScene;
		
		this.setMaxSize(SceneManeger.WIDGTH/2, SceneManeger.HEIGHT/3);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: white");
		//this.setVisible(true);		
	}
	
	public void opening() {
		Text head = new Text("Welcome!");
		Text sub = new Text("Please enter your name:");
		TextField textField = new TextField();
		textField.setPrefWidth(this.getWidth()/3);
		Button yBtn = new Button("YES");
		
		this.getChildren().addAll(head,sub,textField,yBtn);
		
		yBtn.setOnMouseClicked((MouseEvent e)->{
			scene.toDungeon();
		});
	}
	
	public void setting() {
		
	}
	
	public void item() {
		
	}
}
