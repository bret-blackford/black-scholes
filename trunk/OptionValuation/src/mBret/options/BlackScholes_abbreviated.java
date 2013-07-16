package options;

import org.apache.commons.math3.distribution.NormalDistribution;


/**
 *  @author mblackford  mBret Michael Bret Blackford
 * Class from Jernej Kovse -- http://www1.relacija.com/?p=57 
 * 
 * To compile this, you will need the Apache Common Math library (due to 
 * the NormalDistributionImpl class).
 * 
 * an example of Java implementation of the Black Scholes formula for 
 * valuing call options. The formula is used to value a European call 
 * option on a non-dividend paying stock.
 */
public class BlackScholes_abbreviated {

	
	/**
	 *     p: current stock price, i.e., spot price 
	 *    ep: strike (exercise) price
	 * stdev: standard deviation of continuously compounded annual returns, i.e., volatility 
	 *     t: remaining lifetime of the option in years 
	 *     r: continuously compounded risk-free rate
	 * 
	 */
	public static double blackScholesCall(double p, double ep, double stdev,
			double t, double r)  {
		
		double d1 = (Math.log(p / ep) + (r + Math.pow(stdev, 2) / 2) * t)
				/ (stdev * Math.sqrt(t));
		
		double d2 = d1 - stdev * Math.sqrt(t);
		
		NormalDistribution n = new NormalDistribution();
		
		double result = p * n.cumulativeProbability(d1) - ep * Math.exp(-r * t)
				* n.cumulativeProbability(d2);
		
		return result;
	}
}
