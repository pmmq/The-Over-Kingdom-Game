import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameloop extends JPanel implements ActionListener,MouseListener,MouseMotionListener,KeyListener{
	int time=0,timeEnemy,timeItem,timeuseitem,timeWave;
	boolean i1 = false,i2=false,i3=false,i4=false,sItemNew = false,sItemTimeStart = false,atkcheck=false,gameover=false;
	Timer timer;
	grid grid = new grid();
	button []grid2 = grid.getGrid();
	int bullettype=2,gunDelay,speedEnemy=2,wavetype,blood=100,x;
	static double degree,degree2;
	Score score = new Score();
	SpacialItem sItem;
	button[] menu = new button[2];
	Vector <BaseObject> boom = new Vector<BaseObject>();
	Vector <enemy> enemyAtk = new Vector<enemy>();
	Vector <bullet> vecBullet= new Vector<bullet>();
	Vector <item> item = new  Vector<item>();
	Vector <item> vecItem1 = new Vector<item>();
	Vector <enemy> vecEnemy = new Vector<enemy>();
	Gun[]gun=new Gun[4];
	Random random = new Random();
	int []typeItem = {10,20,30,40};
	tower tower;
	String currentState="menu",a;
	BaseObject [] object = new BaseObject[7];
	public Gameloop(){
		addKeyListener(this);
		addMouseListener(this);
		setSize(800,600);
		setFocusable(true);
		setDoubleBuffered(true);
		timer = new Timer(16,this);
		timer.start();
		vecItem1.add(new item(40,490,80,80,"wall1.png",1));
		vecItem1.add(new item(150,490,80,80,"wall2.png",2));
		vecItem1.add(new item(260,490,80,80,"wall3.png",3));
		vecItem1.add(new item(370,490,80,80,"wall4.png",4));
		tower = new tower(-10,200,196,290,"tower.png",100);
	}
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		if(currentState=="menu"){
			object[0] = new BaseObject(0,0,800,600,"menu/bg.png");
			object[0].draw(g2d);
			x=25;
			for(int i=1;i<object.length-1;i++){
				a="menu/button_"+i+".png";
				object[i]=new BaseObject(x,0,75,600,a);
				object[i].draw(g2d);
				x+=90;
			}
		}
		if(currentState=="credit"){
			object[0] = new BaseObject(305,207,190,186,"credit/logo_dpu.png",0);
			object[1] = new BaseObject(275,175,251,251,"credit/1.png",degree2);
			object[2] = new BaseObject(252,152,296,296,"credit/2.png",-degree2);
			object[3] = new BaseObject(229,129,343,343,"credit/3.png",degree2);
			object[4] = new BaseObject(204,104,393,393,"credit/4.png",-degree2);
			object[5] = new BaseObject(177,77,446,446,"credit/5.png",degree2);
			object[6] = new BaseObject(0,0,800,600,"credit/bg.png");
			object[6].draw(g2d);
			for(int i=0;i<object.length-1;i++){
				object[i].drawRo(g2d,object[i].getX()+object[i].getWidth()/2,object[i].getY()+object[i].getHeight()/2);
				degree2+=0.1;
			}
		}
		if(currentState=="play"){
		timeWave+=timer.getDelay();
		timeItem+=timer.getDelay();
		timeEnemy+=timer.getDelay();
		time += timer.getDelay();
		object[3] = new BaseObject(0,0,800,485,"bg.png");
		object[3].draw(g2d);
		if(timeWave>10000){
			timeWave=0;
			wavetype++;
			if(wavetype==4){
				wavetype=0;
				blood+=50;
				speedEnemy+=1;
			}
		}
		if(sItemTimeStart == true){
			timeuseitem += timer.getDelay();
			if(timeuseitem > 5000){
				bullettype = 2;
				sItemTimeStart = false;
				timeuseitem = 0;
			}
		}
		if(bullettype == 2){
			gunDelay = 500;
			gun[0] = new Gun(90,195,68,19,"gun.png",degree);
			object[0] = new BaseObject(gun[0].getX()+15,gun[0].getY()-9,40,40,"Wheel.png"); 
			object[1] = new BaseObject(gun[0].getX()+15,gun[0].getY()-13,40,40,"Wheel.png");
			object[1].draw(g2d);
			gun[0].drawRo(g2d,gun[0].getX()+34,gun[0].getY()+9);
			object[0].draw(g2d);
		}
		else if(bullettype==0){
			gunDelay = 500;
			gun[1] = new Gun(90,195,68,19,"gun.png",degree-10);
			gun[2] = new Gun(90,195,68,19,"gun.png",degree);
			gun[3] = new Gun(90,195,68,19,"gun.png",degree+10);
			object[0] = new BaseObject(gun[2].getX()+15,gun[2].getY()-9,40,40,"Wheel.png"); 
			object[1] = new BaseObject(gun[2].getX()+15,gun[2].getY()-13,40,40,"Wheel.png");
			object[1].draw(g2d);
			gun[1].drawRo(g2d,gun[1].getX()+34,gun[1].getY()+9);
			gun[2].drawRo(g2d,gun[2].getX()+34,gun[2].getY()+9);
			gun[3].drawRo(g2d,gun[3].getX()+34,gun[3].getY()+9);
			object[0].draw(g2d);
		}
		else if(bullettype==1){
			gunDelay=50;
			gun[0] = new Gun(90,195,68,19,"gun2.png",degree);
			object[0] = new BaseObject(gun[0].getX()+15,gun[0].getY()-9,40,40,"Wheel.png"); 
			object[1] = new BaseObject(gun[0].getX()+15,gun[0].getY()-13,40,40,"Wheel.png");
			object[1].draw(g2d);
			gun[0].drawRo(g2d,gun[0].getX()+34,gun[0].getY()+9);
			object[0].draw(g2d);
		}
		for(int i =0;i< vecItem1.size();i++){
			vecItem1.elementAt(i).draw(g2d);
		}
		for(int i = 0;i<item.size();i++){
			item.elementAt(i).draw(g2d);
		}
		if(time>gunDelay){
			time = 0;
			if(bullettype == 2||bullettype==1){
			vecBullet.add(new bullet((int)((Math.cos(Math.toRadians(degree))*40)+115),(int)((Math.sin(Math.toRadians(degree))*-40)+195),15,15,"bullet.png",degree));   
			}
			else if(bullettype == 0){
			vecBullet.add(new bullet((int)((Math.cos(Math.toRadians(degree-10))*40)+115),(int)((Math.sin(Math.toRadians(degree-10))*-40)+195),15,15,"bullet.png",degree-10));
			vecBullet.add(new bullet((int)((Math.cos(Math.toRadians(degree))*40)+115),(int)((Math.sin(Math.toRadians(degree))*-40)+195),15,15,"bullet.png",degree));
			vecBullet.add(new bullet((int)((Math.cos(Math.toRadians(degree+10))*40)+115),(int)((Math.sin(Math.toRadians(degree+10))*-40)+195),15,15,"bullet.png",degree+10));
			}
		}	
		for(int i=0; i<vecBullet.size();i++){
			bullet bullet = vecBullet.elementAt(i);
			bullet.draw(g2d);
			if(vecBullet.elementAt(i).getY()>=470){
				boom.add(new BaseObject("src/boom/boom",vecBullet.elementAt(i).getX()-40,410,80,80,50));
				vecBullet.remove(i);
			}
			bullet.timeUp();
			bullet.pointX();
			bullet.pointY();
		}
		if(timeItem>10000){
			timeItem=0;
			sItem = new SpacialItem("src/bird/walk/",800,random.nextInt(350),132,120,random.nextInt(2),10,7);
			sItemNew = true;
		}
		if(sItemNew==true){
			if(sItem.getX()>0){
					sItem.setTime(timer.getDelay(),30);
					sItem.drawSprite(g2d);
					sItem.enemyMove();
			}
			else{
				sItemNew = false;
			}
		}
		object[2] = new BaseObject(0,480,795,91,"frame-down.png");
		tower.draw(g2d);
		object[2].draw(g2d);
		for(int i=0;i<boom.size();i++){
			boom.elementAt(i).setTime(timer.getDelay(),0);
			boom.elementAt(i).drawSprite(g2d);
			if(boom.elementAt(i).getCurrentFrame()==49){
				boom.remove(i);
			}
		}
		if(timeEnemy>1000){
			timeEnemy=0;
			if(wavetype%4==0){
				vecEnemy.add(new enemy("src/elf/walk/",800,403,85,79,8,speedEnemy,blood,1));
				enemyAtk.add(new enemy("src/elf/walk/",800,403,85,79,8,speedEnemy,blood,1));
			}
			else if(wavetype%4==1){
				vecEnemy.add(new enemy("src/horse/walk/",800,381,119,101,4,speedEnemy,blood+50,2));
				enemyAtk.add(new enemy("src/horse/atk/",800,381,119,101,9,speedEnemy,blood+50,9));
			}
			else if(wavetype%4==2){
				vecEnemy.add(new enemy("src/giant/walk/",800,327,178,155,5,speedEnemy,blood+100,3));
				enemyAtk.add(new enemy("src/giant/atk/",800,327,178,155,9,speedEnemy,blood+100,9));
			}
			else if(wavetype%4==3){
				vecEnemy.add(new enemy("src/golem/walk/",800,332,175,150,4,speedEnemy,blood+150,4));
				enemyAtk.add(new enemy("src/golem/atk/",800,332,175,150,9,speedEnemy,blood+150,8));
			}
		}
		for(int i = 0;i<vecEnemy.size();i++){
			enemy enemy = vecEnemy.elementAt(i);
			if(atkcheck==false){
			enemy.drawSprite(g2d);
			}
			enemy.enemyMove();
			enemyAtk.elementAt(i).enemyMove();
			for(int z=0;z<vecBullet.size();z++){
				bullet bullet = vecBullet.elementAt(z);
				if(enemy.getCollision(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight())){
					vecBullet.remove(z);
					enemy.bloodDown(50);
				}
			}
			for(int j = 0;j<item.size();j++){
				item itema = item.elementAt(j);					
				if(itema.getCollision(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight())){	
					atkcheck=true;
					if(atkcheck==true){
						enemyAtk.elementAt(i).drawSprite(g2d);
					}
					if(enemy.getType()==1){
						itema.hpDown(10);
					}
					else if(enemy.getType()==2){
						itema.hpDown(20);
					}
					else if(enemy.getType()==3){
						itema.hpDown(30);
					}
					else if(enemy.getType()==4){
						itema.hpDown(40);
					}
					if(itema.getType()==1){
						enemy.setCheck(false);
						enemyAtk.elementAt(i).setCheck(false);
					}
					else if(itema.getType()==2){
						enemy.bloodDown(1);
						enemyAtk.elementAt(i).bloodDown(1);
						enemy.setCheck(false);
						enemyAtk.elementAt(i).setCheck(false);
					}
					else if(itema.getType()==3){
						enemy.bloodDown(5);
						enemyAtk.elementAt(i).bloodDown(5);
						enemy.setCheck(false);
						enemyAtk.elementAt(i).setCheck(false);
					}
					else if(itema.getType()==4){
						enemy.bloodDown(1);
						enemyAtk.elementAt(i).bloodDown(1);
						enemy.setSpeed(1);
						enemyAtk.elementAt(i).setSpeed(1);
						enemy.setCheck(true);
						enemyAtk.elementAt(i).setCheck(true);
					}
				}
				else{
					enemy.drawSprite(g2d);
				}
				if(item.elementAt(j).getHp()<=0){
					for(int x=0;x<vecEnemy.size();x++){
						vecEnemy.elementAt(x).setCheck(true);
						enemyAtk.elementAt(i).setCheck(true);
					}
					for(int a = 0;a<grid2.length;a++){
						if(grid2[a].getX()==item.elementAt(j).getX()){
							grid.setSlot(a);
						}
					}
					item.remove(j);
					atkcheck=false;
					}
			}
			if(enemy.getBlood()<=0){
					boom.add(new BaseObject("src/boom/boom",enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getWidth(),50));
					vecEnemy.remove(i);
					enemyAtk.remove(i);
					grid.setMoney(10);
					if(wavetype%4==0){
						score.setScore(20);	
					}
					else if(wavetype%4==1){
						score.setScore(50);
					}
					else if(wavetype%4==2){
						score.setScore(100);
					}
					else if(wavetype%4==3){
						score.setScore(150);
					}
			}
			else if((enemy.getX()<180)){
				boom.add(new BaseObject("src/boom/boom",enemy.getX(),410,80,80,50));
				vecEnemy.remove(i);
				enemyAtk.remove(i);
				tower.downHp(10);
			}
		}
		for(int u=0;u<enemyAtk.size();u++){
			enemyAtk.elementAt(u).setTime(timer.getDelay(),50);
		}
		for(int t =0;t<vecEnemy.size();t++){
			vecEnemy.elementAt(t).setTime(timer.getDelay(),50);
		}
		if(tower.getHp()==0){
			vecEnemy.removeAllElements();
			vecBullet.removeAllElements();
			item.removeAllElements();
			enemyAtk.removeAllElements();
			sItem.setCheck(false);
			gameover = true;
			if(gameover ==true){
			menu[0] =new button(0,150,800,69,"retry.png");
			menu[1] =new button(0,250,800,69,"back.png");
			menu[0].draw(g2d);
			menu[1].draw(g2d);
			}
		}
		//money//
		g2d.setColor(new Color(0,0,0));
		g2d.drawString("Money "+grid.getMoney(),720,520);
		//blood tower//
		g2d.fillRect(539, 538,249*tower.getHp()/100, 28);
		g2d.drawString("Score "+score.getScore(),650,520);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			if(key == KeyEvent.VK_UP/*&&degree<18*/){
				degree+=2;
			}
			else if(key == KeyEvent.VK_DOWN/*&&degree>-72*/){
				degree-=2;
			}
	}
	public void mousePressed(MouseEvent e) {
		
		if(currentState=="menu"){
			if(object[1].setClick(e)){
				currentState="play";
			}
			if(object[4].setClick(e)){
				currentState="credit";
			}
			//if(object[5].setClick(e)){
			//	System.exit(0);
			//}
		}
		else if(currentState=="play"){
			if(sItemNew == true){
				if(sItem.setClick(e)){
					bullettype = sItem.getType();
					sItemNew = false;
					sItemTimeStart = true;
				}
			}
			if(gameover==true){
				if(menu[0].setClick(e)){
					time=0;timeEnemy=0;timeItem=0;timeuseitem=0;timeWave=0;
					bullettype=2;gunDelay=500;speedEnemy=2;wavetype=0;blood=100;
					gameover=false;
				}
				else if(menu[1].setClick(e)){
					currentState="menu";
				}
			}
		}
		else if(currentState=="credit"){
			if(object[6].setClick(e)){
				currentState="menu";
			}
		}
		if(vecItem1.elementAt(0).setClick(e)){
			i1= grid.checkMoney(typeItem[0]);
		}
		else if(vecItem1.elementAt(1).setClick(e)){
			i2= grid.checkMoney(typeItem[1]);
		}
		else if(vecItem1.elementAt(2).setClick(e)){
			i3= grid.checkMoney(typeItem[2]);
		}
		else if(vecItem1.elementAt(3).setClick(e)){
			i4= grid.checkMoney(typeItem[3]);
		}
		if(i1==true){
			item = grid.pushItem(e,10,item);
			i1 = grid.checkClick();
		}
		else if(i2==true){
			item = grid.pushItem(e,20,item);	
			i2 = grid.checkClick();
		}
		else if(i3==true){
			item = grid.pushItem(e,30,item);
			i3 = grid.checkClick();
			
		}
		else if(i4==true){
			item = grid.pushItem(e,40,item);
			i4 = grid.checkClick();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
