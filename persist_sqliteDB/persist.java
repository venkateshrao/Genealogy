enum Relationships
{
	REL_FATHER,
	REL_MOTHER,
	REL_BROTHER,
	REL_SISTER,
	REL_SON,
	REL_DAUGHTER,
	REL_WIFE,
	REL_HUSBAND,
	REL_FRIEND,
	REL_COUSIN,
	REL_MAX      //sentinel
}

public class persist {
	
	private sqliteDB getDBHandle() throws Exception
	{
		sqliteDB dbHandle = sqliteDB.getInstance();
		return dbHandle;
	}

	public boolean createNewPerson(Person p1) throws Exception
	{
		sqliteDB dbHandle = getDBHandle();
		dbHandle.insertIntoTable(Constants.TABLE_NAME_PEOPLE, p1, null);
		return true;
	}
	
	public boolean createNewRelationship(String rel_name, Person p1, Person p2) throws Exception
	{
		sqliteDB dbHandle = getDBHandle();
		dbHandle.insertIntoTable(Constants.TABLE_NAME_REL, p1, p2);
		return true;
	}
}
