import java.util.ArrayList;
public class Doctor {
	private String dID;
	private String fName;
	private String lName;
	private ArrayList<String> Patients;
	
	public Doctor(String id, String first, String last)
	{
		dID = id;
		fName = first;
		lName = last;
	
	}
	
	public String getdID() {
		return dID;
	}
	public void setdID(String dID) {
		this.dID = dID;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	
	public ArrayList<String> getPatients() {
		return Patients;
	}
}


