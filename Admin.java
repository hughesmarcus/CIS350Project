
/**
 * @author Chad Mersino
 * CIS 350 Admin.java class for project
 *
 */

public class Admin {

	private String aID;
	private String fName;
	private String lName;
	
	
	
	public Admin(String id, String first, String last){
		aID = id;
		fName = first;
		lName = last;
		
	}
	
	public String getaID(){
		
		return aID;
	}
	
	public void setaID(String aID){
		
		this.aID = aID;
	}
	
	public String getfName(){
		
		return fName;
	}
	
	public void setafName(String fName){
		
		this.fName = fName;
	}
	
	public String getlName(){
		
		return lName;
	}
	
	public void setalName(String lName){
		
		this.lName = lName;
	}
}
