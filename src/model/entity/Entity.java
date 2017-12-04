package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.gameObject;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;

public abstract class Entity extends gameObject {
	public static final int FRONT = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BACK = 3;

	protected static double w;
	protected static double h;
	protected Image img;
	protected int direction;
	protected int movespeed;
	private int counter;
	private int walktick;

	public Entity(double x, double y, Image img, int row, int column, int direction, int movespeed) {
		super(x, y, 0);
		this.w = 32;
		this.h = 32;
		this.direction = direction;
		this.movespeed = movespeed;
		this.counter = 0;
		this.img = new WritableImage(img.getPixelReader(), (int) w * 3 * column, (int) h * 4 * row, (int) w * 3,
				(int) h * 4);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new WritableImage(img.getPixelReader(), (int) w * walktick, (int) h * direction, (int) w, (int) h),
				(int) pos.x - w / 2, (int) pos.y, w * 2.5, h * 2.5);
	}

	public boolean isInArea(Pair d) {
		return pos.diffX(d.x) <= (w / 2 - 5) && pos.diffY(d.y) <= h;
	}

	private void addWalkTick() {
		if (counter == 8) {
			this.walktick = (this.walktick + 1) % 3;
			counter = 0;
		}
		counter++;
	}

	protected void move(int direction) {
		// should ask if the change will be in the scene before change
		System.out.println((this.direction )+" "+(direction ));
		if (this.direction != direction && (this.direction % 3) == (direction % 3)) {
			// if((this.direction%3)+"+"+(direction%3))
			this.counter = 0;
			this.walktick = 1;
		} else if (InputUtility.isKeyTrig()) {
			this.walktick = (this.walktick + 1) % 3;
		} else if (this.direction == direction || ((this.direction % 3) != (direction % 3)&& (this.direction % 3)!=0 && (direction % 3)==0)) {
			addWalkTick();
		}

		if (direction == FRONT)
			this.pos.y += ((movespeed / 10.0) * SceneManeger.HEIGHT / 150);
		if (direction == BACK)
			this.pos.y -= ((movespeed / 10.0) * SceneManeger.HEIGHT / 150);
		if (direction == RIGHT)
			this.pos.x += ((movespeed / 10.0) * SceneManeger.WIDGTH / 200);
		if (direction == LEFT)
			this.pos.x -= ((movespeed / 10.0) * SceneManeger.WIDGTH / 200);

		this.direction = direction;
	}

	public abstract void update();
}
