import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/*
 * Author: Chad Mersino
 * Revision Date: 3/19/2015
 * 
 * JUnit Testing for Patient Class
 */

public class PatientTest  {

	/*
	 * Testing method for getpID() in patient
	 */
	@Test
	public void testGetpID() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getpID(), 11324);

	}

	/*
	 * Testing method for setpID() in patient
	 */
	@Test
	public void testSetpID() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setpID(11324);
		assertEquals(p.getpID(), 11324);
		
	}
	
	/*
	 * Testing method for setfName() in patient
	 */
	@Test
	public void testSetfName() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setfName("Hank");
		assertEquals(p.getfName(), "Hank");
		
	}

	/*
	 * Testing method for setlName() in patient
	 */
	@Test
	public void testSetlName() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setlName("Straman");
		assertEquals(p.getlName(), "Straman");
		
	}

	/*
	 * Testing method for setheight() in patient
	 */
	@Test
	public void testSetHeight() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setHeight(70);
		assertEquals(p.getHeight(), 70);
		
	}

	/*
	 * Testing method for setWeight() in patient
	 */
	@Test
	public void testSetWeight() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setWeight(275);
		assertEquals(p.getWeight(), 275);	
		
	}

	/*
	 * Testing method for setInsurance() in patient
	 */
	@Test
	public void testSetInsurance() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		p.setInsurance("priority health");
		assertEquals(p.getInsurance(), "priority health");
	}

	/*
	 * Testing method for getfName() in patient
	 */
	@Test
	public void testGetfName() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getfName(), "Hank");	
		
	}

	/*
	 * Testing method for getlName() in patient
	 */
	@Test
	public void testGetlName() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getlName(), "Straman");	
		
	}

	/*
	 * Testing method for getHeight() in patient
	 */
	@Test
	public void testGetHeight() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getHeight(), 70);
		
	}

	/*
	 * Testing method for getWeight() in patient
	 */
	@Test
	public void testGetWeight() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getWeight(), 275);
		
	}


//	@Test
//	public void testGetIlls() {
//		Patient p = new Patient ("11324", "Hank", "Straman", 70, 275, "priority health");
//		ArrayList <String> a = new ArrayList <String>();
//		a.add("body aches");
//		a.add("aches");
//		a.add("nasal congestion");
//		
//		assertEquals(p.getSymptoms(), a);
//		
//	}

	/*
	 * Testing method for getInsurance() in patient
	 */
	@Test
	public void testGetInsurance() {
		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
		assertEquals(p.getInsurance(), "priority health");
	}


	/*
	 * Testing method for addSymptom() in patient
	 */
//	@Test
//	public void testAddSymptom() {
//		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
//		assertEquals(p.addSymptom("balding"), false);
//		assertEquals(p.addSymptom("aches"), true);
//	}

	/*
	 * Testing method for addIllness() in patient
	 */
//	@Test
//	public void testAddIllness() {
//		Patient p = new Patient (11324, "Hank", "Straman", 70, 275, "priority health");
//		assertEquals(p.addIllness("Hepatitus B"), false);
//	
//	}
//	
}
