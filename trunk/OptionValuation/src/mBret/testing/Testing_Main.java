package testing;

import options.BlackScholesFormula;
import options.BlackScholes_abbreviated;
import options.Black_76;

public class Testing_Main {

	// timeToExp can be calculated (Approximated) by 
	// taking expiration date less valuation date (today)
	// and divide by 365.
	//
	// (expiration date - today) / 365
			
	
	public static void main(String[] args) {

		// TEST DATA -- trade 106005
		double strike = 85.00;
		double price = 82.80;
		double timeToExp = .379673; 
		//double timeToExp = .383562;
		double stDev = 0.13425;
		double interest = 0.00085;
		char call = 'c';
		Boolean callOption = true;
		
		BlackScholes_abbreviated bsShort = new BlackScholes_abbreviated();
		Double price1 = bsShort.blackScholesCall(price, strike, stDev, timeToExp, interest);
		System.out.println("  price1 = [" + price1.toString() + "]");
		
		Black_76 bs76 = new Black_76();
		Double price2 = bs76.Black76(call, price, strike, timeToExp, interest, stDev);
		System.out.println("  price2 = [" + price2.toString() + "]");
		
		BlackScholesFormula bsFormula = new BlackScholesFormula();
		Double price3 = bsFormula.calculate(callOption, price, strike, interest, timeToExp, stDev);
		System.out.println("  price3 = [" + price3.toString() + "]");

		System.out.println();
		
		// TEST DATA -- trade 104335
		 strike = 18.25;
		 price = 13.10;
		 //timeToExp = 2.13035;
		 timeToExp = 2.134247;
		 stDev = 0.21;
		 interest = 0.002541;
		 call = 'c';
		 callOption = true;
		
		 price1 = bsShort.blackScholesCall(price, strike, stDev, timeToExp, interest);
		System.out.println("  price1 = [" + price1.toString() + "]");
		
		 price2 = bs76.Black76(call, price, strike, timeToExp, interest, stDev);
		System.out.println("  price2 = [" + price2.toString() + "]");
		
		 price3 = bsFormula.calculate(callOption, price, strike, interest, timeToExp, stDev);
		System.out.println("  price3 = [" + price3.toString() + "]");
	}

}
