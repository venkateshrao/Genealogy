package db;
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
	
	persist() throws Exception
	{
		boolean result = initializePersistence();
	}
	
	public boolean createNewPerson(Person p1) throws Exception
	{
		
		
		boolean result = dbHandle.insertIntoTablePerson(p1.getFirstName(), p1.getLastName(), p1.getAge(), p1.getId());
		
		return result;
	}
	
	public boolean createNewRelationship(Relationships r_name,Person p1, Person p2) throws Exception
	{
			
		boolean result = dbHandle.insertIntoTableRelation(r_name, p1, p2);
		return result;
	}
}
