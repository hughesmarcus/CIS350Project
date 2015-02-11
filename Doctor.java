import java.util.ArrayList;
public class Doctor {
	private String dID;
	private String fName;
	private String lName;
	private ArrayList<Patient> Patients;
	
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
	public void setdfName(String fName) {
		this.fName = fName;
	}
	public void setdlName(String lName) {
		this.lName = lName;
	}
	
	public String getdfName() {
		return fName;
	}
	public String getdlName() {
		return lName;
	}
	
	public ArrayList<Patient> getPatients() {
		return Patients;
	}
	public boolean addPatients(Patient pat){
		
		boolean has = false;
		for(int i = 0; i < Patients.size(); i++)
		{
			if(Patients.get(i) == pat)
			{
				has = true;
			}
		}
		if(!has)
		{
			Patients.add(pat);
		}
		return has;
		
	}
}



