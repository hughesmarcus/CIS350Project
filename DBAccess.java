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
	 * Searches for the given illness and symptom then deletes the symptom,
	 * returns true if successful.
	 * 
	 * @param ill
	 * @param symp
	 * @return del
	 */
	public boolean delSymp(String ill, String symp) {
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
	 * retrives and return the first and last name of the user
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
			if(type.equals("Admin"))
			{
				res = st.executeQuery("SELECT Fname, Lname FROM admin WHERE aID = " + id +";");
			}
			else if(type.equals("Doctor"))
			{
				res = st.executeQuery("SELECT Fname, Lname FROM doctor WHERE dID = " + id +";");
			}
			else if(type.equals("Patient"))
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
	 * retrieves the names of all the patients that see the desired doctor
	 * @param id
	 * @return patients
	 */
	public ArrayList<String> getPatients(int id)
	{
		ArrayList<String> patients = new ArrayList<String>();
		ArrayList<Integer> pIds = new ArrayList<Integer>();
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
						.executeQuery("SELECT Fname, Lname FROM patient WHERE pID = " + pIds.get(x) +";");
				while (res.next()) {
					String fname = res.getString("Fname");
					String lname = res.getString("Lname");
					patients.add(fname + " " + lname);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
}
