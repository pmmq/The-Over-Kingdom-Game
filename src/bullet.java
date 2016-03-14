public class bullet extends BaseObject{
	private double time;
	private int dx,dx2,dy,dy2;
	private int u=70;
	public bullet(int x, int y, int width, int height, String img,double degree) {
		super(x, y, width, height, img,degree);
	}
	public void timeUp(){
		time+=0.09;
	}
	public void pointX(){
		dx = (int) (u * (Math.cos(Math.toRadians(getDegree()))) * time);
		setX(getX()+(dx-dx2));
		dx2=dx;
	}
	public void pointY(){
		dy = (int)((u * (Math.sin(Math.toRadians(getDegree()))) * time) - ((9.8 * time * time) / 2));
		setY(getY()-(dy-dy2));
		dy2=dy;
	}
}

