import java.util.ArrayList;

/**
 * 
 */
public class Doctor {
	/**
	 * Doctor id.
	 */
	private int dID;
	/**
	 * Doctor First name.
	 */
	private String fName;
	/**
	 * Doctor last name.
	 */
	private String lName;

	/**
	 * Doctor's specialization.
	 */
	private String specialization;

	/**
	 * Doctor's Patients.
	 */
	private ArrayList<Patient> Patients;

	/**
	 * Doctor class constructor.
	 * 
	 * @param id
	 *            doctor id
	 * @param first
	 *            first name
	 * @param last
	 *            last name
	 */
	public Doctor(final int id, final String first, final String last) {
		dID = id;
		fName = first;
		lName = last;
	}

	/**
	 * Doctor Constructor.
	 * 
	 * @param id
	 *            doctor id
	 * @param special
	 *            specialization
	 * @param first
	 *            first name
	 * @param last
	 *            last name
	 */
	public Doctor(final int id, final String special, final String first,
			final String last) {
		dID = id;
		fName = first;
		lName = last;
		specialization = special;

	}

	/**
	 * return the doctor's specialization.
	 * 
	 * @return dSpecial
	 */
	public final String getSpecialization() {
		return specialization;
	}

	/**
	 * Set the specialization.
	 * 
	 * @param specialization
	 *            Doctors specialization
	 */
	public final void setSpecialization(final String specialization) {
		this.specialization = specialization;

	}

	/**
	 * Get doctor ID.
	 * 
	 * @return dID
	 */
	public final int getdID() {
		return dID;
	}

	/**
	 * set ID.
	 * 
	 * @param dID
	 *            Id of Doctor
	 */
	public final void setdID(final int dID) {
		this.dID = dID;
	}

	/**
	 * set First name of doctor.
	 * 
	 * @param fName
	 *            first name of doctor
	 */
	public final void setdfName(final String fName) {
		this.fName = fName;
	}

	/**
	 * set last name of Doctor.
	 * 
	 * @param lName
	 *            set last name of doctor
	 * 
	 */
	public final void setdlName(final String lName) {
		this.lName = lName;
	}

	/**
	 * get first name.
	 * 
	 * @return fName first name
	 */
	public final String getdfName() {
		return fName;
	}

	/**
	 * get last name.
	 * 
	 * @return lName last name
	 */
	public final String getdlName() {
		return lName;
	}

	/**
	 * get patients of doctor.
	 * 
	 * @return Patients array of patients
	 */
	public final ArrayList<Patient> getPatients() {
		return Patients;
	}

	/**
	 * set patients array.
	 * 
	 * @param p
	 *            array of patients
	 *
	 **/
	public final void setPatients(final ArrayList<Patient> p) {
		Patients = p;
	}

	/**
	 * Add patient to array list.
	 * 
	 * @param pat
	 *            patient to add
	 * @return has
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
