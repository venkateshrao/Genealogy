package db;

import java.util.ArrayList;
//interface to the persistence module. SQLITE in our case
//Can also be extended to xmlfile writes if sqlite is not preferred
import java.util.HashMap;

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
		
		
		boolean result = dbHandle.insertIntoTablePerson(p1.getFirstName(), p1.getLastName(), p1.getAge(), p1.getId() , p1.getGender() , p1.getBirthDate());
		
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
	
	public boolean selectAllRelation(ArrayList<ArrayList<String>> relationObject) throws Exception
	{
			
		boolean result = dbHandle.showAllRelationsForDisplay(relationObject);
		return result;
	}
	
	public void selectAllRelationForGraph(ArrayList<ArrayList<String>> relationObject , int selectedId) throws Exception
	{
			
		 dbHandle.showAllRelationsForDisplayInGraph(relationObject , selectedId);
		
	}
	public boolean selectAllPersonforhashmap(HashMap Person_HM) throws Exception
	{
			
		boolean result = dbHandle.selectFromTablePersonForHashmap(Person_HM);
		return result;
	}
	public void selectAllPersonforGraphSnippet1(ArrayList<ArrayList<Object>> relationObject , int selectedId) throws Exception
	{
			
		dbHandle.showAllRelationsForDisplayInGraphSnippet1(relationObject,selectedId);
		
	}
	/*public boolean selectAllRelationforhashmap(HashMap Relation_HM) throws Exception
	{
			
		boolean result = dbHandle.selectFromTableRelationForHashmap(Relation_HM);
		return result;
	}*/
	
}
