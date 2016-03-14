public class Money {
	private int money;
	public Money(){
		money=100;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void moneyUp(int x){
		money+=x; 
	}
	public void moneyDown(int x){
		money-=x; 
	}
}
