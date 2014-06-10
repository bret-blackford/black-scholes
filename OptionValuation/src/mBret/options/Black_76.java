package mBret.options;

/**
 * An implementation of the Black 76 option model.
 * The Black model (sometimes known as the Black-76 model) is a variant of 
 * the BlackScholes option pricing model. Its primary applications are for 
 * pricing bond options, interest rate caps / floors, and swaptions. It was 
 * first presented in a paper written by Fischer Black in 1976.
 * 
 * The Black formula is similar to the Black-Scholes formula for valuing stock 
 * options except that the spot price of the underlying is replaced by a 
 * discounted futures price F.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class Black_76 {

	
	// The Black and Scholes (1973) Stock option formula

		// S= Stock price
		// X=Strike price
		// T=Years to maturity
		// r= Risk-free rate
		// v=Volatility

		public double Black76(char CallPutFlag, double S, double X, double T,
				double r, double v) {
			
			double d1, d2;
			double result;

			d1 = (Math.log(S / X) + (r + v * v / 2) * T) / (v * Math.sqrt(T));
			d2 = d1 - v * Math.sqrt(T);

			if (CallPutFlag == 'c') {
				result =  S * CND(d1) - X * Math.exp(-r * T) * CND(d2);
			} else {
				result = X * Math.exp(-r * T) * CND(-d2) - S * CND(-d1);
			}
			
			System.out.print("in Black76 result-[" + result + "] " );
			System.out.println(CallPutFlag + ", $-" + S + ", x-" + X + ", T-" + T + ", r-" + r + ", v-" + v);
			
			return result;
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
