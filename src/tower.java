public class tower extends BaseObject{
	private int hp;
	public tower(int x, int y, int width, int height, String img,int hp) {
		super(x, y, width, height, img);
		this.hp = hp;
	}
	public void downHp(int hp){
		this.hp-=hp;
	}
	public int getHp(){
		return hp;
	}
}
