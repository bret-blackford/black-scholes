package mBret.options;
import java.util.Date;

/**
 * Data class used to manage the specific data items for one
 * option transaction.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class OptionDetails {

	 Boolean callOption;
	 double spotPriceOfUnderlying;
	 double strikePrice;
	 double riskFreeInterestRate;
	 double timeToExpire;
	 double volatility;
	 Date valuationDate;
	 Date ExpDate;

	 double optionValue;
	 double delta;
	 double theta;
	 double rho;
	 double gamma;
	 double vega;

	public OptionDetails(Boolean call, double s, double k, double r, double t,
			double v) {

			callOption = call;
			spotPriceOfUnderlying = s;
			strikePrice = k;
			riskFreeInterestRate = r;
			timeToExpire = t;
			volatility = v;
	}
	
	public String toString() {
		
		String out = "spot price [" + spotPriceOfUnderlying + "] strike [";
		out += strikePrice + "] int rate [" + riskFreeInterestRate + "] expire [";
		out += timeToExpire + "] vol [" + volatility + "] \n";
		
		out += "option value-[" + optionValue + "] delta-[" + delta + "] theta-[";
		out += theta + "] rho-[" + rho + "] gamma-[" + gamma + "] vega-[" + vega + "]";
		
		return out;
	}
}
