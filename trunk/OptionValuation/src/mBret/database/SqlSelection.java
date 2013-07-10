package mBret.database;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * Simple parent class
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class SqlSelection {


	public String getSql() {
		return "SELECT * FROM *";
	}
	
	public String printHeaders() {
		return "DEFAULT";
	}
	
	public Vector processResultSet(ResultSet result) {
		return new Vector();
	}
}
