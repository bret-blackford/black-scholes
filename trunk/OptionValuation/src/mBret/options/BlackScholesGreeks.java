package mBret.options;

/**
 * Delta - measures the rate of change of option value with respect to changes in the underlying asset's price.
 * Gamma - measures the rate of change in the delta with respect to changes in the underlying price.
 * Vega - measures sensitivity to volatility. Vega is typically expressed as the amount of money per underlying 
 *   share that the option's value will gain or lose as volatility rises or falls by 1%.
 * Theta - measures the sensitivity of the value of the derivative to the passage of time: the "time decay."
 * Rho - measures sensitivity to the interest rate:
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class BlackScholesGreeks {

	private static final double P = 0.2316419;
	private static final double B1 = 0.319381530;
	private static final double B2 = -0.356563782;
	private static final double B3 = 1.781477937;
	private static final double B4 = -1.821255978;
	private static final double B5 = 1.330274429;
	
	public static OptionGreeks calculate(OptionDetails req) {
		
		OptionGreeks resp = new OptionGreeks();
		
		double[] array = calculate(req.callOption, req.spotPriceOfUnderlying,
				req.strikePrice, req.riskFreeInterestRate, req.timeToExpire,
				req.volatility);
		
		resp.delta = array[1];
		resp.gamma = array[2];
		resp.vega = array[3];
		resp.theta = ( array[4] / 1 );
		resp.rho = array[5];
		
		return resp;
	}

	public static double[] calculate(boolean c, double s, double k, double r,
			double t, double v) {

		double[] p = new double[6];

		double d1 = d1(s, k, r, t, v);
		double d2 = d2(s, k, r, t, v);

		double sd1 = standardNormalDistribution(d1);
		double cd1 = cumulativeDistribution(d1, sd1);
		double thetaLeft = -(s * sd1 * v) / (2 * Math.sqrt(t));

		if (c) {

			double cd2 = cumulativeDistribution(d2);

			// price
			p[0] = s * cd1 - k * Math.exp(-r * t) * cd2;

			// delta
			p[1] = cd1;

			// theta
			double thetaRight = r * k * Math.exp(-r * t) * cd2;
			p[4] = thetaLeft - thetaRight;

			// rho
			p[5] = k * t * Math.exp(-r * t) * cd2;

		} else {

			double pcd1 = cumulativeDistribution(-d1);
			double pcd2 = cumulativeDistribution(-d2);

			// price
			p[0] = k * Math.exp(-r * t) * pcd2 - s * pcd1;

			// delta
			p[1] = cd1 - 1;

			// theta
			double thetaRight = r * k * Math.exp(-r * t) * pcd2;
			p[4] = thetaLeft + thetaRight;

			// rho
			p[5] = -k * t * Math.exp(-r * t) * pcd2;

		}

		// gamma
		p[2] = sd1 / (s * v * Math.sqrt(t));

		// vega
		p[3] = s * sd1 * Math.sqrt(t);

		return p;

	}

	private static double d1(double s, double k, double r, double t, double v) {
		double top = Math.log(s / k) + (r + Math.pow(v, 2) / 2) * t;
		double bottom = v * Math.sqrt(t);

		return top / bottom;
	}

	private static double d2(double s, double k, double r, double t, double v) {
		return d1(s, k, r, t, v) - v * Math.sqrt(t);
	}

	public static double cumulativeDistribution(double x) {
		return cumulativeDistribution(x, standardNormalDistribution(x));
	}

	public static double cumulativeDistribution(double x, double sdx) {

		double t = 1 / (1 + P * Math.abs(x));
		double t1 = B1 * Math.pow(t, 1);
		double t2 = B2 * Math.pow(t, 2);
		double t3 = B3 * Math.pow(t, 3);
		double t4 = B4 * Math.pow(t, 4);
		double t5 = B5 * Math.pow(t, 5);
		double b = t1 + t2 + t3 + t4 + t5;
		double cd = 1 - sdx * b;

		return x < 0 ? 1 - cd : cd;
	}

	public static double standardNormalDistribution(double x) {
		double top = Math.exp(-0.5 * Math.pow(x, 2));
		double bottom = Math.sqrt(2 * Math.PI);

		return top / bottom;
	}

}
