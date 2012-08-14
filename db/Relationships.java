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
	//Relationships(){ rel_name = null;}
	/*Relationships(int num){
		rel_num = num;
	}*/
	
	/*public String get_rel_name(){
		return rel_name;
		
	}
	
	public String get_rel_num(){
		return rel_num;
	}*/
	@Override
	    public String toString() {
	        return rel_name;
	    }
	
}
