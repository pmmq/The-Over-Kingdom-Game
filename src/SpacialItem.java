public class SpacialItem extends enemy{
	private int type;
	public SpacialItem(int x, int y, int width, int height, String img,int type,int speed,int blood,int frame) {
		super(x, y, width, height, img,speed,blood);
		this.type = type;
	}
	public SpacialItem(String img,int x, int y, int width, int height,int type,int speed,int frame) {
		super(img,x, y, width, height,speed,frame);
		this.speed=speed;
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
