import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Illness {
	private String illname;
	private String treatment;
	private ArrayList<String> symptom;

	public Illness(String ill, String treat) {
		illname = ill;
		treatment = treat;

	}

	public String getillname() {
		return illname;
	}

	public void setillname(String illname) {
		this.illname = illname;
	}

	public String gettreatment() {
		return treatment;
	}

	public void settreatment(String treatment) {
		this.treatment = treatment;
	}

	public ArrayList<String> getSymptoms() {
		return symptom;
	}

	public void addsymptom(String sym) {

		symptom.add(sym);

	}

};
