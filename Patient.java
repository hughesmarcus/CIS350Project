import java.util.ArrayList;

public class Patient {
	private String pID;
	private String fName;
	private String lName;
	private String height;
	private String weight;
	private ArrayList<String> symptoms;
	private ArrayList<String> ills;
	private String insurance;
	
	public String getpID() {
		return pID;
	}
	public void setpID(String pID) {
		this.pID = pID;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public String getHeight() {
		return height;
	}
	public String getWeight() {
		return weight;
	}
	public ArrayList<String> getSymptoms() {
		return symptoms;
	}
	public ArrayList<String> getIlls() {
		return ills;
	}
	public String getInsurance() {
		return insurance;
	}
	
	public Patient(String id, String fN, String lN, String hei, String wei, String insur)
	{
		pID = id;
		fName = fN;
		lName = lN;
		height = hei;
		weight = wei;
		insurance = insur;
	}
	
	public void addSymptom(String symp)
	{
		boolean has = false;
		for(int i = 0; i < symptoms.size(); i++)
		{
			if(symptoms.get(i) == symp)
			{
				//patient already has this symptom
				has = true;
			}
		}
		if(!has)
		{
			//if the patient does not have that symptom
			symptoms.add(symp);
		}
	}
}
