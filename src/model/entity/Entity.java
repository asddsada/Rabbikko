package model.entity;

import com.sun.corba.se.spi.ior.Writeable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.gameObject;
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
	private int walkTick;
	private int maxWalkTick;
	private String name;
	
	public Entity(double x,double y,Image img,int row,int column, int direction,int movespeed) {
		super(x,y,0);
		this.w = 32;
		this.h = 32;		
		this.direction = direction;
		this.movespeed =movespeed;
		this.walkTick=2;
		this.maxWalkTick=3;
		this.img = new WritableImage(img.getPixelReader(), (int) w*3*column, (int) h*4*row,(int) w*3, (int) h*4);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new WritableImage(img.getPixelReader(), (int) w*walkTick, (int) h*direction,(int) w, (int) h)
				, (int) pos.x-w/2, (int) pos.y,w*2.5,h*2.5);
	}

	public boolean isInArea(Pair d) {
		return pos.diffX(d.x) <= (w/2-5) && pos.diffY(d.y) <= h;
	}
	
	protected void resetWalkTick() {
		this.walkTick=2;
	}
	
	private void addWalkTick() {
		if(walkTick==maxWalkTick) walkTick=1;
		else walkTick++;
	}
	
	protected void move(int direction) {
		if(this.direction == direction) resetWalkTick();
		else {
//			addWalkTick();
			this.direction = direction;
		}
		if(direction==FRONT) this.pos.y+=(movespeed*100/SceneManeger.HEIGHT);
		else if(direction==BACK) this.pos.y-=(movespeed*100/SceneManeger.HEIGHT);
		else if(direction==RIGHT) this.pos.x+=(movespeed*100/SceneManeger.WIDGTH);
		else if(direction==LEFT) this.pos.x-=(movespeed*100/SceneManeger.WIDGTH);
		
	}
	
	public abstract void update();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
