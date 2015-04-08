
/**
 * @author Chad Mersino
 * CIS 350 Admin.java class for project
 *
 */

public class Admin {
	/**
	 * 
	 */
	private int aID;
	/**
	 * 
	 */
	private String fName;
	/**
	 * 
	 */
	private String lName;
	
	
	/**
	 * 
	 */
	public Admin(final int id, String first, String last){
		aID = id;
		fName = first;
		lName = last;
		
	}
	/**
	 *@return
	 */
	int getaID() {
		
		return aID;
	}
	/**
	 *@param
	 */
	public final void setaID(final int aID){
		
		this.aID = aID;
	}
	/**
	 * 
	 */
	public final String getfName(){
		
		return fName;
	}
	/**
	 * 
	 */
	public void setafName(final String fName){
		
		this.fName = fName;
	}
	/**
	 * 
	 */
	public final String getlName(){
		
		return lName;
	}
	/**
	 * 
	 */
	public void setalName(final String lName){
		
		this.lName = lName;
	}
}
