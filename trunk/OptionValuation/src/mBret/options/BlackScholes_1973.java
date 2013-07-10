package mBret.options;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Black–Scholes formula, which gives a theoretical estimate of 
 * the price of European-style options.
 * 
 * The model was first articulated by Fischer Black and Myron Scholes in 
 * their 1973 paper, "The Pricing of Options and Corporate Liabilities", 
 * published in the Journal of Political Economy. They derived a stochastic 
 * partial differential equation, now called the Black–Scholes equation, 
 * which governs the price of the option over time. 
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class BlackScholes_1973 {

	// The Black and Scholes (1973) Stock option formula

	// S= Stock price
	// X=Strike price
	// T=Years to maturity
	// r= Risk-free rate
	// v=Volatility

	public double BlackScholes(char CallPutFlag, double S, double X, double T,
			double r, double v) {
		
		double d1, d2;

		d1 = (Math.log(S / X) + (r + v * v / 2) * T) / (v * Math.sqrt(T));
		d2 = d1 - v * Math.sqrt(T);

		if (CallPutFlag == 'c') {
			return S * CND(d1) - X * Math.exp(-r * T) * CND(d2);
		} else {
			return X * Math.exp(-r * T) * CND(-d2) - S * CND(-d1);
		}
	}
	
	public double BlackScholes(char CallPutFlag, double S, double X, Calendar exDate,
			double r, double v) {

		double T = getYearsToMaturity(exDate);
		
		double mtm = BlackScholes(CallPutFlag, S, X, T,	r, v);
		
		return mtm;

	}

	private double getYearsToMaturity(Calendar exDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	// The cumulative normal distribution function
	public double CND(double X) {
		double L, K, w;
		double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

		L = Math.abs(X);
		K = 1.0 / (1.0 + 0.2316419 * L);
		w = 1.0
				- 1.0
				/ Math.sqrt(2.0 * Math.PI)
				* Math.exp(-L * L / 2)
				* (a1 * K + a2 * K * K + a3 * Math.pow(K, 3) + a4
						* Math.pow(K, 4) + a5 * Math.pow(K, 5));

		if (X < 0.0) {
			w = 1.0 - w;
		}
		return w;
	}

}
