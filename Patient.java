import java.util.ArrayList;

public class Patient {
	private String pID;
	private String fName;
	private String lName;
	private int height;// in inches
	private int weight;// in lbs
	private ArrayList<String> symptoms;
	private ArrayList<String> ills;
	private String insurance;// company name

	/**
	 * 
	 * @return
	 */
	public String getpID() {
		return pID;
	}

	/**
	 * 
	 * @param pID
	 */
	public void setpID(String pID) {
		this.pID = pID;
	}

	/**
	 * 
	 * @param fName
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * 
	 * @param lName
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * 
	 * @param insurance
	 */
	public void setInsurance(String insurance) {
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
	public int getWeight() {
		return weight;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getSymptoms() {
		return symptoms;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getIlls() {
		return ills;
	}

	/**
	 * 
	 * @return
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * 
	 * @param id
	 * @param fN
	 * @param lN
	 * @param hei
	 * @param wei
	 * @param insur
	 */
	public Patient(String id, String fN, String lN, int hei, int wei,
			String insur) {
		pID = id;
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
	public boolean addSymptom(String symp) {
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
	public boolean addIllness(String ill) {
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
