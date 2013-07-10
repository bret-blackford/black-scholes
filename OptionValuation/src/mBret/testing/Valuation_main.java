package mBret.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import mBret.misc.Processing;
import mBret.misc.StaticInfo;
import mBret.database.DbAccess;
import mBret.database.SelectOptions;
import mBret.database.SelectOptionsData;
import mBret.options.BlackScholesFormula;
import mBret.options.BlackScholes_1973;
import mBret.options.Black_76;


/**
 *  * Application intended to check the option valuation using Black and Black-Scholes 
 * The model was first articulated by Fischer Black and Myron Scholes in their 1973 paper, 
 * "The Pricing of Options and Corporate Liabilities", published in the Journal of Political Economy.
 * 
 * @author mblackford mBret Bret Blackford (2013)
 * @author mblackford
 *
 */
public class Valuation_main {

	public static Properties props = null;
	
/**
 * @author mblackford mBret Bret Blackford (2013)
 * @param args [User should pass in Db ID (for PRODACI1) and password]
 */
	public static void main(String[] args) {


		props = new Properties();
		if( args != null && args.length > 0 ){
			try {
				props.load( new FileInputStream(args[0]) );
				StaticInfo.setProperties(props);
			} catch (FileNotFoundException e) {
				System.out.println("DUDE! No such file !");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Unknown error");
				e.printStackTrace();
			}
		} //end of IF() stmt
		
		System.out.println("    db.driver[" + props.getProperty("db.driver") + "]");
		System.out.println("db.datasource[" + props.getProperty("db.dataSource") + "]");
		
		Processing process = new Processing(props);
		process.getData();
		process.runOptionValuations();
		//process.getGreeks();
		process.printResultsToFile();
		process.printResultsToExcel();

		System.out.println();
		System.out.println(" FINISHED");
	}

}
