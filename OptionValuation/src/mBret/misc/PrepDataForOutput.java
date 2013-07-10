package mBret.misc;

import java.util.Iterator;
import java.util.Vector;

import mBret.database.SelectOptionsData;

/**
 * Helper class to prepare data for Excel output.
 * @author mblackford M Bret Blackford (mBret)
 *
 */
public class PrepDataForOutput {

	Vector preppedData = new Vector();
	
	/**
	 * Creates a Vector of Strings containing the data elements of 
	 * the SelectOptionsData class.
	 * 
	 * @param _vec
	 * @return
	 */
	public Vector prepData(Vector _vec) {
		
		prepHeaders();
		
		// get an Iterator object for Vector using iterator() method.
		Iterator itr = _vec.iterator();
		
		System.out.println("Iterating through Vector elements...");
		while (itr.hasNext()) {
			Vector vecROW = new Vector();
			
			SelectOptionsData data = (SelectOptionsData) itr.next();
			
			Vector<String> columnVEC = new Vector<String>();
			
			columnVEC.add(data.trade);
			columnVEC.add(data.tradebook);
			columnVEC.add(data.product);
			columnVEC.add(data.settlementDate);
			columnVEC.add(data.counterparty);
			columnVEC.add(data.execution);
			columnVEC.add(data.positionType);
			columnVEC.add(data.priceStatus);
			columnVEC.add(data.quantity);
			columnVEC.add(data.priceQuantity);
			columnVEC.add(data.value);
			columnVEC.add(data.optionValue);
			columnVEC.add(data.marketPrice);
			columnVEC.add(data.optionValue_Black);
			columnVEC.add(data.optionValue_BlackScholes);
			columnVEC.add(data.delta);
			columnVEC.add(data.gamma);
			columnVEC.add(data.theta);
			columnVEC.add(data.vega);
			columnVEC.add(data.rho);
			columnVEC.add(data.delta_s);
			columnVEC.add(data.gamma_s);
			columnVEC.add(data.theta_s);
			columnVEC.add(data.vega_s);
			columnVEC.add(data.rho_s);
			columnVEC.add(data.volatility);
			columnVEC.add(data.strikePrice);
			columnVEC.add(data.expDate);
			columnVEC.add(data.begTime);
			columnVEC.add(data.endTime);
			columnVEC.add(data.price);
			columnVEC.add(data.location);
			columnVEC.add(data.optionType);
			columnVEC.add(data.optionStyle);
			columnVEC.add(data.accountingMethod);
			columnVEC.add(data.strike_s);
			columnVEC.add(data.market_s);
			columnVEC.add(data.intRate_s);
			columnVEC.add(data.expTime_s);
			columnVEC.add(data.expTime);
			columnVEC.add(data.description);
			
			preppedData.add(columnVEC);
		}
		
		return preppedData;
	}
	
	/**
	 * Add String names for all column headers in the 
	 * Excel spreadsheet.
	 * @return
	 */
	public Vector<String> prepHeaders() {
		
		Vector<String> columnHeaders = new Vector<String>();
		
		columnHeaders.add("A.TRADE");
		columnHeaders.add("B.TRADEBOOK");
		columnHeaders.add("C.PRODUCT");
		columnHeaders.add("D.SettlementDATE");
		columnHeaders.add("E.CounterParty");
		columnHeaders.add("F.EXECUTION");
		columnHeaders.add("G.PositionType");
		columnHeaders.add("H.PriceStatus");
		columnHeaders.add("I.QUANTITY");
		columnHeaders.add("J.PriceQUANTITY");
		columnHeaders.add("K.VALUE");
		columnHeaders.add("L.OptionValue");
		columnHeaders.add("M.MarketPrice");
		columnHeaders.add("N.OptionValue_BLACK");
		columnHeaders.add("O.OptionValue_BlackScholes");
		columnHeaders.add("P.DELTA");
		columnHeaders.add("Q.GAMMA");
		columnHeaders.add("R.THETA");
		columnHeaders.add("S.VEGA");
		columnHeaders.add("T.RHO");
		columnHeaders.add("U.delta_s");
		columnHeaders.add("V.gamma_s");
		columnHeaders.add("W.theta_s");
		columnHeaders.add("X.vega_s");
		columnHeaders.add("Y.rho_s");
		columnHeaders.add("Z.VOLATILITY");
		columnHeaders.add("AA.StrikePrice");
		columnHeaders.add("AB.ExpDate");
		columnHeaders.add("AC.begTime");
		columnHeaders.add("AD.endTime");
		columnHeaders.add("AE.PRICE");
		columnHeaders.add("AF.LOCATION");
		columnHeaders.add("AG.OptionType");
		columnHeaders.add("AH.OptionStyle");
		columnHeaders.add("AI.AcctgMethod");
		columnHeaders.add("AJ.strike_s");
		columnHeaders.add("AK.market_s");
		columnHeaders.add("AL.intRate_s");
		columnHeaders.add("AM.expTime_s");
		columnHeaders.add("AN.expTime");
		columnHeaders.add("AO.DESCRIPTION");
		
		preppedData.add(columnHeaders);
		
		return columnHeaders;
	}

}
