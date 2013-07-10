package mBret.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Class written to provide jdbc Db access to an
 * Allegro 7.6 table in Db PRODACI1
 * The class is not generic but written specifically for
 * use on the Arch Coal Allegro Db implementation. To make
 * more generic you would need to pass in the Db url string.
 * 
 * jdbc requires that the ojdbc6.jar be added to the Project Libraries - Build Path
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class DbAccess {
	
	
	Connection conn;
	Vector resultVector = new Vector();
	
    String serverName = "acidb1.aci.corp.net";
    String portNumber = "1522";
    String sid = "prodaci1";
    String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
    String username = "blackfordm";
    String password = "--";
    
    
    public DbAccess(Properties _props) {
    	
    	serverName = _props.getProperty("db.server");
    	portNumber = _props.getProperty("db.port");
    	sid = _props.getProperty("db.sid");
    	url = _props.getProperty("db.dataSource");
    	username = _props.getProperty("db.userName");
    	password = _props.getProperty("db.passWord");
    }
    

    
    
	private void makeConnection() throws ClassNotFoundException, SQLException {

		System.out.println("in DbAccess.makeConnection()");
		System.out.println("Going to try the connection now ...");
		System.out.println("       url-[" + url + "]");
		System.out.println("  username-[" + username + "]");
		//System.out.println("  password-[" + password + "]");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("in DbAccess.makeConnection() ... ERROR !!");
			e.printStackTrace();
		}
		System.out.println("Connection done !!");
	}
    
    public Vector makeDbCall(SqlSelection sqlObject) throws SQLException, ClassNotFoundException {
        
    	System.out.println("in DbAccess.makeDbCall()");
    	
    	
    	makeConnection();
    	
    	String sql = sqlObject.getSql();
        System.out.println("in DbAcess.makeDbCall(" + sql + ")" );
        
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
       ResultSet  result = preStatement.executeQuery();
       
       Vector dataVector = sqlObject.processResultSet(result);
       System.out.println(sqlObject.toString());
       
       return dataVector;
       
    }
    
    public ResultSet makeSqlCall(String sql) throws ClassNotFoundException, SQLException {
    	
    	//System.out.println("in DbAccess.makeSqlCall()");
    	//System.out.println("SQL=[" + sql + "]");
    	
    	//makeConnection();
    	PreparedStatement preStatement = conn.prepareStatement(sql);
    	ResultSet  result = preStatement.executeQuery();
    	return result;
    	
    }

}
