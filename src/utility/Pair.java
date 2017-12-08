package utility;

public class Pair {
	//class create base on pair structure
	public double x;
	public double y;

	public Pair() {
	}

	public Pair(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Pair(Pair o) {
		this.x = o.x;
		this.y = o.y;
	}
	
	public void add(double x,double y) {
		this.x+=x;
		this.y+=y;
	}
	
	public double diffX(double dx) {
		return Math.abs(x - dx);
	}
	
	public double diffY(double dy) {
		return Math.abs(y - dy);
	}
	
	public double diffD(double dx,double dy) {
		return Math.pow((diffX(dx)*diffX(dx))+(diffY(dy)*diffY(dy)), 0.5);
	}
}
