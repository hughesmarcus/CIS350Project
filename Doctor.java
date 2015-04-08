import java.util.ArrayList;
public class Doctor {
	private int dID;
	private String fName;
	private String lName;
	private ArrayList<Patient> Patients;
	private String specialization;
	
	

	public Doctor(int id, String first, String last)
	{
		dID = id;
		fName = first;
		lName = last;
	}
	
	public Doctor(int id, String first, String last, String special)
	{
		dID = id;
		fName = first;
		lName = last;
		specialization = special;
	}
	
	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public int getdID() {
		return dID;
	}
	
	public void setdID(int dID) {
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
	
	public void setPatients(ArrayList<Patient> p)
	{
		Patients = p;
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
