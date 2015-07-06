package Final;

import java.util.List;
import java.util.ArrayList;

public class MoneyManager {

	protected int maxStoreCount;
	protected List<Money> listMoney = new ArrayList<Money>();
	
	MoneyManager() {		
	}
	
	MoneyManager(final int count, final Money money, final int maxStoreCount) {
		this.maxStoreCount = maxStoreCount;
		for( int i = 0; i < count; i++ )
			listMoney.add( money );
	}
	
	protected int getMaxStoreCount() {
		return maxStoreCount;
	}
	
	public int getCount() {
		return listMoney.size();
	}
	
	public int getPrice() {
		int price = 0;
		int count = getCount();
		if( count > 0 ) {
			Money money = listMoney.get(0);
			price = money.getPrice();
		}
		return price;
	}
	
	public void showList() {
		int count = getCount();
		if( count > 0 ) {
			Money money = listMoney.get(0);
			System.out.println( money.getPrice() + money.getUnit() + " " + count + "개" );
		}
	}
	
	public boolean setMoney(final int count, final Money money) {
		boolean result = false;
		if( count + getCount() < getMaxStoreCount() ) {
			for( int i = 0; i < count; i++ ) {
				listMoney.add( money );
				result = true;
			}
		}
		return result;
	}
	
	public boolean giveMoney(final int count, final Money money) {
		boolean result = false;
		if( count < getCount() ) {
			for( int i = 0; i < count; i++ ) {
				listMoney.remove( getCount() - 1 );
				result = true;
			}
		}
		return result;
	}
}




