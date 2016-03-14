public class item extends button{
	private int hp=5000;
	private int type;
	public item(int x, int y, int width, int height, String img,int type) {
		super(x, y, width, height, img);
		this.type = type;
	}
	public item( String img,int x, int y, int width, int height,int type,int frame) {
		super(img,x, y, width, height,frame);
		this.type = type;
	}
	public void hpDown(int damage){
		setHp(getHp()-damage);
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getType(){
		return type;
	}
}
