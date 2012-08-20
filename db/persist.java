package db;

import java.util.ArrayList;
//interface to the persistence module. SQLITE in our case
//Can also be extended to xmlfile writes if sqlite is not preferred

public class persist
{
	private sqliteDB dbHandle = null;
	
	private sqliteDB getDBHandle() throws Exception { return sqliteDB.getInstance();}
	
	private boolean initializePersistence() throws Exception
	{
		dbHandle = sqliteDB.getInstance();
		return true;
	}
	
	public persist() throws Exception
	{
		boolean result = initializePersistence();
	}
	
	public boolean createNewPerson(Person p1) throws Exception
	{
		
		
		boolean result = dbHandle.insertIntoTablePerson(p1.getFirstName(), p1.getLastName(), p1.getAge(), p1.getId());
		
		return result;
	}
	
	public boolean createNewRelationship(Object r_name,int p1, int p2) throws Exception
	{
			
		boolean result = dbHandle.insertIntoTableRelationWithId(r_name, p1, p2);
		return result;
	}
	
	public boolean deletePerson(Person p1) throws Exception
	{
		
		
		boolean result = dbHandle.deleteFromTablePerson(p1.getId());
		
		return result;
	}
	
	public boolean selectAllPerson(ArrayList<Person> personList) throws Exception
	{
			
		boolean result = dbHandle.selectFromTablePerson(personList);
		return result;
	}
	
	/*public boolean selectPersonName() throws Exception
	{
			
		boolean result = dbHandle.selectNameFromTablePerson();
		return result;
	}*/
	
	public boolean selectAllRelation(String[][] relationObject) throws Exception
	{
			
		boolean result = dbHandle.showAllRelationsForDisplay(relationObject);
		return result;
	}
}
