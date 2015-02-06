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
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Window.Type;


public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JLabel lblInvalid = new JLabel("");
		lblInvalid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvalid.setBounds(10, 10, 300, 14);
		panel.add(lblInvalid);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//check if the username and password are valid
						String user = textField.getText();
						String pass = passwordField.getText();
						//determine if they belong to a doctor or a patient
						if(user.equals("doc"))
						{
							MedGui.doctorView();//record and patients tabs only get added if a doctor is logging in
							MedGui.showFrame();
							setVisible(false);
						}
						else if(user.equals("pat"))
						{
							MedGui.showFrame();
							setVisible(false);
						}
						else
						{
							//display wrong username/password
							lblInvalid.setText("Invalid username/password combination");
						}
					}
				}
			);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(100);
						}
					}
				);
				buttonPane.add(cancelButton);
			}
		}
	}
}
