package utility;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InputUtility {
	public static double mouseX, mouseY;
	public static boolean mouseOnCanvas = false;
	private static Set<KeyCode> keyPressed = new HashSet<>();
	private static int tickCount = 0;
	private static boolean prevPressed = false;

	// hashset key code
	public static void addKey(KeyCode code) {
//		System.out.println(keyPressed.size());
		keyPressed.add(code);
	}
	
	public static boolean isKeyTrig() {
		return keyPressed.size()==0;
	}

	public static void removeKey(KeyCode code) {
		keyPressed.remove(code);
	}

	public static boolean isKeyPressed(KeyCode code) {
		return keyPressed.contains(code);
	}

	// counter mouse pressed
	public static boolean isPrevPressed() {
		return prevPressed;
	}

	public static void setMousePressed(boolean pressed) {
		if (pressed) {
			if (prevPressed)
				tickCount++;
			else
				tickCount = 1;
		}else tickCount=0;
		prevPressed = pressed;
//		System.out.println(tickCount+" "+prevPressed);
	}

	public static boolean isTriggered() {
		return tickCount == 1;
	}

	// set common Listener on scene
	public static void bindListeners(Scene scene) {
		scene.setOnKeyPressed((KeyEvent e) -> addKey(e.getCode()));
		scene.setOnKeyReleased((KeyEvent e) -> removeKey(e.getCode()));
		scene.setOnMousePressed((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				InputUtility.setMousePressed(true);
		});
		scene.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				InputUtility.setMousePressed(false);
		});		
	}

	// set unique listener on some canvas
	public static void bindMouseOnListeners(Canvas canvas) {
		canvas.setOnMouseEntered((MouseEvent e) -> mouseOnCanvas = true);
		canvas.setOnMouseExited((MouseEvent e) -> mouseOnCanvas = false);
		canvas.setOnMouseMoved((MouseEvent e) -> {
			if (mouseOnCanvas) {
				mouseX = e.getSceneX();
				mouseY = e.getSceneY();
//				System.out.println(mouseX+" "+mouseY);
			}
		});
		canvas.setOnMouseDragged((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				InputUtility.setMousePressed(true);
		});
		canvas.setOnMouseDragReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				InputUtility.setMousePressed(false);
		});
	}
}
