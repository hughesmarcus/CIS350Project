import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * Class that deals with all the dialog the user sees when loging into the
 * database.
 * @author Silas
 *
 */
public class LoginDialog extends JDialog {
	
	/**
	 * JPanel used to hold the content of all the login information.
	 */
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * textfield that is used to hold the text info for login. 
	 */
	private JTextField textField;
	
	/**
	 * password field that holds the encrypted password for the 
	 * user that is loging into the system.
	 */
	private JPasswordField passwordField;
	
	/**
	 * DB instance that keeps track of the updated field
	 * and allows the entire program to stay in sync.
	 */
	DBAccess DB;
	
	/**
	 * string that holds the username trying to login.
	 */
	private String userName;

	/**
	 * Method that gets the username trying to login.
	 * @return userName the name of the user loging into the system.
	 */
	public final String getUsername() {
		return userName;
	}
	
	/**
	 * Launch the application.
	 * 
	 * @param args the arguements for the method to run.
	 */
	public static void main(final String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		DB = new DBAccess();

		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Login");
		setBounds(100, 100, 355, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 160);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(10, 35, 100, 30);
		panel.add(lblUsername);

		textField = new JTextField();
		textField.setBounds(95, 35, 200, 30);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 89, 75, 30);
		panel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(95, 96, 200, 30);
		panel.add(passwordField);

		final JLabel lblInvalid = new JLabel("");
		lblInvalid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvalid.setBounds(10, 10, 300, 14);
		panel.add(lblInvalid);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// check if the username and password are valid
				String user = textField.getText();
				userName = user;
				String pass = passwordField.getText();
				String type = DB.login(user, pass);
				// determine if they belong to a doctor or a patient
				if (type.equals("D")) {
					//display main frame in doctor view	
					MedGui frame = new MedGui(type, user);
					frame.showFrame();
					setVisible(false);
				} else if (type.equals("P")) {
					//display main frame in patient view
					MedGui frame = new MedGui(type, user);
					frame.showFrame();
					setVisible(false);
				} else if (type.equals("A")) {
					//display main frame in admin view
					MedGui frame = new MedGui(type, user);
					frame.showFrame();
					setVisible(false);
				} else {
					// display wrong username/password
					lblInvalid.setText("Invalid username/password combination");
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(100);
			}
		});
		buttonPane.add(cancelButton);

	}

}
