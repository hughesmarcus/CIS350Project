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

	public ArrayList<String> getSymps(String ill) {
		ArrayList<String> symps = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT DISTINCT Symptom FROM illness WHERE Illness_Name = '"
							+ ill + "';");
			while (res.next()) {
				String illness = res.getString("Symptom");
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
	public ArrayList<String> adddoctor(Doctor D){
		ArrayList<String> reject = new ArrayList<String>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			try {
				st.executeUpdate("INSERT INTO doctor VALUES('"+ D.getdID() +"', '" +D.getdfName()+"', '" + D.getdlName()+"', '"+D.getPatients()+ "');");
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException err) {
				reject.add(D.getdlName());
			}
		
		conn.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return reject;
		
	}
	public ArrayList<Doctor> getDoc() {
		ArrayList<Doctor> Doc = new ArrayList<Doctor>();
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url + dbName,
					userName, password);
			Statement st = conn.createStatement();
			ResultSet res = st
					.executeQuery("SELECT DISTINCT Did FROM doctor;");
			ResultSet res1 = st
					.executeQuery("SELECT DISTINCT Dfname FROM doctor;");
			ResultSet res2 = st
					.executeQuery("SELECT DISTINCT Dlname FROM doctor;");
			while (res.next()) {
				Doctor Doc1= new Doctor(res.getString("Did"),res1.getString("dfname"), res2.getString("dlname")  );
				
				Doc.add(Doc1);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Doc;
	}
	
}
