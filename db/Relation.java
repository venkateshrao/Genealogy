package db;
import java.util.*; 

//TODO Create a map of enum Relationships with corresponding name strings
/*
+--------------|---------------+
  Relationships    String
  ------------------------------
  REL_FATHER		"Father of"
  REL_MOTHER		"Mother of"
*/

public class Relation {
	/*public enum Relationships {
		REL_FATHER,
		REL_MOTHER,
		REL_SON,
		REL_DAUGHTER,
		REL_HUSBAND,
		REL_WIFE,
		REL_BROTHER,
		REL_SISTER,
		REL_MAX
		
	}*/

	public Relation(){
		
	}

	
	/*public static void mapRelationNameToEnum() {
		HashMap<Relationships,String> hm = new HashMap<Relationships,String>(); 
		// Put elements to the map 
		hm.put(Relationships.REL_FATHER,"Father"); 
		hm.put(Relationships.REL_MOTHER,"Mother"); 
		hm.put(Relationships.REL_SON,"Son"); 
		hm.put(Relationships.REL_DAUGHTER,"Daughter"); 
		hm.put(Relationships.REL_HUSBAND,"Husband");
		hm.put(Relationships.REL_WIFE,"Wife");
		hm.put(Relationships.REL_BROTHER,"Brother");
		hm.put( Relationships.REL_SISTER,"Sister");
	}*/
	
	public Relationships getRelationshipName() { return rel_name;}
	public void setRelationshipName(Relationships rel) { rel_name = rel; }
	
	public void addPeople(Person p1, Person p2) 
	{
		person1 = p1 ; person2 = p2;
		p1.addRelation(this);
		p2.addRelation(this);
	}
	
	public Person getFirstPerson() { return person1;}
	public Person getSecondPerson() { return person2;}
	
	private Relationships rel_name;
	private Person person1;
	private Person person2;

}
