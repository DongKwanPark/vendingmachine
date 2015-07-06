package Final;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
	
	private int customerMoney = 0;
	
	protected List<MoneyManager> listMoneyManager;
	protected List<BeverageManager> listBeverageManager;
	
	VendingMachine() {
		open();
	} 
	
	private int getCustomerMoney() {
		return customerMoney;
	}
	
	private void setCustomerMoney(int money) {
		this.customerMoney = money;
	}
	
	protected void open() {
		initMoneyManager();
		initBeverageManager();
	}
	
	protected Money makeMoney(int index) {
		Money money = null;
		switch( index )
		{
			case 0 : money = new Ten(); break;
			case 1 : money = new Fifty(); break;
			case 2 : money = new Hundred(); break;
			case 3 : money = new FiveHundred(); break;
			case 4 : money = new Thousand(); break;
			case 5 : money = new FiveThousand(); break;
		}
		return money;
	}
	
	protected void initMoneyManager() {
		int index = 0;
		listMoneyManager = new ArrayList<MoneyManager>();
		listMoneyManager.add( new MoneyManager( 10, makeMoney(index++), 20 ) );
		listMoneyManager.add( new MoneyManager( 10, makeMoney(index++), 20 ) );
		listMoneyManager.add( new MoneyManager( 10, makeMoney(index++), 20 ) );
		listMoneyManager.add( new MoneyManager( 10, makeMoney(index++), 20 ) );
		listMoneyManager.add( new MoneyManager( 10, makeMoney(index++), 20 ) );
		listMoneyManager.add( new MoneyManager( 0, makeMoney(index++), 5 ) );
	}
	
	protected void initBeverageManager() {
		listBeverageManager = new ArrayList<BeverageManager>();
		listBeverageManager.add( new BeverageManager( 10, new Coke() ) );
		listBeverageManager.add( new BeverageManager( 10, new Cider() ) );
		listBeverageManager.add( new BeverageManager( 10, new Coffe() ) );
		listBeverageManager.add( new BeverageManager( 10, new Juice() ) );
		listBeverageManager.add( new BeverageManager( 11, new Soda() ) );
	}
	
	public void run() {
		
		boolean run = true;
		while( run )
		{
			System.out.println( "####################" );
			System.out.println( "# 1. 동전 투입" );
			System.out.println( "# 2. 음료수" );
			System.out.println( "# 3. 거스름돈" );
			System.out.println( "# 4. 자판기 상태" );
			System.out.println( "# 5. 종료" );
			System.out.println( "####################" );
			
			Scanner scanner = new Scanner(System.in);
			int select = scanner.nextInt();
			
			switch( select )
			{
				case 1 :
					System.out.println( "돈을 넣어주세요." );
					int money = scanner.nextInt();
					if( setMoney( money ) ) {
						putCustomerMoney( money );	
					} else {
						run = false;
					}
					break;
				case 2 :
					System.out.println( "음료수를 선택하세요." );
					showBeverageSelect();
					selectBeverage( scanner.nextInt(), 1 );
					break;
				case 3 :
					giveChange();
					run = false;
					break;
				case 4 :
					showMoneyList();
					System.out.println();
					showBeverageList();
					break;
				case 5 :
					run = false;
					break;
				default :
					System.out.println( "자판기를 흔들거나 치지 마십시오." );
					break;
			}
			
			if( !run )
				break;
			
			System.out.println();
		}
		
		System.out.println( "한국 자판기를 이용해주셔서 갑사합니다." );
	}
	
	private void putCustomerMoney(int money) {
		this.customerMoney += money;
		System.out.println( customerMoney + "원이 투입되었습니다." );
	}
	
	public void selectBeverage(int select, int buycount) {
		if( select > 0 && listBeverageManager.size() >= select ) {
			BeverageManager bm = listBeverageManager.get( select - 1 );
			int selectIndex = 0;
			Beverage beverage = bm.select( selectIndex );
			if( beverage == null ) {
				System.out.println( "선택한 음료수가 부족합니다." );
			} else {
				int price = beverage.getPrice();
				String name = beverage.getName();
				if( price > getCustomerMoney() ) {
					System.out.println( "돈이  부족합니다." );	
				} else {
					if( bm.buy( buycount, selectIndex ) ) {
						setCustomerMoney( getCustomerMoney() - price );
						System.out.println( name + " " + buycount + "개가 나왔습니다." );
						System.out.println( "남은 금액은 " + getCustomerMoney() + "원 입니다." );
					} else {
						System.out.println( "자판기에 오류가 났습니다. 관리자에게 문의 바랍니다." );
					}
				}	
			}
		}
		else {
			System.out.println( "선택을 잘못하였습니다." );
		}
	}
	
	public void showBeverageSelect() {
		int count = listBeverageManager.size();
		for( int index = 0; index < count; index++ ) {
			BeverageManager bm = listBeverageManager.get( index );
			bm.showBeverageSelect( index );
		}
	}
	
	public void giveChange() {
		int giveMoney = getMoney( getCustomerMoney() );
		this.customerMoney -= giveMoney; 
		System.out.println( "반환 금액은 " + giveMoney + "원 입니다." );
		showMoneyList();
	}
	
	public void showMoneyList() {
		int count = listMoneyManager.size();
		for( int index = 0; index < count; index++ ) {
			MoneyManager mm = listMoneyManager.get( index );
			mm.showList();
		}
	}
	
	public void showBeverageList() {
		int count = listBeverageManager.size();
		for( int index = 0; index < count; index++ ) {
			BeverageManager bm = listBeverageManager.get( index );
			bm.showList();
		}
	}
	
	public boolean setMoney(int saveMoney) {
		boolean result = false;
		int count = listMoneyManager.size();
		for( int index = count - 1; index >= 0; index-- ) {
			MoneyManager mm = listMoneyManager.get( index );
			int price = mm.getPrice();
			if( price > 0 ) {
				int saveCount = ( saveMoney / price );
				if( saveCount > 0 ) {
					result = mm.setMoney( saveCount, makeMoney( index ) );
					if( !result ) {
						System.out.println( "저장 할 수 있는 최대개수 " + mm.getMaxStoreCount() + "개가 넘었습니다." );
						System.out.println( "관리자에게 문의 바랍니다." );
						break;
					} else {
						saveMoney /= price;
					}
				}
			}
		}
		return result;
	}
	
	public int getMoney(int giveMoney) {
		int money = 0;
		int count = listMoneyManager.size();
		for( int index = count - 1; index >= 0; index-- ) {
			MoneyManager mm = listMoneyManager.get( index );
			int price = mm.getPrice();
			if( price > 0 ) {
				int removeCount = ( giveMoney / price );
				if( removeCount > 0 ) {
					boolean result = mm.giveMoney( removeCount, makeMoney( index ) );
					if( !result ) {
						System.out.println( "반환할" + price + "의 개수가 부족합니다." );
						System.out.println( "관리자에게 문의 바랍니다." );
						break;
					} else {
						giveMoney %= price;
						money += ( removeCount * price );
					}
				}
			}
		}
		return money;
	}
}










