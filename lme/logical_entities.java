package lme;

import java.util.HashMap;
import java.util.Iterator;

import db.Person;
import db.persist;


public class logical_entities {
	
	private static logical_entities instance = null;
	
	public static logical_entities getInstance() throws Exception
	{
		if(instance == null)
			instance = new logical_entities();
		return instance;

	}
	
	public HashMap<Integer,Person> Person_HM = new HashMap<Integer,Person>();
	public HashMap Relation_HM = new HashMap();
	
	private logical_entities() throws Exception{
		
		// read everything from db
		
		//put the read information into two hashmaps for people and relationships
		
		// now your logical entities (people and relations) is ready.
		
		readAllFromDatabase();
		
	}
	
	private void readAllFromDatabase() throws Exception
	{
		readAllPeopleFromDatabase();
		//readAllrelationsFromDatabase();
	}
	
	private void readAllPeopleFromDatabase() throws Exception
	{
		persist persist_object = new persist();
		persist_object.selectAllPersonforhashmap(Person_HM);
		
		Iterator iterator = Person_HM.keySet().iterator();  
		   
		while (iterator.hasNext()) {  
		   int key = Integer.parseInt(iterator.next().toString());  
		   Person value = Person_HM.get(key);  
		   
		   System.out.println(key + " " + value.getFirstName());
		   System.out.println(key + " " + value.getLastName()); 
		   System.out.println(key + " " + value.getAge()); 
		   System.out.println(key + " " + value.getId()); 
		}  
		
	}
	
	/*private void readAllrelationsFromDatabase() throws Exception
	{
		persist persist_object = new persist();
		persist_object.selectAllRelationforhashmap(Relation_HM);
		
	}*/
	
	
	/* if (  (myId == r[i].p1id) ||  (myId == r[i].p2id) )
	 { 
	   if (myId == r[i].p2id) {
	         if (r[i].rel_name == "father"){
	 return p1.id;}}}
	 return r[i].p1id*/
}





