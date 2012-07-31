
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}


/* Singleton class
 * This class is the one that interacts with the DB
 * Contains all the sqlite commands exposing only 
 * clean interfaces outside.
 */
class sqliteDB 
{
	
	public static final String SQLITE_CLASS_FORMAT =  "org.sqlite.JDBC";
	public static final String SQLITE_JDBC_DB = "jdbc:sqlite:gene.db";
	
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
	protected sqliteDB() throws Exception
	{
		boolean result = createTable(Constants.TABLE_NAME_PEOPLE);
		result = createTable(Constants.TABLE_NAME_RELATION);
		
		System.out.println("Creating table status "+result);
	}
	
	private String constructCreateTableStmt(String table_name)
	{
		String dbStmt = new String();
		System.out.println("Constructing table "+table_name);
		if(table_name == Constants.TABLE_NAME_PEOPLE)
		{
			// create table people (TEXT f_name, TEXT l_Name, INT age, INT id);
			dbStmt = "create table "+Constants.TABLE_NAME_PEOPLE+" (f_name,l_name,age,id);" ;
		}
		else if(table_name == Constants.TABLE_NAME_RELATION)
		{
			// create table relation (INT rel_name, INTEGER p1_id, INTEGER p2_id);
			dbStmt = "create table "+Constants.TABLE_NAME_RELATION+" (rel_name,p1_id,p2_id);";
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
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			
			stat = conn.createStatement();
			String insertString = "insert into "+Constants.TABLE_NAME_RELATION+" values ("+relation+","+p1.getId()+","+p2.getId()+");";
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
			
			String insertString = "insert into "+Constants.TABLE_NAME_PEOPLE+" values ("+f_name+","+l_name+","+age+","+id+");" ;
			
			conn.setAutoCommit(false);
			stat.executeUpdate(insertString);
			conn.setAutoCommit(true);
			
			return Constants.TRUE_VALUE;
		}
		catch (Exception e) 
		{
			System.out.println(Messages.ERR_PERSON_INSERTION);
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
