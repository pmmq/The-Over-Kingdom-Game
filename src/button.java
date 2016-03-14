import java.awt.event.MouseEvent;
public class button extends BaseObject{
	private boolean slot;
	public button(int x, int y, int width, int height, String img) {
		super(x, y, width, height, img);
		getHeight();
	}
	public button(String img,int x, int y, int width, int height,int frame) {
		super(x, y, width, height, img);
		getHeight();
	}
	public button(int x, int y, int width, int height,boolean slot) {
		super(x, y, width, height);
		this.slot = slot;
	}
	
	public boolean setClick (MouseEvent e){
		int MouseX = e.getX();
		int MouseY = e.getY();
		if(MouseX > getX() && MouseX < getX()+getWidth() && MouseY > getY() && MouseY < getY()+ getHeight()){
			return true;
		}
		return false;
	}
	public void setSlot(boolean slot){
		this.slot = slot;
	}
	public boolean getSlot(){
		return slot;
	}
}
