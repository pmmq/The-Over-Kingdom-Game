import javax.swing.JFrame;
public class Gamemain extends JFrame{
	public Gamemain(){
		add(new Gameloop());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		//setLocationRelativeTo(800);
		setTitle("Projectile Test");
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Gamemain();
	}
}
