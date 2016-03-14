import java.awt.event.MouseEvent;
import java.util.Vector;
public class grid{
	boolean check;
	Money money = new Money();
	button  g[] = new button[8];
	int x=186;
	public grid(){
		for(int i = 0; i < g.length;i++){
			g[i]=new button(x,345,70,145,false);
			x += 75;
		}
	}
	public Vector<item> pushItem(MouseEvent e,int type,Vector<item> item){
		check = false;
		for(int i = 0; i< g.length;i++){
			if(g[i].setClick(e)&&(g[i].getSlot()==false)&&(money.getMoney()>=type)){
				g[i].setSlot(true);
				if(type == 10){
					item.add(new item(g[i].getX(),g[i].getY(),g[i].getWidth(),g[i].getHeight(),"a.png",1));
					check = true;
					money.moneyDown(type);
				}
				else if(type == 20){
					item.add(new item(g[i].getX(),g[i].getY(),g[i].getWidth(),g[i].getHeight(),"b.png",2));
					check = true;
					money.moneyDown(type);
				}
				else if(type == 30){
					item.add(new item(g[i].getX(),g[i].getY(),g[i].getWidth(),g[i].getHeight(),"c.png",3));
					check = true;
					money.moneyDown(type);
				}
				else if(type == 40){
					item.add(new item(g[i].getX(),g[i].getY(),g[i].getWidth(),g[i].getHeight(),"d.png",4));
					check = true;
					money.moneyDown(type);
				}
			}
		}
		return item;
	}
	public boolean checkClick(){
		if(check == true){
			return false;
		}
		return true;
	}
	public boolean checkMoney(int type){
		if(money.getMoney()>=type){
			return true;
		}
		return false;
	}
	public void setMoney(int x){
		money.moneyUp(x);
	}
	public int getMoney(){
		return money.getMoney();
	}
	public void setSlot(int i){
		g[i].setSlot(false);
	}
	public button[] getGrid(){
		return g;
	}
}

