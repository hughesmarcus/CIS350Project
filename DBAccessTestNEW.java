import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

/*
 * Author: Chad Mersino
 * Revision Date: 3/19/2015
 * 
 * JUnit Testing for DBAccess Class
 */
public class DBAccessTestNEW extends DBAccess {

	/*
	 * Testing method for login() in DBAccess
	 */
	@Test
	public void testLogin() {
		DBAccess d = new DBAccess();
		assertEquals(d.login("doc0", "doc0"),"D");
		assertEquals(d.login("admin", "admin"),"A");
		assertEquals(d.login("pat5", "pat5"),"P");

	}
	
	/*
	 * Testing method for sympSearch() in DBAccess
	 */
	@Test
	public void testSympSearch() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Malaria");
		test0.add("Pneumonia");
		test0.add("Tuberculosis");

		
		assertEquals(d.sympSearch("sweating"),test0);

	}

	/*
	 * Testing method for illSearch() in DBAccess
	 */
	@Test
	public void testIllSearch() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("appetite loss");
		test0.add("body aches");
		test0.add("chills");
		test0.add("cough");
		test0.add("dry throat");
		test0.add("fatigue");
		test0.add("fever");
		test0.add("muscle pain");
		test0.add("nasal congestion");
		test0.add("runny nose");
		test0.add("sinus pain");
		test0.add("sore throat");
		
		assertEquals(d.illSearch("Common Cold"),test0);
		
	}
	
	/*
	 * Testing method for addIll() in DBAccess
	 */
	@Test
	public void testAddIll() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		ArrayList<String> test1 = new ArrayList<String>();

		test0.add("chills");
		test0.add("cough");
		
		assertEquals(d.addIll("AIDS", test0), test1);
	}

	/*
	 * Testing method for getIlls() in DBAccess
	 */
	@Test
	public void testGetIlls() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Common Cold");
		test0.add("Tuberculosis");
		test0.add("Typhoid");
		test0.add("Yellow Fever");
		test0.add("Influenza(flu)");
		test0.add("Pneumonia");
		test0.add("Malaria");
		test0.add("Hypothermia");
		test0.add("Bronchitus");
		test0.add("Measles");
		test0.add("Hepatitis A");
		test0.add("Cholera");
		test0.add("Meningitis");
		test0.add("Cryptosporidiosis");
		test0.add("Rotavirus");
		test0.add("Hepatitis B");
		test0.add("Strep Throat");
		test0.add("Dengue");
		
		assertEquals(d.getIlls(),test0);

	}

	/*
	 * Testing method for getSymps() in DBAccess
	 */
	@Test
	public void testGetSymps() {
		DBAccess d = new DBAccess();

		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("appetite loss");
		test0.add("body aches");
		test0.add("chest pain");
		test0.add("chills");
		test0.add("clammy skin");
		test0.add("confusion");
		test0.add("constipation");
		test0.add("cough");
		test0.add("dark urine");
		test0.add("dehydration");
		test0.add("delirium");
		test0.add("diarrhea");
		test0.add("drowsiness");
		test0.add("dry throat");
		test0.add("fatigue");
		test0.add("fever");
		test0.add("headache");
		test0.add("hemorrhaging");
		test0.add("jaundice");
		test0.add("joint pain");
		test0.add("leg cramps");
		test0.add("lethargy");
		test0.add("malaise");
		test0.add("memory loss");
		test0.add("muscle pain");
		test0.add("nasal congestion");
		test0.add("nausea");
		test0.add("photophobia");
		test0.add("rash");
		test0.add("runny nose");
		test0.add("shaking");
		test0.add("shallow breath");
		test0.add("shivers");
		test0.add("sinus pain");
		test0.add("sleeping difficulty");
		test0.add("slurred speech");
		test0.add("sore throat");
		test0.add("stiff neck");
		test0.add("stomach cramps");
		test0.add("stomach pain");
		test0.add("sweating");
		test0.add("upset stomach");
		test0.add("vomiting");
		test0.add("weakness");
		test0.add("weight loss");
		test0.add("wheezing");
		
		assertEquals(d.getSymps(),test0);
	}

	/*
	 * Testing method for newSymptom() in DBAccess
	 */
	@Test
	public void testNewSymptom() {
		DBAccess d = new DBAccess();
		assertEquals(d.newSymptom("cold feet"), true);
		
	}

	/*
	 * Testing method for delIllSymp() in DBAccess
	 */
	@Test
	public void testDelIllSymp() {
		DBAccess d = new DBAccess();
		assertEquals(d.delIllSymp("Hepatitus B", "stomach pain"), true);
		assertEquals(d.delIllSymp("Hepatitus 123", "stomach pain"), false);

	}

	/*
	 * Testing method for delIll() in DBAccess
	 */
	@Test
	public void testDelIll() {
		DBAccess d = new DBAccess();
		assertEquals(d.delIll("Hepatitus B"), true);
	}

	/*
	 * Testing method for getUsers() in DBAccess
	 */
	@Test
	public void testGetUsers() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("doc0");
		test0.add("admin");
		test0.add("pat5");
		test0.add("admin4");
		test0.add("pat2");
		test0.add("pat");
		test0.add("pat4");
		test0.add("pat3");
		test0.add("admin2");
		test0.add("admin3");
		test0.add("pat00");
		test0.add("pat6");
		test0.add("doc");
		test0.add("doc3");
		test0.add("doc2");
		test0.add("doc5");
		test0.add("doc4");

		assertEquals(d.getUsers(), test0);
		
	}

	/*
	 * Testing method for getType() in DBAccess
	 */
	@Test
	public void testGetType() {
		DBAccess d = new DBAccess();
		assertEquals(d.getType("doc0"), "D");
		assertEquals(d.getType("admin"), "A");
	}

	/*
	 * Testing method for getID(String) in DBAccess
	 */
	@Test
	public void testGetIDString() {
		DBAccess d = new DBAccess();
		assertEquals(d.getID("doc0"), 11067);
		assertEquals(d.getID("admin"), 11211);	
		
	}

	/*
	 * Testing method for getID(String,String) in DBAccess
	 */
	@Test
	public void testGetIDStringString() {
		DBAccess d = new DBAccess();
		assertEquals(d.getID("Hank", "Straman"), 11324);
		assertEquals(d.getID("Fred", "Armito"), 12552);	
	}

	/*
	 * Testing method for getName() in DBAccess
	 */
	@Test
	public void testGetName() {
		DBAccess d = new DBAccess();
		assertEquals(d.getName(98011, "D"), "Frank Charles");
		
	}

	/*
	 * Testing method for getPass() in DBAccess
	 */
	@Test
	public void testGetPass() {
		DBAccess d = new DBAccess();
		assertEquals(d.getPass(98011), "doc");
		assertEquals(d.getPass(55980), "pat00");
	}

	/*
	 * Testing method for getSpec() in DBAccess
	 */
	@Test
	public void testGetSpec() {
		DBAccess d = new DBAccess();
		assertEquals(d.getSpec(98011), "cardiologist");
		assertEquals(d.getSpec(98553), "family practitioner");

	}

	/*
	 * Testing method for getPatients() in DBAccess
	 */
	@Test
	public void testGetPatients() {
		DBAccess d = new DBAccess();
		ArrayList<Integer> test0 = new ArrayList<Integer>();
		test0.add(13452);
		test0.add(11324);
		test0.add(15532);
		test0.add(16783);
		assertEquals(d.getPatients(98011), test0);
		
	}

//	@Test
//	public void testGetPatientsLess() {
//		fail("Not yet implemented");
//	}

	/*
	 * Testing method for getHeight() in DBAccess
	 */
	@Test
	public void testGetHeight() {
		DBAccess d = new DBAccess();
		assertEquals(d.getHeight(11324), 70);
		assertEquals(d.getHeight(12552), 73);

	}

	/*
	 * Testing method for getWeight() in DBAccess
	 */
	@Test
	public void testGetWeight() {
		DBAccess d = new DBAccess();
		assertEquals(d.getWeight(11324), 275);
		assertEquals(d.getWeight(12552), 250);
		
	}

	/*
	 * Testing method for getInsurance() in DBAccess
	 */
	@Test
	public void testGetInsurance() {
		DBAccess d = new DBAccess();
		assertEquals(d.getInsurance(11324), "priority health");
		assertEquals(d.getInsurance(12552), "humana");
		
	}

	/*
	 * Testing method for addUser() in DBAccess
	 */
	@Test
	public void testAddUser() {
		DBAccess d = new DBAccess();
		assertEquals(d.addUser("A", 123, "bobby", "bill", 50, 200, "Life insurance", "none", "bob123", "hey123"), true);
		
	}

	/*
	 * Testing method for delUser() in DBAccess
	 */
	@Test
	public void testDelUser() {
		DBAccess d = new DBAccess();
		d.addUser("A", 123, "bobby", "bill", 50, 200, "Life insurance", "none", "bob123", "hey123");
		assertEquals(d.delUser("bob123"), true);

	}

	/*
	 * Testing method for getPatSymps() in DBAccess
	 */
	@Test
	public void testGetPatSymps() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("sinus pain");
		test0.add("upset stomach");
		test0.add("vomiting");
		test0.add("weakness");
		
		assertEquals(d.getPatSymps(16783), test0);
		
	}

	/*
	 * Testing method for getPatDocs() in DBAccess
	 */
	@Test
	public void testGetPatDocs() {
		DBAccess d = new DBAccess();
		ArrayList<String> test0 = new ArrayList<String>();
		test0.add("Phillip VanHoven");
		test0.add("Timothy Matthews");
		test0.add("Frederic Opstein");
		
		assertEquals(d.getPatDocs(77541), test0);
		
	}

//	@Test
//	public void testGetPatientInfo() {
//		fail("Not yet implemented");
//	}

	/*
	 * Testing method for removePatient() in DBAccess
	 */
	@Test
	public void testRemovePatient() {
		DBAccess d = new DBAccess();
		assertEquals(d.removePatient(98011, 11324), true);
		assertEquals(d.removePatient(98011, 000012), false);
	}

	/*
	 * Testing method for addPatient() in DBAccess
	 */
	@Test
	public void testAddPatient() {
		DBAccess d = new DBAccess();
		assertEquals(d.addPatient(98011, 444444), true);
		assertEquals(d.addPatient(98011, 3313321), true);
		
	}

	/*
	 * Testing method for removeSymptom() in DBAccess
	 */
	@Test
	public void testRemoveSymptom() {
		DBAccess d = new DBAccess();
		assertEquals(d.removeSymptom(11324, "aches"), true);
		assertEquals(d.removeSymptom(55980, "shallow breath"), true);
		
	}

	/*
	 * Testing method for giveSymptom() in DBAccess
	 */
	@Test
	public void testGiveSymptom() {
		DBAccess d = new DBAccess();
		assertEquals(d.giveSymptom(11324, "cold feet"), true);
		assertEquals(d.giveSymptom(55980, "balding"), true);
		
	}

}
