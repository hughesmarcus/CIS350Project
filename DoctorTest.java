import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class DoctorTest {

	/*
	 * Author: Chad Mersino
	 * Revision Date: 3/19/2015
	 * 
	 * JUnit Testing for Doctor Class
	 */
	
	/*
	 * Testing method for getSpecial() in Doctor
	 */
	@Test
	public void testGetSpecial() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		assertEquals(d.getSpecial(), "cardiologist");
	}

	/*
	 * Testing method for setSpecial() in Doctor
	 */
	@Test
	public void testSetSpecial() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		d.setSpecial("cardiologist");
		assertEquals(d.getSpecial(), "cardiologist");
	}

	/*
	 * Testing method for getdID() in Doctor
	 */
	@Test
	public void testGetdID() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		assertEquals(d.getdID(), 98011);

	}

	/*
	 * Testing method for setdID() in Doctor
	 */
	@Test
	public void testSetdID() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		d.setdID(98011);
		assertEquals(d.getdID(), 98011);	
		
	}

	/*
	 * Testing method for setdfName() in Doctor
	 */
	@Test
	public void testSetdfName() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		d.setdfName("Frank");
		assertEquals(d.getdfName(), "Frank");
		
	}

	/*
	 * Testing method for setdlName() in Doctor
	 */
	@Test
	public void testSetdlName() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		d.setdlName("Charles");
		assertEquals(d.getdlName(), "Charles");
		
	}
	
	/*
	 * Testing method for dfName() in Doctor
	 */
	@Test
	public void testGetdfName() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		assertEquals(d.getdfName(), "Frank");

	}

	/*
	 * Testing method for getdlName() in Doctor
	 */
	@Test
	public void testGetdlName() {
		Doctor d = new Doctor(98011, "cardiologist", "Frank", "Charles");
		assertEquals(d.getdlName(), "Charles");
		
	}

//	@Test
//	public void testGetPatients() {
//		//Patient(String id, String fN, String lN, int hei, int wei,String insur)
//		Doctor d = new Doctor("98011", "cardiologist", "Frank", "Charles");
//		ArrayList<Patient> test0 = new ArrayList<Patient>();
//		Patient person = "11324", "Hank", "Straman", 70, 275, "priority health";
//		test0.add(person);
//		//test0.add(13452);
//		//test0.add(15532);
//		//test0.add(16783);
//
//
//		
//	}

//	@Test
//	public void testAddPatients() {
//		
//		
//	}

}
