public class enemy extends BaseObject{
	private int blood,defultBlood,type;
	int speedcount = 0,speed;
	boolean check=true;
	public enemy(int x, int y, int width, int height,String img,int speed,int blood) {
		super(x, y, width, height, img);
		this.speed=speed;
		this.blood=blood;
		defultBlood = blood;
	}	
	public enemy(String img,int x, int y, int width, int height,int speed,int frame) {
		super(img,x, y, width, height,frame);
		this.speed=speed;
	}
	public enemy(String img,int x, int y, int width, int height,int frame,int speed,int blood,int type) {
		super(img,x, y, width, height,frame);
		this.speed=speed;
		this.blood=blood;
		defultBlood = blood;
		this.type = type;
	}
	public void enemyMove(){
		if(check){	
		setX(getX()-speed);
		}
	}
	public void bloodDown(int damage){
		blood-=damage;
	}
	public int getBlood(){
		return blood;
	}
	public boolean getCheck(){
		return check;
	}
	public void setCheck(boolean a){
		this.check=a;
	}
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public int getDefultBlood(){
		return defultBlood;
	}
	public int getType(){
		return type;
	}
}
