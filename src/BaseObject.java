import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class BaseObject {
	private int x,y,width,height;
	private double degree,x2,y2;
	private Image image;
	private AffineTransform Ro;
	private int frame;
	private Image[]sImage;
	private int time;
	private int currentFrame=0;
	private String number;
	public int currentImage=0;
	public boolean check;
	public BaseObject(int x,int y,int width,int height,String img,double degree){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.degree = degree;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(img));
		this.image = ii.getImage();
	}
	public BaseObject(int x,int y,int width,int height,String img){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(img));
		this.image = ii.getImage();
	}
	public BaseObject(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public BaseObject (String path,int x,int y,int width,int height,int frame){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.frame = frame;
		sImage = new Image[frame];
		for(int i = 1;i<=frame;i++){
		if(i<10){
			number = "_0"+i;
		}
		else {
		    number = "_"+i;		
		}
		ImageIcon ii = new ImageIcon(path+number+".png");
		sImage[i-1] = ii.getImage();
		}
	}
	public BaseObject (String img, int x, int y, int width, int height, int col, int row){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = col*row;
		sImage = new Image[frame];
		ImageIcon ii = new ImageIcon(this.getClass().getResource(img));
		Image source = ii.getImage();
		int width_image = ii.getIconWidth();
		int height_image = ii.getIconHeight();
		int width_crop = width_image/col;
		int height_crop = height_image/row;
		int count=0;
		for(int i = 0; i<row; i++){
			for(int j = 0; j<col; j++){
			JFrame Jf = new JFrame();
			sImage[count] =
			Jf.createImage(new FilteredImageSource(source.getSource(),
					new CropImageFilter(j*width_crop, i*height_crop, width_crop, height_crop)));
			count++;
			}
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public double getDegree(){
		return degree;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Image getImage() {
		return image;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public boolean getCollision(int x2,int y2,int width2,int height2){
		if(x<x2+width2&&x+width>x2&&y<y2+height2&&y+height>y2){
			return true;
		}
		return false;
	}
	public void drawSprite(Graphics2D g2d){
		Ro = new AffineTransform();
		Ro.rotate(Math.toRadians(0), x, y);
		g2d.setTransform(Ro);
		g2d.drawImage(getImageFrame(), getX(),getY(),getWidth(),getHeight(),null);
	}
	public void draw(Graphics2D g2d){
		Ro = new AffineTransform();
		Ro.rotate(Math.toRadians(0), x, y);
		g2d.setTransform(Ro);
		g2d.drawImage(getImage(), getX(),getY(),getWidth(),getHeight(),null);
	}
	public void drawRo(Graphics2D g2d,int x,int y){
		Ro = new AffineTransform();
		Ro.rotate(Math.toRadians(-degree), x, y);
		g2d.setTransform(Ro);
		g2d.drawImage(getImage(), getX(),getY(),getWidth(),getHeight(),null);
	}
	public Image getImageFrame(){
		return sImage[currentFrame];
	}
	public int getCurrentFrame(){
		return currentFrame;
	}
	public void setTime(int timer,int delayFrame){
		this.time += timer;
		if(time>delayFrame){
			time = 0;
			currentFrame++;
		}
		if(currentFrame>=frame)
			currentFrame = 0;
	}
	public double getX2(){
		return x2;
	}
	public double getY2(){
		return y2;
	}
	public boolean setClick (MouseEvent e){
		int MouseX = e.getX();
		int MouseY = e.getY();
		if(MouseX > getX() && MouseX < getX()+getWidth() && MouseY > getY() && MouseY < getY()+ getHeight()){
			return true;
		}
		return false;
	}
}
