import java.util.ArrayList;
/**
 * 
 */
public class Doctor {
	/**
	 * 
	 */
	private int dID;
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
	private String specialization;
	
	/**
	 * 
	 */
	private ArrayList<Patient> Patients;
public Doctor(int id, String first, String last)
	{
		dID = id;
		fName = first;
		lName = last;
	}

	/**
	 * 
	 * @param id
	 * @param special 
	 * @param first
	 * @param last
	 */
	public Doctor(final int id, final String special, final String first, final String last) {
		dID = id;
		fName = first;
		lName = last;
		dSpecial = special;

	}
	
	/**
	 * 
	 * @return dSpecial
	 */
	public final String getSpecialization(){
		return dSpecial;
	}
	
	/**
	 * 
	 * @param special
	 */
	public final void setSpecialization(final String specialization){
		this.specialization = specialization;
		
	}

	/**
	 * 
	 * @return dID
	 */
	public final int getdID() {
		return dID;
	}

	/**
	 * 
	 * @param dID
	 */
	public final void setdID(final int dID) {
		this.dID = dID;
	}

	/**
	 * 
	 * @param fName
	 */
	public void setdfName(final String fName) {
		this.fName = fName;
	}

	/**
	 * @param lName
	 * 
	 */
	public final void setdlName(final String lName) {
		this.lName = lName;
	}

	/**
	 * 
	 * @return fName
	 */
	public final String getdfName() {
		return fName;
	}

	/**
	 * 
	 * @return lName
	 */
	public final String getdlName() {
		return lName;
	}

	/**
	 * 
	 * @return Patients
	 */
	public final ArrayList<Patient> getPatients() {
		return Patients;
	}
	/**
	*
	**/
	public void setPatients(ArrayList<Patient> p)
	{
		Patients = p;
	}

	/**
	 * 
	 * @return
	 */
	public final boolean addPatients(final Patient pat) {

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
