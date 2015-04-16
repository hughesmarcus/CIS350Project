/**
 * @author Chad Mersino CIS 350 Admin.java class for project.
 *
 */

public class Admin {
	/**
	 * Admin Id.
	 */
	private int aID;
	/**
	 * Admin FIrst name.
	 */
	private String fName;
	/**
	 * admin last name.
	 */
	private String lName;

	/**
	 * Admin constructor.
	 * 
	 * @param id
	 *            the id of the admin who runs the database
	 * @param first
	 *            the first name of the admin
	 * @param last
	 *            the last name of the admin
	 */
	public Admin(final int id, final String first, final String last) {
		aID = id;
		fName = first;
		lName = last;

	}

	/**
	 * return admin id.
	 * @return aID
	 */
	final int getaID() {

		return aID;
	}

	/**
	 * set the Admin ID. 
	 * @param aID
	 *            the id of the admin who runs the database.
	 */
	public final void setaID(final int aID) {

		this.aID = aID;
	}

	/**
	 * return first name.
	 * @return fName
	 */
	public final String getfName() {

		return fName;
	}

	/**
	 * set first name.
	 * @param fName first name
	 */
	public final void setafName(final String fName) {

		this.fName = fName;
	}

	/**
	 * return last name.
	 * @return lName
	 */
	public final String getlName() {

		return lName;
	}

	/**
	 * set last name.
	 * @param lName last name of admin.
	 */
	public final void setalName(final String lName) {

		this.lName = lName;
	}
}
