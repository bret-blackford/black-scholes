package mBret.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Class handles the specific SQL calls necessary to obtain the
 * option trade information from Allegro 7.6.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class SelectOptions extends SqlSelection {

	public String getSql() {

		String sql = "";
		StringBuffer select = new StringBuffer();

		select.append("SELECT aval.trade, aval.tradebook, aval.product, ");
		select.append("to_char(aval.settlementdate, \'yyyy-mm-dd\') as settlementdate, ");
		select.append("aval.counterparty, aval.execution, aval.positiontype, ");
		select.append("aval.pricestatus, aval.quantity, aval.pricequantity, aval.VALUE, ");
		select.append("aval.optionvalue, aval.marketprice, ");
		select.append("aval.delta, aval.gamma, aval.theta, aval.vega, ");
		select.append("aval.rho, aval.volatility, aval.strikeprice, ");
		select.append("to_char(aval.expirationdate, \'yyyy-mm-dd\') as expirationdate, ");
		select.append("to_char(aval.begtime, \'yyyy-mm-dd\') as begtime, ");
		select.append("to_char(aval.endtime, \'yyyy-mm-dd\') as endtime, ");
		select.append("aval.price, aval.LOCATION, ");
		select.append("apos.accountingmethod, ");
		select.append("aval.description");
		select.append(" FROM allegro.valuationdetail aval, allegro.POSITION apos");
		select.append("  WHERE aval.valuation IN (SELECT MAX (valuu.valuation)");
		select.append(" FROM allegro.valuation valuu");
		select.append("  WHERE valuu.valuationmode = \'MTM Ton\')");
		select.append("  AND aval.trade = apos.trade");
		select.append("  AND UPPER (aval.execution) LIKE \'%OPTION%\' ");
		select.append("  AND aval.description NOT LIKE \'%Clear%\' ");
		select.append("  AND aval.description NOT LIKE \'%Fee%\' ");
		select.append("  AND aval.description NOT LIKE \'%Adj%\' ");
		select.append("  AND aval.product NOT LIKE \'Heat%\' ");
		select.append("  AND UPPER (aval.execution) LIKE \'%COAL%\'");
		select.append("  AND aval.endtime > (SYSDATE - 15)");
		select.append(" ORDER BY aval.trade DESC, aval.begtime");

		sql = select.toString();
		return sql;
	}

	public Vector processResultSet(ResultSet result) {
		
		
		Vector dataVector = new Vector();

		try {
			while (result.next()) {

				SelectOptionsData data = new SelectOptionsData();
				
				data.trade = result.getString("trade");
				data.tradebook = result.getString("tradebook");
				data.product = result.getString("product");
				data.settlementDate = result.getString("settlementdate");
				data.counterparty = result.getString("counterparty");
				data.execution = result.getString("execution");
				data.positionType = result.getString("positiontype");
				data.priceStatus = result.getString("pricestatus");
				data.quantity = result.getString("quantity");
				data.priceQuantity = result.getString("pricequantity");
				data.value = result.getString("VALUE");
				data.optionValue = result.getString("optionvalue");
				data.marketPrice = result.getString("marketprice");
				data.delta = result.getString("delta");
				data.gamma = result.getString("gamma");
				data.theta = result.getString("theta");
				data.vega = result.getString("vega");
				data.rho = result.getString("rho");
				data.volatility = result.getString("volatility");
				data.strikePrice = result.getString("strikeprice");
				data.expDate = result.getString("expirationdate");
				data.begTime = result.getString("begtime");
				data.endTime = result.getString("endtime");
				data.price = result.getString("price");
				data.location = result.getString("location");
				data.accountingMethod = result.getString("accountingmethod");
				data.description = result.getString("description");
				
				if( data.description != null) {
					parseDescription(data);
				} 
				
				if( data.execution.contains("Physical")) {
					//System.out.println("PHYSICAL - [" + data.execution + "]");
				} else if( data.execution.contains("Financial")) {
					//System.out.println("FINANCIAL - [" + data.execution + "]");
				}else {
					//should not get here
					System.out.println("PROBLEM in SelectOptions.processResultSet()");
				}
				
				//System.out.println(this.printHeaders());
				//System.out.println(this.toString());
				
				dataVector.add(data);
			}
		} catch (SQLException e) {

			System.out.println("in SelectOptions.processResultSet() and encountered an error *****");
			e.printStackTrace();
		}
		
		return dataVector;
	}
	
	
	public void parseDescription(SelectOptionsData data) {
		
		String delims = "[ :]+";
		String[] tokens = data.description.split(delims);
		
		//System.out.println("Description = [" + data.description + "]");
		
		
		for (int i = 0; i < tokens.length; i++) {
			if ( tokens[i].equalsIgnoreCase("Strike") ) {
				data.strike_s = tokens[i + 1];
			}
			if (tokens[i].equalsIgnoreCase("Market") ) {
				data.market_s = tokens[i + 1];
			}
			if (tokens[i].equalsIgnoreCase("IntRate=") ) {
				data.intRate_s = tokens[i + 1];
			}
			if (tokens[i].equalsIgnoreCase("Expiration") ) {
				data.expTime_s = tokens[i + 1];
			}
			
			//System.out.println("in SelectOptions.parseDescription()");
			//System.out.println(data.toString());
		}

	}

}
