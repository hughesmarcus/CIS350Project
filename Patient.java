import java.util.ArrayList;
/**
 * 
 * @author marcu_000
 *
 */
public class Patient {
	/**
	 * 
	 */
	private int pID;
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
	private int height; // in inches
	/**
	 * 
	 */
	private int weight; // in lbs
	/**
	 * 
	 */
	private ArrayList<String> symptoms;
	/**
	 * 
	 */
	private ArrayList<String> ills;
	/**
	 * 
	 */
	private String insurance; // company name

	/**
	 * 
	 * @return
	 */
	public int getpID() {
		return pID;
	}

	/**
	 * 
	 * @param pID
	 */
	public final void setpID(final int pID) {
		this.pID = pID;
	}

	/**
	 * 
	 * @param fName
	 */
	public final void setfName(final String fName) {
		this.fName = fName;
	}

	/**
	 * 
	 * @param lName
	 */
	public final void setlName(final String lName) {
		this.lName = lName;
	}

	/**
	 * 
	 * @param height
	 */
	public final void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * 
	 * @param weight
	 */
	public final void setWeight(final int weight) {
		this.weight = weight;
	}

	/**
	 * 
	 * @param insurance
	 */
	public final void setInsurance(final String insurance) {
		this.insurance = insurance;
	}

	/**
	 * 
	 * @return
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 
	 * @return
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return
	 */
	public final int getWeight() {
		return weight;
	}

	/**
	 * 
	 * @return
	 */
//	public final ArrayList<String> getSymptoms() {
//		return symptoms;
//	}

	/**
	 * 
	 * @return
	 */
	public final ArrayList<String> getIlls() {
		return ills;
	}

	/**
	 * 
	 * @return
	 */
	public final String getInsurance() {
		return insurance;
	}

	/**
	 * 
	 * @param userID
	 * @param fN
	 * @param lN
	 * @param hei
	 * @param wei
	 * @param insur
	 */
	public Patient(final int userID, final String fN,
			final String lN, final int hei, final int wei,
			final String insur) {
		pID = userID;
		fName = fN;
		lName = lN;
		height = hei;
		weight = wei;
		insurance = insur;
	}

	/**
	 * 
	 * @param symp
	 * @return
	 */
	public final boolean addSymptom(final String symp) {
		boolean has = false;
		for (int i = 0; i < symptoms.size(); i++) {
			if (symptoms.get(i) == symp) {
				// patient already has this symptom
				has = true;
			}
		}
		if (!has) {
			// if the patient does not have that symptom
			symptoms.add(symp);
		}
		return has;
	}

	/**
	 * 
	 * @param ill
	 * @return
	 */
	public final boolean addIllness(final String ill) {
		boolean has = false;
		for (int i = 0; i < ills.size(); i++) {
			if (ills.get(i) == ill) {
				has = true;
			}
		}
		if (!has) {
			symptoms.add(ill);
		}
		return has;
	}
}
