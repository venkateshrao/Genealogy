
public class Person {
	
	public String getFirstName() { return first_name; }
	
	public String getLastName() { return last_name;}
	
	public String getFullName(String fullName)
	{	
		fullName = first_name+" "+last_name;	
		System.out.println("fullName is "+fullName);
		return fullName;
	}
	
	public int getAge() { return age; }
	
	public int getId() { return id; }
	
	public void setFirstName(String name) { first_name = name; }
	public void setLastName(String name) { last_name = name; }
	public void setAge(int years) { age = years; }
	public void setId(int identification) { id = identification; }
	public void addRelation(Relation r) {rel = r;}
	public Relationships getRelation() { return rel.getRelationshipName(); }
	
	public String stringify()
	{
		StringBuilder str = new StringBuilder();
		
		str.append(this);
		
		System.out.println("Person is "+str.toString());
		
		return str.toString();
	}
	
	private String first_name;
	private String last_name;
	private int age;
	private int id;
	
	//Person should have the list of relationships that he is involved in
	private Relation rel;
	
}