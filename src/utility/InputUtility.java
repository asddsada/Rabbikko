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
	private static boolean mousePressed = false;

	// keyboard setter
	private static void addKey(KeyCode code) {
		// System.out.println(keyPressed.size());
		keyPressed.add(code);
	}

	private static void removeKey(KeyCode code) {
		keyPressed.remove(code);
	}

	// keyboard getter
	public static boolean isKeyPressed(KeyCode code) {
		return keyPressed.contains(code);
	}

	public static boolean isKeyTrig() {
		return keyPressed.size() == 0;
	}

	// mouse getter
	public static boolean isMousePressed() {
		return mousePressed;
	}

	// set common Listener on scene
	public static void bindListeners(Scene scene) {
		scene.setOnKeyPressed((KeyEvent e) -> addKey(e.getCode()));
		scene.setOnKeyReleased((KeyEvent e) -> removeKey(e.getCode()));
		scene.setOnMousePressed((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				mousePressed = true;
		});
		scene.setOnMouseReleased((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY)
				mousePressed = false;
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
				// System.out.println(mouseX+" "+mouseY);
			}
		});
	}
}
