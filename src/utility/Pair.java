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
}
