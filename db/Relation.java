enum Relationships {
	REL_FATHER,
	REL_MOTHER,
	REL_SON,
	REL_DAUGHTER,
	REL_HUSBAND,
	REL_WIFE,
	REL_BROTHER,
	REL_SISTER,
	REL_MAX;
}

//TODO Create a map of enum Relationships with corresponding name strings
/*
+--------------|---------------+
  Relationships    String
  ------------------------------
  REL_FATHER		"Father of"
  REL_MOTHER		"Mother of"
*/

public class Relation {

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
