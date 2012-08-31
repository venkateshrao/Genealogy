package db;


//import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JLabel;

import UI.new_jtable;
import UI.relation_form;
import UI.relation_jtable;
//import UI.jtable;
import db.Person;
import UI.GraphJFaceSnippet2;
//import net.proteanit.sql.DbUtils;
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
	public static final String ERR_DB_CONNECTION_FAILURE = "sqliteDB Error: DB connection faiure";
}


/* Singleton class
 * This class is the one that interacts with the DB
 * Contains all the sqlite commands exposing only 
 * clean interfaces outside.
 */
public class sqliteDB 
{

	public static final String SQLITE_CLASS_FORMAT =  "org.sqlite.JDBC";
	public static final String SQLITE_JDBC_DB = "jdbc:sqlite:test2.db";

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
	sqliteDB() throws Exception
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
			dbStmt = "create table "+Constants.TABLE_NAME_PEOPLE+" (f_name TEXT,l_name TEXT,age number,id number,Gender TEXT);" ;
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

	public boolean insertIntoTableRelation(Object relation, Person p1, Person p2) throws Exception
	{
		return insertIntoTableRelationWithId (relation, p1.getId(), p2.getId());
	}

	public boolean insertIntoTableRelationWithId(Object relation, int id1, int id2) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			stat = conn.createStatement();
			String insertString = "insert into "+Constants.TABLE_NAME_RELATION+" values ('"+relation+"',"+id1+","+id2+");";
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

	public boolean insertIntoTablePerson(String f_name, String l_name, int age, int id , String gender) throws Exception
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
			String insertString = "insert into "+Constants.TABLE_NAME_PEOPLE+" (f_name ,l_name ,age ,id,Gender ) values ('"+f_name+"','"+l_name+"',"+age+",( SELECT IFNULL(MAX(id), 0)+1 AS MaxX from people) , '"+gender+"');" ;

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

	public boolean selectFromTablePersonForHashmap(HashMap Person_HM) throws Exception
	{
		Statement stat = null;
		Person new_person;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			int i=0; 
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from people;");

			while (rs.next()) {
				new_person = new Person();

				System.out.println("f-name = " + rs.getString("f_name"));
				System.out.println("l-name = " + rs.getString("l_name"));
				System.out.println("age = " + rs.getInt("age"));
				System.out.println("ID = " + rs.getInt("id"));


				if(new_person!=null)
				{
					new_person.setFirstName(rs.getString("f_name"));
					new_person.setLastName(rs.getString("l_name"));
					new_person.setAge(rs.getInt("age"));
					new_person.setId(rs.getInt("id"));
					i = rs.getInt("id");
				}
				else
					System.out.println("null pointer exception:");

				Person_HM.put(i, new_person);

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}

	public void getAllNamesFromPeopleTable(ArrayList<String> fullNamesList) throws Exception
	{
		Statement stat = null;
		try {
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			stat = conn.createStatement();

			String selectString = "select f_name, l_name from people;" ;
			ResultSet rs = stat.executeQuery(selectString);
			while(rs.next())
			{
				String fullName = new String();
				String fname = new String(rs.getString("f_name"));
				String lname = new String(rs.getString("l_name"));
				fullName = fname+" "+lname;
				fullNamesList.add(fullName);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_DB_CONNECTION_FAILURE);
		}
		finally{
			stat.close();
			conn.close();
		}
	}

	// Description: This function returns the Full name of a person given his id
	// Inputs: Integer Id
	// Outputs: String FullName
	public String getNameFromId(int id) throws Exception
	{
		Statement stat = null;
		String fullName = new String();
		try {
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			stat = conn.createStatement();

			//
			String selectString = "select f_name, l_name from people where id="+id+";" ;
			ResultSet rs = stat.executeQuery(selectString);
			if(rs.next())
			{
				String fname = new String(rs.getString("f_name"));
				String lname = new String(rs.getString("l_name"));
				fullName = fname+" "+lname;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_DB_CONNECTION_FAILURE);
		}
		finally{
			stat.close();
			conn.close();
		}
		return fullName;

	}

	public int getIdFromFullName(String fullName)throws Exception
	{
		System.out.println("starting of get id from full name");
		Statement stat = null;
		String fname[] = fullName.split(" ");
		System.out.println("fname is "+fname[0]);
		//String lname = fullName;
		System.out.println("lname is "+fname[1]);
		try {
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			stat = conn.createStatement();

			//
			String selectString = "select id from "+Constants.TABLE_NAME_PEOPLE+" where f_name = '"+fname[0]+"' and l_name = '"+fname[1]+"';";
			ResultSet rs = stat.executeQuery(selectString);
			if(rs.next())
			{
				int id = rs.getInt("id");
				return id;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_DB_CONNECTION_FAILURE);
		}
		finally{
			stat.close();
			conn.close();
		}

		return 0;
	}
	
	public String getGenderFromFullName(String fullName)throws Exception
	{
		System.out.println("starting of get id from full name");
		Statement stat = null;
		String fname[] = fullName.split(" ");
		System.out.println("fname is "+fname[0]);
		//String lname = fullName;
		System.out.println("lname is "+fname[1]);
		try {
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}
			stat = conn.createStatement();

			//
			String selectString = "select Gender from "+Constants.TABLE_NAME_PEOPLE+" where f_name = '"+fname[0]+"' and l_name = '"+fname[1]+"';";
			ResultSet rs = stat.executeQuery(selectString);
			if(rs.next())
			{
				String str = rs.getString("Gender");
				return str;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_DB_CONNECTION_FAILURE);
		}
		finally{
			stat.close();
			conn.close();
		}

		return null;
	}

	public boolean showAllRelationsForDisplay(ArrayList<ArrayList<String>>relationObject) throws Exception
	{
		Statement stat = null;
		String RelName = new String();
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from relation ;");
			int i=0,j=0;
			while (rs.next()) {

				relationObject.add(new ArrayList<String>());

				RelName = rs.getString("rel_name");

				String p1_fullName = getNameFromId(rs.getInt("p1_id"));
				String p2_fullName = getNameFromId(rs.getInt("p2_id"));

				System.out.println(p1_fullName);
				System.out.println(p2_fullName);

				for (int col = 0; col < 3; col++)
				{
					relationObject.get(i).add(RelName);
					relationObject.get(i).add(p1_fullName);
					relationObject.get(i).add(p2_fullName);

				}

				//relationObject[i][j] = RelName;
				//relationObject[i][j+1] = p1_fullName;
				//relationObject[i][j+2] = p2_fullName;
				i++;  

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(" sqliteDB Error in displaying relation");
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}

	public void showAllRelationsForDisplayInGraphSnippet1(ArrayList<ArrayList<Object>> relationObject , int selectedId) throws Exception
	{
		System.out.println("starting of showAllRelationsForDisplayInGraphSnippet1");
		relationObject.add(new ArrayList<Object>());
		Statement stat = null;
		String RelName = new String();
		//ArrayList<ArrayList<String>> listOfList = new ArrayList<ArrayList<String>>();
		//GraphJFaceSnippet2 gef_object = new GraphJFaceSnippet2();
		//ArrayList<String[][]> str = new ArrayList<String[][]>();
		HashMap<String,String> HM = new HashMap<String,String>();
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from relation where p1_id ="+ selectedId+";");

			int i=0,j=0;
			while (rs.next()) {
				relationObject.add(new ArrayList<Object>());

				RelName = rs.getString("rel_name");

				//String p1_fullName = getNameFromId(rs.getInt("p1_id"));
				//String p2_fullName = getNameFromId(rs.getInt("p2_id"));

				//System.out.println(p1_fullName);
				//System.out.println(p2_fullName);

				for (int col = 0; col < 3; col++)
				{
					relationObject.get(i).add(RelName);
					relationObject.get(i).add(rs.getInt("p1_id"));
					relationObject.get(i).add(rs.getInt("p2_id"));

				}


				//relationObject.get(i)[j] = RelName;
				//relationObject[i][j+1] = p1_fullName;
				//relationObject[i][j+2] = p2_fullName;

				i++;  

			}

			ResultSet rs2 = stat.executeQuery("select * from relation where p2_id ="+ selectedId+";");
			while (rs2.next()) {
				relationObject.add(new ArrayList<Object>());

				RelName = rs.getString("rel_name");

				//String p1_fullName = getNameFromId(rs.getInt("p1_id"));
				//String p2_fullName = getNameFromId(rs.getInt("p2_id"));

				//System.out.println(p1_fullName);
				//System.out.println(p2_fullName);

				for (int col = 0; col < 3; col++)
				{
					relationObject.get(i).add(RelName);
					relationObject.get(i).add(rs.getInt("p1_id"));
					relationObject.get(i).add(rs.getInt("p2_id"));

				}

				//relationObject[i][j] = RelName;
				//relationObject[i][j+1] = p1_fullName;
				//relationObject[i][j+2] = p2_fullName;

				i++;  

			}
			//System.out.println(relationObject[0][0]);
			/*for (int k= 0 ; k<relationObject.length ; k++){
				if(relationObject[k][0] == "Son of"){
					System.out.println("k2 is "+relationObject[0][2]+" k1 is "+relationObject[0][1]);
					str = relationObject;
				}
			}*/

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(" sqliteDB Error in displaying relation");
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		
		

	}

	public int get_fatherOf(int selectedId ) throws Exception{	
		Person p =new Person();
		ArrayList<ArrayList<Object>> relationObject = new ArrayList<ArrayList<Object>>();
		showAllRelationsForDisplayInGraphSnippet1(relationObject , selectedId);
		System.out.println("HI father of");
		System.out.println("relationObject[0][1] "+relationObject.get(0).get(0));
		for(int j=0;j<relationObject.size()-1;j++){
		System.out.println("for");
			if (  (selectedId == (int)relationObject.get(j).get(1)) ||  (selectedId == (int)relationObject.get(j).get(2)) )
			{ 
				System.out.println("if "+relationObject.get(j).get(0));
				if (selectedId == (int)relationObject.get(j).get(2)) {
					System.out.println("2ndif");
					if (relationObject.get(j).get(0).equals("Father of")){
						System.out.println("Father of");
						return (int)relationObject.get(j).get(1);}}
				if (selectedId == (int)relationObject.get(j).get(1) ) {
					System.out.println("22ndif");
					if ((relationObject.get(j).get(0).equals("Son of")||relationObject.get(j).get(0).equals("Daughter of")) && getGenderFromFullName(getNameFromId((int)relationObject.get(j).get(2))).equals("M") ){
						System.out.println("Son of");
						return (int)relationObject.get(j).get(2);}}
			}

		}
		return 0;
	}

		public int get_MotherOf(int selectedId) throws Exception{	
			Person p = new Person();
			ArrayList<ArrayList<Object>> relationObject1 = new ArrayList<ArrayList<Object>>();
			showAllRelationsForDisplayInGraphSnippet1(relationObject1 , selectedId);
			System.out.println("HI");
			//System.out.println("relationObject1[0][1] "+relationObject1.get(2).get(0));
			for(int j=0;j<relationObject1.size()-1;j++){
				System.out.println("for");

				if (  (selectedId == (int)relationObject1.get(j).get(1)) ||  (selectedId == (int)relationObject1.get(j).get(2)) )
				{ 
					System.out.println("if "+relationObject1.get(j).get(0));
					if (selectedId == (int)relationObject1.get(j).get(2)) {
						System.out.println("2ndif");
						if (relationObject1.get(j).get(0).equals("Mother of")){
							System.out.println("Mother of");
							return (int)relationObject1.get(j).get(1);}}
					if (selectedId == (int)relationObject1.get(j).get(1) ) {
						if ((relationObject1.get(j).get(0).equals("Son of")||relationObject1.get(j).get(0).equals("Daughter of")) && getGenderFromFullName(getNameFromId((int)relationObject1.get(j).get(2))).equals("F")){
							System.out.println("f "+getGenderFromFullName(getNameFromId((int)relationObject1.get(j).get(1)))+" name "+getNameFromId((int)relationObject1.get(j).get(1)));
							return (int)relationObject1.get(j).get(2);}}
				}
			}
			return 0;
		}

		

	public void showAllRelationsForDisplayInGraph(ArrayList<ArrayList<String>> relationObject , int selectedId) throws Exception
	{
		System.out.println("starting of showAllRelationsForDisplayInGraph");
		relationObject.add(new ArrayList<String>());
		Statement stat = null;
		String RelName = new String();
		//ArrayList<ArrayList<String>> listOfList = new ArrayList<ArrayList<String>>();
		//GraphJFaceSnippet2 gef_object = new GraphJFaceSnippet2();
		//ArrayList<String[][]> str = new ArrayList<String[][]>();
		HashMap<String,String> HM = new HashMap<String,String>();
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from relation where p1_id ="+ selectedId+";");

			int i=0,j=0;
			while (rs.next()) {
				relationObject.add(new ArrayList<String>());

				RelName = rs.getString("rel_name");

				String p1_fullName = getNameFromId(rs.getInt("p1_id"));
				String p2_fullName = getNameFromId(rs.getInt("p2_id"));

				System.out.println(p1_fullName);
				System.out.println(p2_fullName);

				for (int col = 0; col < 3; col++)
				{
					relationObject.get(i).add(RelName);
					relationObject.get(i).add(p1_fullName);
					relationObject.get(i).add(p2_fullName);

				}


				//relationObject.get(i)[j] = RelName;
				//relationObject[i][j+1] = p1_fullName;
				//relationObject[i][j+2] = p2_fullName;
				HM.put(p1_fullName, null);
				HM.put(p2_fullName,null);
				i++;  

			}

			ResultSet rs2 = stat.executeQuery("select * from relation where p2_id ="+ selectedId+";");
			while (rs2.next()) {
				relationObject.add(new ArrayList<String>());

				RelName = rs.getString("rel_name");

				String p1_fullName = getNameFromId(rs.getInt("p1_id"));
				String p2_fullName = getNameFromId(rs.getInt("p2_id"));

				System.out.println(p1_fullName);
				System.out.println(p2_fullName);

				for (int col = 0; col < 3; col++)
				{
					relationObject.get(i).add(RelName);
					relationObject.get(i).add(p1_fullName);
					relationObject.get(i).add(p2_fullName);

				}

				//relationObject[i][j] = RelName;
				//relationObject[i][j+1] = p1_fullName;
				//relationObject[i][j+2] = p2_fullName;
				HM.put(p1_fullName, null);
				HM.put(p2_fullName,null);
				i++;  

			}
			//System.out.println(relationObject[0][0]);
			/*for (int k= 0 ; k<relationObject.length ; k++){
				if(relationObject[k][0] == "Son of"){
					System.out.println("k2 is "+relationObject[0][2]+" k1 is "+relationObject[0][1]);
					str = relationObject;
				}
			}*/

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(" sqliteDB Error in displaying relation");
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		final String[][] r = new String[relationObject.size()][];
		int i = 0;
		for (ArrayList<String> l : relationObject) 
			r[i++] = l.toArray(new String[l.size()]);
		GraphJFaceSnippet2.setRelationObject(r);


		/* if (  (myId == r[i].p1id) ||  (myId == r[i].p2id) )
		 { 
		   if (myId == r[i].p2id) {
		         if (r[i].rel_name == "father"){
		 return p1.id;}}}
		 return r[i].p1id*/
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

	public boolean selectFromTablePerson(ArrayList<Person> personList) throws Exception
	{
		Statement stat = null;
		try
		{
			conn = getConnection();
			if(conn == null){
				throw new Exception("exception thrown");
			}

			int i=0; 
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from people;");

			while (rs.next()) {
				personList.add(new Person());

				System.out.println("f-name = " + rs.getString("f_name"));
				System.out.println("l-name = " + rs.getString("l_name"));
				System.out.println("age = " + rs.getInt("age"));
				System.out.println("ID = " + rs.getInt("id"));


				if(personList.get(i)!=null)
				{
					personList.get(i).setFirstName(rs.getString("f_name"));
					personList.get(i).setLastName(rs.getString("l_name"));
					personList.get(i).setAge(rs.getInt("age"));
					personList.get(i).setId(rs.getInt("id"));
				}
				else
					System.out.println("null pointer exception:");

				i++; 

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e);
			System.out.println(Messages.ERR_PERSON_SELECTION);
		}
		finally{
			stat.close();
			conn.close();
		}
		return Constants.FALSE_VALUE;
	}
} 


/*String [0][0] = "\""+personWithCommas(p)+"\"";

String personWithCommas (Person p)
{
	String s1 = new String();

	s1 = p.getFirstName()+","+p.getLastName();
	return s1;
}*/
