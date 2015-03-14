import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


/**
 * @author Chad Mersino
 * CIS 350 JUnit tests for DBAccess.java
 *
 */
public class DBAccessTests {

	/**
	 * Test method for {@link DBAccess#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		DBAccess d = new DBAccess();
		assertEquals(d.login("mersinoc", "Cis350"),"D");
		assertEquals(d.login("harry", "pass3"),"P");
		assertEquals(d.login("bill", "123"),"");

	}

	/**
	 * Test method for {@link DBAccess#sympSearch(java.lang.String)}.
	 */
	@Test
	public void testSympSearch() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Cold");
		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("Cold");
		test1.add("Flu");

		
		assertEquals(d.sympSearch("Aches"),test0);
		assertEquals(d.sympSearch("Fever"),test1);
	}

	/**
	 * Test method for {@link DBAccess#illSearch(java.lang.String)}.
	 */
	@Test
	public void testIllSearch() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Aches");
		test0.add("Fever");
		test0.add("Runny Nose");
		test0.add("Watery Eyes");

		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("Chills");
		test1.add("Confusion");
		test1.add("Fatigue");

		
		assertEquals(d.illSearch("Cold"),test0);
		assertEquals(d.illSearch("Malaria"),test1);
		
	}

	/**
	 * Test method for {@link DBAccess#addIll(java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testAddIll() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		ArrayList<String> test1 = new ArrayList<String>();

		test0.add("Aches");
		test0.add("Fever");
		
		assertEquals(d.addIll("Bronchitus", test0), test1);
		assertEquals(d.addIll("Cold", test0), test0);

	}

	/**
	 * Test method for {@link DBAccess#getIlls()}.
	 */
	@Test
	public void testGetIlls() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Cold");
		test0.add("Flue");
		test0.add("Malaria");
		
		assertEquals(d.getIlls(),test0);

	}

	/**
	 * Test method for {@link DBAccess#getSymps(java.lang.String)}.
	 */
	/**@Test
	public void testGetSymps() {
		DBAccess d = new DBAccess();

		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Aches");
		test0.add("Fever");
		test0.add("Runny Nose");
		test0.add("Watery Eyes");
		test0.add("Fatigue");
		test0.add("Sore Throat");
		test0.add("Chills");
		test0.add("Confusion");
		
		assertEquals(d.getSymps(),test0);
	}
*/
	
	/**
	 * Test method for {@link DBAccess#delSymp(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDelSymp() {
		DBAccess d = new DBAccess();
		assertEquals(d.delSymp("Cold","Aches"), true);
		assertEquals(d.delSymp("potato","fries"), false);

	}


	/**
	 * Test method for {@link DBAccess#delIll(java.lang.String)}.
	 */
	@Test
	public void testDelIll() {
		DBAccess d = new DBAccess();
		assertEquals(d.delIll("Cold"), true);
		assertEquals(d.delIll("joe"), false);

	}

}
