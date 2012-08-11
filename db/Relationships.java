package db;

public enum Relationships {
	FATHER("Father"),
	MOTHER("Mother"),
	SON("Son"),
	DAUGHTER("Daughter"),
	HUSBAND("Husband"),
	WIFE("Wife"),
	BROTHER("Brother"),
	SISTER("Sister"),
	MAX("Max");
	
	private final String rel_name;
	//private final int rel_num;
	
	
	Relationships(String name){
		rel_name = name;
		
		
	}
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
