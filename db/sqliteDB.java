package db;


//import java.awt.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JLabel;

//import UI.select_person;

class Constants
{
	public static final boolean FALSE_VALUE = false;
	public static final boolean TRUE_VALUE = true;

	public static final String TABLE_NAME_PEOPLE = "people";
	public static final String TABLE_NAME_RELATION = "relation";
}

class Messages
{
	public static final String ERR_TABLE_CREATION = "sqliteDB Error: Error in table creation";
	public static final String ERR_PERSON_INSERTION = "sqliteDB Error: Inserting Person failed";
	public static final String ERR_RELATION_INSERTION = "sqliteDB Error: Inserting Relation failed";
	public static final String ERR_PERSON_DELETION = "sqliteDB Error: deleting Person failed";
	public static final String ERR_RELATION_DELETION = "sqliteDB Error: deleting Relation failed";
	public static final String ERR_PERSON_SELECTION = "sqliteDB Error: selection Person failed";
}


/* Singleton class
 * This class is the one that interacts with the DB
 * Contains all the sqlite commands exposing only 
 * clean interfaces outside.
 */
public class sqliteDB 
{
	
	public static final String SQLITE_CLASS_FORMAT =  "org.sqlite.JDBC";
	public static final String SQLITE_JDBC_DB = "jdbc:sqlite:batman.db";
	
	private static sqliteDB instance = null;
	
	private Connection conn;

	//Method to expose the singleton instance
	public static sqliteDB getInstance() throws Exception
	{
		if(instance == null)
			instance = new sqliteDB();
		return instance;

	}

	//Constructor
	public sqliteDB() throws Exception
	{
		boolean result = createTable(Constants.TABLE_NAME_PEOPLE);
		result = createTable(Constants.TABLE_NAME_RELATION);
		System.out.println("Creating table status "+result);
		
		//Create Relationship hashmap that stores Relation ships as strings
		//Relation.mapRelationNameToEnum();
	}
	
	private String constructCreateTableStmt(String table_name)
	{
		String dbStmt = new String();
		System.out.println("Constructing table "+table_name);
		if(table_name == Constants.TABLE_NAME_PEOPLE)
		{
			// create table people (TEXT f_name, TEXT l_Name, INT age, INT id);
			dbStmt = "create table "+Constants.TABLE_NAME_PEOPLE+" (f_name TEXT,l_name TEXT,age number,id number);" ;
		}
		else if(table_name == Constants.TABLE_NAME_RELATION)
		{
			// create table relation (INT rel_name, INTEGER p1_id, INTEGER p2_id);
			dbStmt = "create table "+Constants.TABLE_NAME_RELATION+" (rel_name number,p1_id number,p2_id number);";
		}
		System.out.println("Constructed statement: "+dbStmt);
		return dbStmt;
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
			System.out.println("Table to create:   "+table_name);

			stat = conn.createStatement();

			if(!isTableExists(table_name) )
			{
				String stmt = null;
				stmt = constructCreateTableStmt(table_name);
				if(stmt != null)
				{
					System.out.println("Creating table: Statement is: "+stmt);
					stat.executeUpdate(stmt);
				}
				else
				{
					System.out.println("Null statement for create table constructed");
				}
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
	
	public boolean insertIntoTableRelation(Relationships relation, Person p1, Person p2) throws Exception
	{
		return insertIntoTableRelationWithId (relation, p1.getId(), p2.getId());
	}

	public boolean insertIntoTableRelationWithId(Relationships relation, int id1, int id2) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			
			stat = conn.createStatement();
			String insertString = "insert into "+Constants.TABLE_NAME_RELATION+" values ("+relation+","+id1+","+id2+");";
			conn.setAutoCommit(false);
			stat.executeUpdate(insertString);
			conn.setAutoCommit(true);
			
			return Constants.TRUE_VALUE;
		}
		catch (Exception e) 
		{
			System.out.println(Messages.ERR_RELATION_INSERTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}
	
	public boolean insertIntoTablePerson(String f_name, String l_name, int age, int id) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			
			stat = conn.createStatement();
			// insert into tablename (collist) values (colvalues);
			String insertString = "insert into "+Constants.TABLE_NAME_PEOPLE+" values ('"+f_name+"','"+l_name+"',"+age+","+id+");" ;
			
			conn.setAutoCommit(false);
			stat.executeUpdate(insertString);
			conn.setAutoCommit(true);
			
			return Constants.TRUE_VALUE;
		}
		catch (Exception e) 
		{
			System.out.println(e);
			System.out.println(Messages.ERR_PERSON_INSERTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}
	
	public boolean selectFromTablePerson() throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			
			stat = conn.createStatement();
			 ResultSet rs = stat.executeQuery("select * from people;");
		        while (rs.next()) {
		        	//(f_name,l_name,age,id)
		        	//JLabel lblNewLabel = new JLabel("");
		        	//lblNewLabel.setText(rs.getString("f_name"));
		            System.out.println("f-name = " + rs.getString("f_name"));
		            System.out.println("l-name = " + rs.getString("l_name"));
					System.out.println("l-name = " + rs.getInt("age"));
		            System.out.println("l-name = " + rs.getInt("id"));
		        }
		}
		catch (Exception e) 
		{
			System.out.println(e);
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}
	
	public static String[] headings(ResultSet rs){  
		   String[] col;  
		   try {  
		      ResultSetMetaData metadata = rs.getMetaData();  
		      col = new String[metadata.getColumnCount()];  
		  
		      //int numcols;
		      int numcols = rs.getMetaData().getColumnCount();
			for(int count = 0; count < numcols; count++) {               
		         col[count] = metadata.getColumnLabel(count + 1);  
		      }  
		   } catch(Exception e) {  
		      throw new RuntimeException(e);  
		   }    
		   return col;  
		}
	
	public static Object[][] data(ResultSet rs){  
		   // The code below is basically the same as yours; I've just used a  
		   // List for simplicity.  
		   List<Object[]> data = new ArrayList<Object[]>();  
		   try {  
		      int numcols = rs.getMetaData().getColumnCount();  
		  
		      while (rs.next()) {    
		         Object [] rowData = new Object[numcols];    
		         for (int i = 0; i < rowData.length; ++i) {  
		            rowData[i] = rs.getObject(i+1);    
		         }  
		         data.add(rowData);  
		      }  
		   } catch(Exception e) {  
		      throw new RuntimeException(e);  
		   }    
		   return (Object[][]) data.toArray();  
		}
	
	public boolean deleteFromTablePerson(int id) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			
			stat = conn.createStatement();
			String deleteString = "delete from "+Constants.TABLE_NAME_PEOPLE+" where id = "+id+";" ;
			
			conn.setAutoCommit(false);
			stat.executeUpdate(deleteString);
			conn.setAutoCommit(true);
			
			return Constants.TRUE_VALUE;
		}
		catch (Exception e) 
		{
			System.out.println(e);
			System.out.println(Messages.ERR_PERSON_DELETION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}

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
		System.out.println("Checkin for existence of table "+Table_Name );
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
