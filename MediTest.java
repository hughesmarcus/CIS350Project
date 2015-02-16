import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author marcus
 *
 */
public class MediTest {
	private Doctor d1;
	private Doctor d2;
	private Doctor d3;
	private Patient p1;
	private Patient p2;
	private Patient p3;
	private Illness i1;
	private Illness i2;

	protected void setUp() throws Exception {
		d1 = new Doctor("79435", "Marcus", "Hughes");
		d2 = new Doctor(null, null, null);
		d3 = new Doctor("43257", "Jance", "No");

	}

	@Test
	public void testGetdlname() throws Exception {
		assertEquals("Hughes", d1.getdlName());
		assertEquals("No", d3.getdlName());
	}

	@Test
	public void testGetdfname() throws Exception {
		assertEquals("Marcus", d1.getdfName());
		assertEquals("Jance", d3.getdfName());
	}

	@Test
	public void testGetdID() throws Exception {
		assertEquals("79435", d1.getdID());
		assertEquals("43257", d3.getdID());
	}

	@Test
	public void testSetdID() throws Exception {
		d1.setdID("A43546");
		assertEquals("A43546", d1.getdID());

		d2.setdID("89430");
		assertEquals("89430", d2.getdID());
		d3.setdID("Po012");
		assertEquals("Po012", d3.getdID());
	}

	@Test
	public void testSetdfname() throws Exception {
		d1.setdfName("Dog");
		assertEquals("Dog", d1.getdfName());

		d2.setdfName("Leyva");
		assertEquals("Leyva", d2.getdfName());
		d3.setdfName("Pickle");
		assertEquals("Pickle", d3.getdfName());
	}

	@Test
	public void testSetdlname() throws Exception {
		d1.setdlName("Dog");
		assertEquals("Dog", d1.getdlName());

		d2.setdlName("Leyva");
		assertEquals("Leyva", d2.getdlName());
		d3.setdlName("Pickle");
		assertEquals("Pickle", d3.getdlName());
	}

	// Patient test
	protected void setUpP() throws Exception {
		Patient p1 = new Patient(null, null, null, 0, 0, null);
		Patient p2 = new Patient("u89023", "Marcus", "Hughes", 72, 230,
				"Tricare");
		Patient p3 = new Patient("u586549", "Tom", "Harmen", 68, 130, "Mecare");
	}

	@Test
	public void testGetlname() throws Exception {
		assertEquals("Hughes", p2.getlName());
		assertEquals("Harmen", p3.getlName());

	}

	@Test
	public void testGetfname() throws Exception {
		assertEquals("Marcus", p2.getfName());
		assertEquals("Tom", p3.getfName());
	}

	@Test
	public void testGetID() throws Exception {
		assertEquals(null, p1.getpID());
		assertEquals("u586549", p3.getpID());
	}

	@Test
	public void testGetHeight() throws Exception {
		assertEquals(72, p2.getHeight());
		assertEquals(68, p3.getHeight());
	}

	public void testGetWeight() throws Exception {
		assertEquals(230, p2.getWeight());
		assertEquals(130, p3.getWeight());
	}

	public void testInsurance() {
		assertEquals("Tricare", p2.getInsurance());
	}

	@Test
	public void testSetID() throws Exception {
		p1.setpID("A43546");
		assertEquals("A43546", p1.getpID());

		p2.setpID("89430");
		assertEquals("89430", p2.getpID());
		p3.setpID("Po012");
		assertEquals("Po012", p3.getpID());
	}

	@Test
	public void testSetfname() throws Exception {
		p1.setfName("Dog");
		assertEquals("Dog", p1.getfName());

		p2.setfName("Leyva");
		assertEquals("Leyva", p2.getfName());
		p3.setfName("Pickle");
		assertEquals("Pickle", p3.getfName());
	}

	@Test
	public void testSetlname() throws Exception {
		p1.setlName("Dog");
		assertEquals("Dog", p1.getlName());

		p2.setlName("Leyva");
		assertEquals("Leyva", p2.getlName());
		p3.setlName("Pickle");
		assertEquals("Pickle", p3.getlName());
	}

	//
	protected void setupI() {
		i1 = new Illness("chicken pox", "rest");
		i2 = new Illness("2", "math");
	}

	/**
	 * Test method for {@link Illness#getillname()}.
	 */
	@Test
	public final void testGetillname() {
		assertEquals("chicken pox", i1.getillname());
	}

	/**
	 * Test method for {@link Illness#setillname(java.lang.String)}.
	 */
	@Test
	public final void testSetillname() {
		i2.setillname("Dog");
		assertEquals("Dog", i2.getillname());
	}

	/**
	 * Test method for Illness gettreatment()}.
	 */
	@Test
	public final void testGettreatment() {
		assertEquals("chicken pox", i1.gettreatment());
	}

	/**
	 * Test method for Illness settreatment.
	 */
	@Test
	public final void testSettreatment() {
		i2.settreatment("Dog");
		assertEquals("Dog", i2.gettreatment());
	}

	/**
	 * Test method for {@link Illness#getSymptoms()}.
	 */
	@Test
	public final void testGetSymptoms() {
		assertEquals("chicken pox", i1.getSymptoms());
	}

	/**
	 * Test method for {@link Illness#addisymptom(java.lang.String)}.
	 */
	@Test
	public final void testAddisymptom() {
		fail("Not yet implemented"); // TODO
	}
}