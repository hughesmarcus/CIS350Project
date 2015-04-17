import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Illness dialog box that extends JDialog in order to
 * inform the user of illness realted aspects.
 */
public class addIllnessDialog extends JDialog {

	/**
	 * JPanel of content that uses illness instances.
	 */
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Text field used for illness instances.
	 */
	private JTextField nameField;
	
	/**
	 * db used to access our DBAccess class so that way
	 * all aspects of our program are in sync.
	 */
	private DBAccess db;
	
	/**
	 * Array list of new symptoms that are added in
	 * whenever a new illness is instanciated.
	 */
	private ArrayList<String> newSymptoms;

	/**
	 * Create the dialog.
	 */
	public addIllnessDialog() {
		db = new DBAccess();
		newSymptoms = new ArrayList<String>();
		setVisible(true);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\mediicon.jpg"));//comment this out
		setTitle("Add Illness");
		setAlwaysOnTop(true);
		setBounds(100, 100, 452, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblName.setBounds(10, 30, 45, 20);
		contentPanel.add(lblName);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameField.setBounds(55, 30, 150, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSymptoms.setBounds(10, 60, 70, 20);
		contentPanel.add(lblSymptoms);
		
		final JComboBox sympsBox = new JComboBox();
		//fill combo box with symptoms
		ArrayList<String> symptoms = db.getSymps();
		for (int i = 0; i < symptoms.size(); i++) {
			sympsBox.addItem(symptoms.get(i));	
		}
		sympsBox.setBounds(240, 31, 170, 20);
		contentPanel.add(sympsBox);
		
		JScrollPane sympsPane = new JScrollPane();
		sympsPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		sympsPane.setBounds(90, 60, 115, 160);
		contentPanel.add(sympsPane);
		
		final JTextArea sympsArea = new JTextArea();
		sympsArea.setEnabled(false);
		sympsArea.setEditable(false);
		sympsPane.setViewportView(sympsArea);
		
		JButton btnAddSymptom = new JButton("Add Symptom");
		btnAddSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String addSymp = (String) sympsBox.getSelectedItem();
				boolean in = false;
				for (int i = 0; i < newSymptoms.size(); i++) {
					if (newSymptoms.get(i).equals(addSymp)){
						in = true;
					}
				}
				if (!in) {
					sympsArea.append(addSymp + "\n");
					newSymptoms.add(addSymp);
				}
			}
		});
		btnAddSymptom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddSymptom.setBounds(240, 65, 120, 25);
		contentPanel.add(btnAddSymptom);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent arg0) {
						String illName = nameField.getText();
						ArrayList<String> add = db.addIll(illName, newSymptoms);
						MedGui.updateIllnessList();
						nameField.setText("");
						sympsArea.setText("");
						setVisible(false);
					}
				});
				okButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						nameField.setText("");
						sympsArea.setText("");
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
