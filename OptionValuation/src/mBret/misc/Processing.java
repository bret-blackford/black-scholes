package mBret.misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import mBret.options.BlackScholesFormula;
import mBret.options.BlackScholesGreeks;
import mBret.options.BlackScholes_1973;
import mBret.options.Black_76;
import mBret.options.OptionDetails;
import mBret.options.OptionGreeks;

import mBret.database.DbAccess;
import mBret.database.SelectOptions;
import mBret.database.SelectOptionsData;

/**
 * Used to manage the processing of the option model calculations.
 * 1) get data from Db, 2) calculate option valuations, 3) print output to file
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class Processing {
	
	Vector dataVector;
	Vector dataVector2 = new Vector();
	
	Properties props = null;
	
	//test data
	char callPut = 'c';
	double stockPrice = 88.45;
	double strike = 85.00;
	double timeToExpire = 0.526846;
	double intRate = 0.001009;
	double vol = .13425;
	
	boolean call = true;
	
	public Processing(Properties _props) {
		props = _props;
	}
	
	public void getData(){
		
		System.out.println("in Processing.getData()");
		
		SelectOptions selectObj = new SelectOptions();
		String sql = selectObj.getSql();
		String sqlFin;
		String sqlPhys;
		System.out.println(sql);
		
		ResultSet rs;
		
		System.out.println("Getting ready for the hard stuff ...");
		DbAccess db = new DbAccess(props);
		try {
			dataVector = db.makeDbCall(selectObj);
			
			// get an Iterator object for Vector using iterator() method.
			Iterator itr = dataVector.iterator();

			// use hasNext() and next() methods of Iterator to iterate through the elements
			System.out.println("Iterating through Vector elements...");
			while (itr.hasNext()) {
				
				sqlFin= "select optiontype, optionstyle  from allegro.commodityposition where TRADE = ";
				sqlPhys= "select optiontype, optionstyle from allegro.PHYSICALPOSITION where TRADE = ";
				
				SelectOptionsData data = (SelectOptionsData) itr.next();
				//System.out.println(" ----------------------------------");
				
				
				if( data.execution.contains("Physical")) {
					//System.out.println("PHYSICAL - [" + data.execution + "]");
					sqlPhys += data.trade;
					sql = sqlPhys;
				} else if( data.execution.contains("Financial")) {
					//System.out.println("FINANCIAL - [" + data.execution + "]");
					sqlFin += data.trade;
					sql = sqlFin;
				}else {
					//should not get here
					System.out.println("PROBLEM in SelectOptions.processResultSet()");
				}	
				
				rs = db.makeSqlCall(sql);
				while (rs.next()) {
					data.optionStyle = rs.getString("optionStyle");
					data.optionType = rs.getString("optionType");
				}
				
				//now, calculate option value
				double priceQuant = new Double(data.priceQuantity);
				priceQuant = 1; // can change !!
				
				
				//put the populated SelectOptionsData object in the vector
				dataVector2.add(data);

			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(" *** ERROR 1 *** ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(" *** ERROR 2 *** ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(" *** ERROR 3 *** ");
			e.printStackTrace();
		}
		
	}
	
	
	public void runOptionValuations() {
		
		System.out.println("in Processing.runOptionValuations()");
				
		// get an Iterator object for Vector using iterator() method.
		Iterator itr = dataVector2.iterator();

		// use hasNext() and next() methods of Iterator to iterate through the elements
		System.out.println("  Iterating through Vector elements...");
		while (itr.hasNext()) {
			SelectOptionsData data = (SelectOptionsData) itr.next();
			System.out.println("  Processing trade[" + data.trade + "]");
			
			if( data.optionType.equalsIgnoreCase("CALL") ) {
				call = true;
				callPut = 'c';
			} else {
				call = false;
				callPut = 'p';
			}
			
			if (data.market_s != null) {
				stockPrice = new Double(data.market_s);
			}
			if (data.strike_s != null) {
				strike = new Double(data.strike_s);
			}
			if (data.expTime_s != null) {
				timeToExpire = new Double(data.expTime_s);
			}
			if (data.intRate_s != null) {
				intRate = new Double(data.intRate_s);
			}
			if (data.volatility != null) {
				vol = new Double(data.volatility);
			}
			
			System.out.println("  S-[" + stockPrice + "] x-[" + strike + "] exp-[" + timeToExpire + "] int-[" + intRate + "] vol-[" + vol + "]");
			
			//===========================
			//Black_76 black = new Black_76();
			double optionValue = new Black_76().Black76(callPut, stockPrice, strike, timeToExpire, intRate, vol);
			data.optionValue_Black = new Double(optionValue).toString();
			
			optionValue = new BlackScholesFormula().calculate(call, stockPrice, strike, intRate, timeToExpire, vol);
			data.optionValue_BlackScholes = new Double(optionValue).toString();
			
			optionValue = new BlackScholes_1973().BlackScholes(callPut, stockPrice, strike, timeToExpire, intRate, vol);
		}
		
	}
	
	
	/**
	 * STILL NEED TO WORK OUT PROBLEMS HERE
	 * GREEKS NOT CALCULATING CORRECTLY
	 */
	public void getGreeks() {
		
		System.out.println("in Processing.getGreeks()");
		
		// get an Iterator object for Vector using iterator() method.
		Iterator itr = dataVector2.iterator();

		// use hasNext() and next() methods of Iterator to iterate through the elements
		System.out.println("  Iterating through Vector elements...");
		while (itr.hasNext()) {
			SelectOptionsData data = (SelectOptionsData) itr.next();
			System.out.println("  Processing trade[" + data.trade + "]");
			System.out.println("  S-[" + stockPrice + "] x-[" + strike + "] exp-[" + timeToExpire + "] int-[" + intRate + "] vol-[" + vol + "]");
			
			Double s = new Double( CheckString.check(data.price) );
			Double k = new Double( CheckString.check(data.strike_s) );
			Double r = new Double( CheckString.check(data.intRate_s) );
			Double t = new Double( CheckString.check(data.expTime_s) );
			Double v = new Double( CheckString.check(data.volatility) );
			
			if( data.optionType.equalsIgnoreCase("CALL") ) {
				call = true;
				callPut = 'c';
			} else {
				call = false;
				callPut = 'p';
			}
						
			try {
				OptionDetails optionDetail = new OptionDetails(call,s,k,r,t,v);
				
				OptionGreeks greeks = new BlackScholesGreeks().calculate(optionDetail);
				System.out.println("  GREEKS: " + greeks.toString());
				
				double[] check = new BlackScholesGreeks().calculate(call, s, k, r, t, v);
				System.out.println("  delta-[" + check[1] + "]");
				System.out.println("  gamma-[" + check[2] + "]");
				System.out.println("   vega-[" + check[3] + "]");
				System.out.println("  theta-[" + check[4] + "]");
				System.out.println("    rho-[" + check[5] + "]");
				
				//put greeks in SelectOptionsData from vector
				data.delta_s = new Double(greeks.delta).toString();
				data.gamma_s = new Double(greeks.gamma).toString();
				data.vega_s = new Double(greeks.vega).toString();
				data.theta_s = new Double(greeks.theta).toString();
				data.rho_s = new Double(greeks.rho).toString();
				
			} catch (Exception e) {
				System.out.println("EXCEPTION in Procesing.getGreeks()");
				e.printStackTrace();
			}		
		}
		
		System.out.println("leaving Procesing.getGreeks()");
	}
	
	
	public void printResultsToFile() {
		
		System.out.println("in Processing.printResultsToFile()");
		System.out.println(new SelectOptionsData().printHeaders() );	
		
		WriteToFile write = new WriteToFile(props);
		write.write(dataVector2);
		
		System.out.println("finished with Processing.printResultsToFile()");
	}
	
	
	public void printResultsToExcel() {
		System.out.println("in Processing.printResultsToExcel()");
		
		WriteToExcel write = new WriteToExcel(props, dataVector2);
		//WriteToExcel_OLD write_v2 = new WriteToExcel_OLD(props, dataVector2);
	}

	
}
