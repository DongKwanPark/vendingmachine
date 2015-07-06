package Final;

import java.util.ArrayList;
import java.util.List;

public class BeverageManager {
	
	protected Beverage sampleBeverage;
	protected List<Beverage> listBeverage = new ArrayList<Beverage>();
	
	BeverageManager() {
	}
	
	BeverageManager(final int count, final Beverage beverage) {
		makeSample( beverage );
		for( int i = 0; i < count; i++ )
			listBeverage.add( beverage );
	}
	
	protected void makeSample(final Beverage beverage) {
		sampleBeverage = beverage;
	}
	
	public Beverage select(int index) {
		Beverage select = null;
		if( index >= 0 && getCount() >= index + 1 ) {
			select = listBeverage.get( index );
		}
		return select;
	}
	
	public boolean buy(int buycount, int index) {
		boolean result = false;
		if( index >= 0 && getCount() >= index + 1 ) {
			listBeverage.remove( index );
			result = true;
		}
		return result;
	}
	
	public int getCount() {
		return listBeverage.size();
	}
	
	public int getPrice() {
		return sampleBeverage.getPrice();
	}
	
	public void showBeverageSelect(int index) {
		System.out.println( "- " + (index + 1) + " " + sampleBeverage.getName() );
	}
	
	public void showList() {
		System.out.println( sampleBeverage.getName() + " " + getCount() + "개" );
	}
}










