package Final;

public class Money {
	
	protected int price;
	protected String unit;
		
	Money() {
		this.unit = "원";
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getUnit() {
		return unit;
	}
}

class Ten extends Money {
	Ten() {
		this.price = 10;
	}
}

class Fifty extends Money {
	Fifty() {
		this.price = 50;
	}
}

class Hundred extends Money {
	Hundred() {
		this.price = 100;
	}
}

class FiveHundred extends Money {
	FiveHundred() {
		this.price = 500;
	}
}

class Thousand extends Money {
	Thousand() {
		this.price = 1000;
	}
}

class FiveThousand extends Money {
	FiveThousand() {
		this.price = 5000;
	}
}










