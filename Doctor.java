import java.util.ArrayList;

public class Doctor {
	private String dID;
	private String fName;
	private String lName;
	private String dSpecial;
	private ArrayList<Patient> Patients;

	/**
	 * 
	 * @param id
	 * @param first 
	 * @param last
	 * @param special
	 */
	public Doctor(String id, String special, String first, String last) {
		dID = id;
		fName = first;
		lName = last;
		dSpecial = special;

	}
	
	/**
	 * 
	 * @return dSpecial
	 */
	public String getSpecial(){
		return dSpecial;
	}
	
	/**
	 * 
	 * @param special
	 */
	public void setSpecial(String special){
		this.dSpecial = special;
		
	}

	/**
	 * 
	 * @return dID
	 */
	public String getdID() {
		return dID;
	}

	/**
	 * 
	 * @param dID
	 */
	public void setdID(String dID) {
		this.dID = dID;
	}

	/**
	 * 
	 * @param fName
	 */
	public void setdfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @param lName
	 * 
	 */
	public void setdlName(String lName) {
		this.lName = lName;
	}

	/**
	 * 
	 * @return fName
	 */
	public String getdfName() {
		return fName;
	}

	/**
	 * 
	 * @return lName
	 */
	public String getdlName() {
		return lName;
	}

	/**
	 * 
	 * @return Patients
	 */
	public ArrayList<Patient> getPatients() {
		return Patients;
	}

	/**
	 * 
	 * @return
	 */
	public boolean addPatients(Patient pat) {

		boolean has = false;
		for (int i = 0; i < Patients.size(); i++) {
			if (Patients.get(i) == pat) {
				has = true;
			}
		}
		if (!has) {
			Patients.add(pat);
		}
		return has;

	}
}
