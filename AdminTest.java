import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Author: Chad Mersino
 * Revision Date: 3/19/2015
 * 
 * JUnit Testing for Admin Class
 */

public class AdminTest {

	/*
	 * Testing method for getaID() in DBAccess
	 */
	@Test
	public void testGetaID() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		assertEquals(a.getaID(), "11211");	
		
	}

	/*
	 * Testing method for setaID() in DBAccess
	 */
	@Test
	public void testSetaID() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		a.setaID("11211");
		assertEquals(a.getaID(), "11211");	
		
	}

	/*
	 * Testing method for getfName() in DBAccess
	 */
	@Test
	public void testGetfName() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		assertEquals(a.getfName(), "Howard");	
		
	}

	/*
	 * Testing method for setafName() in DBAccess
	 */
	@Test
	public void testSetafName() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		a.setafName("Howard");
		assertEquals(a.getfName(), "Howard");	
		
	}

	/*
	 * Testing method for getlName() in DBAccess
	 */
	@Test
	public void testGetlName() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		assertEquals(a.getlName(), "Fitzgerald");	
		
	}

	/*
	 * Testing method for setalName() in DBAccess
	 */
	@Test
	public void testSetalName() {
		Admin a = new Admin("11211", "Howard", "Fitzgerald");
		a.setalName("Fitzgerald");
		assertEquals(a.getfName(), "Howard");	
		
	}

}
