import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to access the database and return the needed information.
 * @author silas
 *
 */
public class DBAccess {
	private final String url = "jdbc:mysql://localhost:3306/";
	private final String dbName = "illnessdb";
	private final String driver = "com.mysql.jdbc.Driver";
	private final int portNumber = 3306;
	private final String dbms = "mysql";
	private final String serverName = "localhost";
	private final String userName = "vincensi";
	private final String password = "Cis350";

/**
 * 
 */
	public DBAccess() {

	}

	/**
	 * Method checks to find if the username and password combination are in the
	 * users table. returns P if the user is a patient, D if the user is a
	 * doctor, or an empty string if the U/P combination is not found.
	 * 
	 * @param user
	 * @param pass
	 * @return ret
	 */
	public final String login(String user, String pass) {
		user = user.toLowerCase();
		String ret = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT username, password, type FROM users WHERE username = '"
							+ user + "' AND password = '" + pass + "';");
			while (res.next()) {
				// use getString on the result of the query
				String userNames = res.getString("username");
				userNames = userNames.toLowerCase();
				String passwords = res.getString("password");
				if (user.equals(userNames) && pass.equals(passwords)) {
					ret = res.getString("type");
				} else {
					// username and password not found
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Searches the illness table for illnesses with the given symptom and
	 * returns an arraylist of all the illnesses with that symptom.
	 * 
	 * @param symptom
	 * @return illness
	 */
	public ArrayList<String> sympSearch(String symptom) {
		ArrayList<String> illness = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT Illness_Name FROM illness WHERE Symptom = '"
							+ symptom + "';");
			while (res.next()) {
				String ill = res.getString("Illness_Name");
				illness.add(ill);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return illness;
	}

	/**
	 * Searches the illness table for an illness and returns an arraylist of the
	 * symptoms associated with that illness.
	 * 
	 * @param ill
	 * @return symps
	 */
	public ArrayList<String> illSearch(String ill) {
		ArrayList<String> symps = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT Symptom FROM illness WHERE Illness_Name = '"
							+ ill + "';");
			while (res.next()) {
				String symptom = res.getString("Symptom");
				symps.add(symptom);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symps;
	}

	/**
	 * Attempts to add the illness with the given name and symptoms to the
	 * illness table returns an arrayList of rejected symptoms.
	 * 
	 * @param ill
	 * @param symps
	 * @return reject
	 */
	public ArrayList<String> addIll(String ill, ArrayList<String> symps) {
		ArrayList<String> reject = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			for (int i = 0; i < symps.size(); i++) {
				try {
					st.executeUpdate("INSERT INTO illness VALUES('" + ill
							+ "', '" + symps.get(i) + "');");
				} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
					reject.add(symps.get(i));
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reject;
	}

	/**
	 * returns all the illnesses in the illness table.
	 * 
	 * @return ills
	 */
	public ArrayList<String> getIlls() {
		ArrayList<String> ills = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT DISTINCT Illness_Name FROM illness;");
			while (res.next()) {
				String illness = res.getString("Illness_Name");
				ills.add(illness);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ills;
	}

	/**
	 * return all the symptoms in the database
	 * @param ill
	 * @return symps
	 */
	public ArrayList<String> getSymps() {
		ArrayList<String> symps = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT * FROM symptoms;");
			while (res.next()) {
				String illness = res.getString("symptom_name");
				symps.add(illness);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symps;
	}

	/**
	 * retrieves all the symptoms associated with the given illness
	 * @param illness
	 * @return symptoms
	 */
	public ArrayList<String> getIllSymps(String illness)
	{
		ArrayList<String> symptoms = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT Symptom FROM illness WHERE Illness_Name = '" + illness + "';");
			while (res.next()) {
				String symp = res.getString("Symptom");
				symptoms.add(symp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptoms;
	}
	
	public boolean newSymptom(String symptom)
	{
		boolean valid = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("INSERT INTO symptoms VALUES ('" + symptom + "');");
				valid = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				valid = false;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * Searches for the given illness and symptom then deletes the symptom,
	 * returns true if successful.
	 * 
	 * @param ill
	 * @param symp
	 * @return del
	 */
	public boolean delIllSymp(String ill, String symp) {
		boolean del = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("DELETE FROM illness WHERE Illness_Name = '"
						+ ill + "' AND Symptom = '" + symp + "';");
				del = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				del = false;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	/**
	 * Searches for the given illness and deletes it from the illness table,
	 * returns true if successful.
	 * 
	 * @param ill
	 * @return del
	 */
	public boolean delIll(String ill) {
		boolean del = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("DELETE FROM illness WHERE Illness_Name = '"
						+ ill + "';");
				del = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				del = false;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}
	
	/**
	 * returns an arraylist that contains the usernames of all the users
	 * @return
	 */
	public ArrayList<String> getUsers()
	{
		ArrayList<String> names = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT username FROM users;");
			while (res.next()) {
				String name = res.getString("username");
				names.add(name);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * returns the user type for the inputed username
	 * @param username
	 * @return t
	 */
	public String getType(String username)
	{
		String t = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT type FROM users WHERE username = '" + username +"';");
			while (res.next()) {
				String get = res.getString("type");
				t = get;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * gets the id number for the given user using their username
	 * @param username
	 * @return id
	 */
	public int getID(String username)
	{
		int id = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT userID FROM users WHERE username = '" + username +"';");
			while (res.next()) {
				id = res.getInt("userID");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * gets the id number for the given user using their first and last names
	 * @param username
	 * @return id
	 */
	public int getID(String fName, String lName)
	{
		int id = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT pID FROM patient WHERE Fname = '" + fName +"' AND Lname = '" + lName.trim() +"';");
			while (res.next()) {
				id = res.getInt("pID");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * retrieves and return the first and last name of the user
	 * @param username
	 * @param type
	 * @return name
	 */
	public String getName(int id, String type)
	{
		String fname = "";
		String lname = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = null;
			if(type.equals("Admin") || type.equals("A"))
			{
				res = st.executeQuery("SELECT Fname, Lname FROM admin WHERE aID = " + id +";");
			}
			else if(type.equals("Doctor") || type.equals("D"))
			{
				res = st.executeQuery("SELECT Fname, Lname FROM doctor WHERE dID = " + id +";");
			}
			else if(type.equals("Patient") || type.equals("P"))
			{
				res = st.executeQuery("SELECT Fname, Lname FROM patient WHERE pID = " + id +";");
			}
			while (res.next()) {
				fname = res.getString("Fname");
				lname = res.getString("Lname");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fname + " " + lname;
	}
	
	/**
	 * gets the password for the user with the given id
	 * @param id
	 * @return pass
	 */
	public String getPass(int id)
	{
		String pass = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT password FROM users WHERE userID = " + id +";");
			while (res.next()) {
				pass = res.getString("password");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	/**
	 * gets the specialization for the given doctor
	 * @param id
	 * @return
	 */
	public String getSpec(int id)
	{
		String spec = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT specialization FROM doctor WHERE dID = " + id +";");
			while (res.next()) {
				spec = res.getString("specialization");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spec;
	}
	
	/**
	 * retrieves the patient objects of all the patients that see the desired doctor
	 * @param id
	 * @return patients
	 */
	public ArrayList<Patient> getPatients(int id)
	{
		ArrayList<Integer> pIds = new ArrayList<Integer>();
		ArrayList<Patient> pats = new ArrayList<Patient>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT patientID FROM doctor_patient WHERE doctorID = " + id +";");
			while (res.next()) {
				int i = res.getInt("patientID");
				pIds.add(i);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int x = 0; x < pIds.size(); x++)
		{
			try {
				Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url + dbName,
						userName, password);
				Statement st = conn.createStatement();
				ResultSet res = st
						.executeQuery("SELECT username FROM users WHERE userID = " + pIds.get(x) + ";");
				while (res.next()) {
					pats.add(getPatOb(res.getString("username")));
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pats;
	}
	
	/**
	 * retrieves the patient objects of all the patients that the given doctor does not see
	 * @param id
	 * @return patients
	 */
	public ArrayList<Patient> getPatientsLess(int id)
	{
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT username FROM illnessdb.users WHERE userID NOT IN(SELECT patientID from doctor_patient WHERE doctorID = " + id + ") AND type = 'P';");
			while (res.next()) {
				Patient p = getPatOb(res.getString("username"));
				patients.add(p);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	/**
	 * returns the given patients height data
	 * @param id
	 * @return
	 */
	public int getHeight(int id)
	{
		int h = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT height FROM patient WHERE pID = " + id +";");
			while (res.next()) {
				h = res.getInt("height");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	
	/**
	 * return the given patients weight data
	 * @param id
	 * @return
	 */
	public int getWeight(int id)
	{
		int w = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT weight FROM patient WHERE pID = " + id +";");
			while (res.next()) {
				w = res.getInt("weight");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return w;
	}
	
	public String getInsurance(int id)
	{
		String in = "";
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT insurance FROM patient WHERE pID = " + id +";");
			while (res.next()) {
				in = res.getString("insurance");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
	
	/**
	 * Adds a user to the database with the given info, determines the user type and first adds them
	 * to the user table then to the table corresponding to their type. Type also dictates
	 * which of the parameters are used in the inserts.
	 * @param type
	 * @param id
	 * @param fName
	 * @param lName
	 * @param h
	 * @param w
	 * @param insur
	 * @param spec
	 * @param un
	 * @param pass
	 * @return add
	 */
	public boolean addUser(String type, int id, String fName, String lName, int h, int w, String insur, String spec, String un, String pass)
	{
		boolean add = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				//"INSERT INTO illness VALUES('" + ill + "', '" + symps.get(i) + "');"
				
				st.executeUpdate("INSERT INTO users VALUES('" + id +"', '" + un + "', '" + pass + "', '" + type + "');" );
				add = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				add = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(add)
		{
			if(type.equals("A"))
			{
				try {
					Class.forName(driver).newInstance();
					Connection conn = DriverManager.getConnection(url + dbName,
							userName, password);
					Statement st = conn.createStatement();
					try {
						st.executeUpdate("INSERT INTO admin VALUES('" + id +"', '" + fName + "', '" + lName + "');" );
						add = true;
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
						add = false;//failed to insert
					}
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(type.equals("D"))
			{
				try {
					Class.forName(driver).newInstance();
					Connection conn = DriverManager.getConnection(url + dbName,
							userName, password);
					Statement st = conn.createStatement();
					try {
						st.executeUpdate("INSERT INTO doctor VALUES('" + id +"', '" + spec +"', '" + fName + "', '" + lName + "');" );
						add = true;
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
						add = false;//failed to insert
					}
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(type.equals("P"))
			{
				try {
					Class.forName(driver).newInstance();
					Connection conn = DriverManager.getConnection(url + dbName,
							userName, password);
					Statement st = conn.createStatement();
					try {
						st.executeUpdate("INSERT INTO patient VALUES('" + id +"', '" + fName + "', '" + lName + "', '" 
								+ h + "', '" + w + "', '" + insur + "');");
						add = true;
					} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
						add = false;//failed to insert
					}
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return add;
	}
	
	/**
	 * delete the specified user from the users table(which will cascade to the other appropriate tables)
	 * @param username
	 * @return del
	 */
	public boolean delUser(String username)
	{
		boolean del = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("DELETE FROM users WHERE username = '" + username +"';");
				del = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				del = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}
	
	/**
	 * returns the symptoms associated with the given patient
	 * @param id
	 * @return symptoms
	 */
	public ArrayList<String> getPatSymps(int id)
	{
		ArrayList<String> symptoms = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT symp from patient_symptom WHERE patID = " + id + ";");
			while (res.next()) {
				symptoms.add(res.getString("symp"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptoms;
	}
	
	/**
	 * returns the names of the doctors that see the given patient
	 * @param id
	 * @return docs
	 */
	public ArrayList<String> getPatDocs(int id)
	{
		ArrayList<String> docs = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT Fname, Lname FROM illnessdb.doctor WHERE dID IN(SELECT doctorID from doctor_patient WHERE patientID = " + id +");");
			while (res.next()) {
				String fname = res.getString("Fname");
				String lname = res.getString("Lname");
				docs.add(fname + " " + lname);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/**
	 * returns the info about the given patient
	 * @param id
	 * @return String info
	 */
	public String getPatientInfo(int id)
	{
		String info = "";
		ArrayList<String> symptoms = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT * FROM patient WHERE pID = " + id + ";");
			while (res.next()) {
				info += "Height: " + res.getString("height") + "\n";
				info += "Weight: " + res.getString("weight") + "\n";
				info += "Insurance: " + res.getString("insurance") + "\n";
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		info += "Symptoms: \n"; 
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT symp FROM patient_symptom WHERE patID = " + id + ";");
			while (res.next()) {
				info += "   " + res.getString("symp") + "\n";
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
	/**
	 * attempts to remove the given patient from the list of patients associated to the given doctor
	 * @param id
	 * @return boolean removed
	 */
	public boolean removePatient(int dID, int pID)
	{
		boolean removed = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("DELETE FROM doctor_patient WHERE doctorID = " + dID + " AND patientID = " + pID + ";");
				removed = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				removed = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	/**
	 * adds the selected patient to the active doctor
	 * @param id
	 * @return
	 */
	public boolean addPatient(int dID, int pID)
	{
		boolean added = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("INSERT INTO doctor_patient VALUES (" +dID+ ", " + pID + ");");
				added = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				added = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * adds the given symptom to the given patient
	 * @param id
	 * @param symptom
	 * @return boolean removed
	 */
	public boolean removeSymptom(int id, String symptom)
	{
		boolean removed = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("DELETE FROM patient_symptom WHERE patID = " + id + " AND symp = '" + symptom + "';");
				removed = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				removed = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removed;
	}
	
	/**
	 * assigns the given symptom to the given patient
	 * @param id
	 * @param symptom
	 * @return boolean added
	 */
	public boolean giveSymptom(int id, String symptom)
	{
		boolean added = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("INSERT INTO patient_symptom VALUES (" + id + ", '" + symptom + "');");
				added = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				added = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * returns the list of names of people that have sent messages to the user 
	 * @param id
	 * @param type
	 * @return from
	 */
	public ArrayList<String> getMessages(int id, String type)
	{
		ArrayList<String> from = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = null;
			if(type.equals("P"))
			{
				res = st
						.executeQuery("SELECT Lname, Fname FROM doctor WHERE dID IN(SELECT fromID FROM message WHERE toID = " + id + ");");
			}
			else if(type.equals("D"))
			{
				res = st
					.executeQuery("SELECT Lname, Fname FROM patient WHERE pID IN(SELECT fromID FROM message WHERE toID = " + id + ");");
			}
			while (res.next()) {
				from.add(res.getString("Fname") + " " + res.getString("Lname"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return from;
	}
	
	/**
	 * gets the text of the message sent from the 'name' to toID
	 * @param name
	 * @param userType
	 * @return String text
	 */
	public String getText(String name, String userType, int toID)
	{
		String text = "";
		String fname = name.substring(0, name.indexOf(" "));
		String lname = name.substring(name.indexOf(" ") + 1, name.length());
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = null;
			if(userType.equals("P"))
			{
				res = st
						.executeQuery("SELECT text FROM message WHERE fromID IN(SELECT dID FROM doctor WHERE Fname = '" + fname + "' AND Lname = '" + lname + "') AND toID = " + toID + ";");
			}
			else if(userType.equals("D"))
			{
				res = st
					.executeQuery("SELECT text FROM message WHERE fromID IN(SELECT pID FROM patient WHERE Fname = '" + fname + "' AND Lname = '" + lname + "') AND toID = " + toID + ";");
			}
			while (res.next()) {
				text = res.getString("text");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * deletes the message from name to toID 
	 * @param name
	 * @param userType
	 * @return boolean del
	 */
	public boolean delMessage(String name, String userType, int toID)
	{
		boolean del = false;
		String fname = name.substring(0, name.indexOf(" "));
		String lname = name.substring(name.indexOf(" ") + 1, name.length());
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				if(userType.equals("P"))
				{
					st.executeUpdate("DELETE FROM message WHERE fromID IN(SELECT dID FROM doctor WHERE Fname = '" + fname + "' AND Lname = '" + lname + "') AND toID = " + toID + ";");
				}
				else if(userType.equals("D"))
				{
					st.executeUpdate("DELETE FROM message WHERE fromID IN(SELECT pID FROM patient WHERE Fname = '" + fname + "' AND Lname = '" + lname + "' AND toID = " + toID + ");");
				}
				del = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				del = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}
	
	/**
	 * add the message to the message table
	 * @param from
	 * @param to
	 * @param text
	 * @param userType
	 * @return sent
	 */
	public boolean sendMessage(int from, String to, String text, String userType)
	{
		boolean sent = false;
		String fname = to.substring(0, to.indexOf(" "));
		String lname = to.substring(to.indexOf(" ") + 1, to.length());
		int toID = 0;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				if(userType.equals("D"))
				{
					ResultSet res = st.executeQuery("SELECT pID FROM patient WHERE Fname = '" + fname + "' AND Lname = '" + lname + "';");
					while(res.next())
					{
						toID = res.getInt("pID");
					}
					st.executeUpdate("INSERT INTO message VALUES('" + from + "', '" + toID + "', '" + text + "')");
				}
				else 	if(userType.equals("P"))
				{
					ResultSet res = st.executeQuery("SELECT dID FROM doctor WHERE Fname = '" + fname + "' AND Lname = '" + lname + "';");
					while(res.next())
					{
						toID = res.getInt("dID");
					}
					st.executeUpdate("INSERT INTO message VALUES('" + from + "', '" + toID + "', '" + text + "')");
				}
				sent = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				sent = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sent;
	}
	
	public Doctor getDocOb(String username)
	{
		Doctor d = null;
		int userID = getID(username);
		String name = getName(userID, "D");
		String fName = name.substring(0, name.indexOf(" "));
		String lName = name.substring(name.indexOf(" "), name.length());
		String spec = getSpec(userID);
		d = new Doctor(userID, fName, lName, spec);
		return d;
	}
	
	public Patient getPatOb(String username)
	{
		Patient p = null;
		int userID = getID(username);
		String patName = getName(userID, "P");
		String fName = patName.substring(0, patName.indexOf(" "));
		String lName = patName.substring(patName.indexOf(" "), patName.length());
		p = new Patient(userID, fName, lName, getHeight(userID), getWeight(userID), getInsurance(userID));
		return p;
	}
	
	public ArrayList<String> getAllMeds()
	{
		ArrayList<String> meds = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("Select medName from medications;");
			while (res.next()) {
				String m = res.getString("medName");
				meds.add(m);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meds;
	}
	
	public ArrayList<String> getPatMeds(int id)
	{
		ArrayList<String> meds = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("Select med from med_pat where pat = " + id + ";");
			while (res.next()) {
				String m = res.getString("med");
				meds.add(m);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meds;
	}
	
	public boolean prescribeMed(String med, int pID)
	{
		boolean pre = false;
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("INSERT INTO med_pat VALUES ('" + med + "', " + pID + ");");
				pre = true;
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				pre = false;//failed to insert
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pre;
	}
}
