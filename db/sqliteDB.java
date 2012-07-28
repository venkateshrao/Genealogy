import java.awt.FlowLayout;
import java.sql.DatabaseMetaData;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class Constants
{
	public static final boolean FALSE_VALUE = false;
	public static final boolean TRUE_VALUE = true;

	public static final String TABLE_NAME_PEOPLE = "people";
}

class Messages
{
	public static final String ERR_TABLE_CREATION = "sqliteDB Error: Error in table creation";
}

/*
class Persist
{
	private sqliteDB getDBHandle()
	{
		sqliteDB dbHandle = sqliteDB.getInstance();
		return dbHandle;
	}

	public boolean createNewPerson(String person_name, int person_age)
	{
		sqliteDB dbHandle = getDBHandle();
		dbHandle.insertIntoTable(Constants.TABLE_NAME_PEOPLE);
		return true;
	}
}
*/

class sqliteDB
{
	public static final String SQLITE_CLASS_FORMAT =  "org.sqlite.JDBC";
	public static final String SQLITE_JDBC_DB = "jdbc:sqlite:jsoftsol.db";

	private static sqliteDB instance = null;
	
	private Connection conn;

	public static sqliteDB getInstance() throws Exception
	{
		if(instance == null)
			instance = new sqliteDB();
		return instance;

	}

	protected sqliteDB() throws Exception
	{
		//printf "Constructor"
		boolean result = createTable(Constants.TABLE_NAME_PEOPLE);
		
		System.out.println(result);
	}


	public boolean createTable(String table_name) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			System.out.println("table name is"+table_name);

			stat = conn.createStatement();

			if(!isTableExists(table_name) )
			{
				stat.executeUpdate("create table people (F_name, L_name);");
			}
			return Constants.TRUE_VALUE;
		}
		catch (Exception e){
			System.out.println(Messages.ERR_TABLE_CREATION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}

	/*
	public boolean insertIntoTable(String table_name)
	{
		String insertString = "insert into"+table_name+"values (?,?)" ;
		PreparedStatement prep = conn.prepareStatement(insertString);

		prep.setString(1 , str);
		prep.setString(2 , str2);
		prep.addBatch();

		conn.setAutoCommit(false);
		prep.executeBatch();
		conn.setAutoCommit(true);
	}
	*/

	public Connection getConnection()
	{
		// TODO Auto-generated method stub
		try{
			Class.forName(SQLITE_CLASS_FORMAT);
			return DriverManager.getConnection(SQLITE_JDBC_DB);
			//Class.forName( "org.sqlite.JDBC");
			//return DriverManager.getConnection( "jdbc:sqlite:jsoftsol.db");
		}
		catch(Exception e){ System.out.println("Error Connection ");}
		return null;
	}


	public boolean isTableExists(String Table_Name) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("enterd istableexists table name is"+Table_Name );
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			stat = conn.createStatement();
			//return false;
			rs = stat.executeQuery("select name from sqlite_master where type = 'table' and name = '"+Table_Name+"';");
			return rs.next();
		}
		catch (Exception e){
			System.out.println("error checking table exists");

		}
		finally{
			rs.close();
			stat.close();
			conn.close();
		}
		return false;
	}
}
