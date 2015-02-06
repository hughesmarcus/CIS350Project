import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

public class MedGui {

	private static JFrame frame;
	private JTextField nameField;
	private static JTabbedPane tabbedPane;
	private JTextField searchField;
	static JPanel recordPanel;
	JPanel searchPanel;
	static JPanel patientsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedGui window = new MedGui();
					
					LoginDialog login = new LoginDialog();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MedGui() {
		initialize();
	}

	public static void showFrame()
	{
		frame.setVisible(true);
	}
	
	public static void doctorView()
	{
		tabbedPane.addTab("Record", null, recordPanel, null);
		tabbedPane.addTab("Patients", null, patientsPanel, null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("MediApp");
		frame.setResizable(false);
		frame.setBounds(100, 100, 398, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(2, 0, 388, 382);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(false);
		
		JTextArea sympTxtArea = new JTextArea();
		sympTxtArea.setLineWrap(true);
		sympTxtArea.setWrapStyleWord(true);
		
		recordPanel = new JPanel();
		searchPanel = new JPanel();
		patientsPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchPanel, null);
		
		
		searchPanel.setBounds(405, 25, 354, 317);
		searchPanel.setLayout(null);
		
		JLabel searchLbl = new JLabel("Search: ");
		searchLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchLbl.setBounds(42, 59, 58, 28);
		searchPanel.add(searchLbl);
		
		searchField = new JTextField();
		searchField.setBounds(110, 54, 228, 33);
		searchPanel.add(searchField);
		searchField.setColumns(10);
		
		JScrollPane resultScrlPn = new JScrollPane();
		resultScrlPn.setBounds(110, 98, 231, 196);
		searchPanel.add(resultScrlPn);
		
		JTextArea txtrILikeTo = new JTextArea();
		txtrILikeTo.setWrapStyleWord(true);
		txtrILikeTo.setLineWrap(true);
		resultScrlPn.setViewportView(txtrILikeTo);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResults.setBounds(42, 95, 64, 29);
		searchPanel.add(lblResults);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(129, 307, 72, 33);
		searchPanel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.setBounds(224, 307, 72, 33);
		searchPanel.add(btnClear);
		String[] searchOptions = {"Symptom", "Illness"};
		JComboBox searchOptBox = new JComboBox(searchOptions);
		searchOptBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchOptBox.setEditable(false);
		searchOptBox.setBounds(110, 12, 127, 26);
		searchPanel.add(searchOptBox);
		
		JLabel lblSearchOptions = new JLabel("Search for");
		lblSearchOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchOptions.setBounds(20, 11, 80, 28);
		searchPanel.add(lblSearchOptions);
		recordPanel.setLayout(null);
		
		String[] patients = getPatients();
		patientsPanel.setLayout(null);
		JList patientList = new JList(patients);
		patientList.setBounds(10, 11, 130, 330);
		patientList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientsPanel.add(patientList);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(45, 13, 47, 20);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		recordPanel.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(110, 8, 228, 33);
		nameField.setColumns(10);
		recordPanel.add(nameField);
		
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setBounds(12, 53, 81, 20);
		lblSymptoms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		recordPanel.add(lblSymptoms);
		
		JButton clrBtn = new JButton("Clear");
		clrBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					sympTxtArea.setText("");
					nameField.setText("");
			}
		});
		clrBtn.setBounds(224, 261, 72, 33);
		recordPanel.add(clrBtn);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(129, 261, 72, 33);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symps = sympTxtArea.getText();
				
				//pass the symptoms and name to the save method to be added to the database
			}
		});
		recordPanel.add(btnSave);
		
		JScrollPane sympScrlPn = new JScrollPane();
		sympScrlPn.setBounds(110, 52, 231, 196);
		recordPanel.add(sympScrlPn);
		
		sympScrlPn.setViewportView(sympTxtArea);
		recordPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{sympScrlPn, sympTxtArea, lblSymptoms, btnSave, clrBtn, lblName, nameField}));
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public String[] getPatients()
	{
		String[] p = {"Vincent, Silas", "Mersino, Chad", "Hughes, Marcus"};
		return p;
	}
}
