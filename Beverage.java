package Final;

public class Beverage {
	protected int price;
	protected String name;
	protected String makeDate;
	protected String selfLife;
	
	Beverage() {
		price = 0;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMakeDate() {
		return makeDate;
	}
}

class Coke extends Beverage {
	Coke() {
		this.price = 600;
		this.name = "콜라";
		this.makeDate = "2015.06.05";
		this.selfLife = "2016.12.01";
	}
}

class Cider extends Beverage {
	Cider() {
		this.price = 600;
		this.name = "사이다";
		this.makeDate = "2015.06.05";
		this.selfLife = "2016.12.01";
	}
}

class Coffe extends Beverage {
	Coffe() {
		this.price = 500;
		this.name = "커피";
		this.makeDate = "2015.06.05";
		this.selfLife = "2016.08.12";
	}
}

class Juice extends Beverage {
	Juice() {
		this.price = 700;
		this.name = "쥬스";
		this.makeDate = "2015.06.05";
		this.selfLife = "2016.05.29";
	}
}

class Soda extends Beverage {
	Soda() {
		this.price = 1000;
		this.name = "밀키스";
		this.makeDate = "2015.06.05";
		this.selfLife = "2017.03.29";
	}
}







