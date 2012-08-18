package db;

public enum Relationships {
	FATHER("Father of"),
	MOTHER("Mother of"),
	SON("Son of"),
	DAUGHTER("Daughter of"),
	HUSBAND("Husband of"),
	WIFE("Wife of"),
	BROTHER("Brother of"),
	SISTER("Sister of"),
	MAX("Max");
	
	private final String rel_name;
	//private final int rel_num;
	
	
	Relationships(String name){
		rel_name = name;
		
		
	}
	
	@Override
	    public String toString() {
	        return rel_name;
	    }
	
}
