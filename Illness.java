import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Illness {
	private String illname;
	private String treatment;
	private ArrayList<String> symptom;

	/**
	 * 
	 * @param ill
	 * @param treat
	 */
	public Illness(String ill, String treat) {
		illname = ill;
		treatment = treat;

	}

	/**
	 * 
	 * @return
	 */
	public String getillname() {
		return illname;
	}

	/**
	 * 
	 * @param illname
	 */
	public void setillname(String illname) {
		this.illname = illname;
	}

	/**
	 * 
	 * @return
	 */
	public String gettreatment() {
		return treatment;
	}

	/**
	 * 
	 * @param treatment
	 */
	public void settreatment(String treatment) {
		this.treatment = treatment;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getSymptoms() {
		return symptom;
	}

	/**
	 * 
	 * @param sym
	 */
	public void addisymptom(String sym) {

		symptom.add(sym);

	}

};
