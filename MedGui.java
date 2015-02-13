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

import org.eclipse.wb.swing.DBAccess;
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
	static JPanel editPanel;
	private JTextField addSympField;
	DBAccess DB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
//					LoginDialog login = new LoginDialog();
//					login.setVisible(true);
					
					MedGui gui = new MedGui();
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
		MedGui window = new MedGui();
	}
	
	public static void doctorView()
	{
		tabbedPane.addTab("Record", null, recordPanel, null);
		tabbedPane.addTab("Edit", null, editPanel, null);
		//tabbedPane.addTab("Patients", null, patientsPanel, null);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DB = new DBAccess();
		
		frame = new JFrame("MediApp");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(2, 0, 442, 382);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);
		
		JTextArea sympTxtArea = new JTextArea();
		sympTxtArea.setLineWrap(true);
		sympTxtArea.setWrapStyleWord(true);
		
		recordPanel = new JPanel();
		editPanel = new JPanel();
		editPanel.setLayout(null);
		
		//get list of ills
		JList illList = new JList();
		illList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		illList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		illList.setBounds(10, 45, 150, 296);
		editPanel.add(illList);
		
		addSympField = new JTextField();
		addSympField.setBounds(170, 99, 148, 33);
		editPanel.add(addSympField);
		addSympField.setColumns(10);
		
		JButton btnAddSymptom = new JButton("Add Symptom");
		btnAddSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add the inputed symptom to the selected illness if it doesn't already have it
				String newSymp = addSympField.getText();
				Object selIll = illList.getSelectedValue();//this should always be a string since the sympList should only be holding an array of strings
			}
		});
		btnAddSymptom.setBounds(328, 99, 99, 33);
		editPanel.add(btnAddSymptom);
		
		JButton btnRemoveSymptom = new JButton("Remove Symptom");
		btnRemoveSymptom.setBounds(279, 55, 119, 33);
		editPanel.add(btnRemoveSymptom);
		
		JButton btnDelIll = new JButton("Delete Illness");
		btnDelIll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete the selected illness
				Object rmIll = illList.getSelectedValue();
			}
		});
		btnDelIll.setBounds(170, 55, 99, 33);
		editPanel.add(btnDelIll);
		
		JLabel lblIllnesses = new JLabel("Illnesses");
		lblIllnesses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIllnesses.setBounds(10, 25, 46, 14);
		editPanel.add(lblIllnesses);
		
		JList sympList = new JList();
		sympList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sympList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sympList.setBounds(170, 160, 150, 181);
		editPanel.add(sympList);
		
		btnRemoveSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove the selected symptom from the selected illness
				Object rmSymp = sympList.getSelectedValue();
			}
		});
		
		JLabel lblSymptoms_1 = new JLabel("Symptoms");
		lblSymptoms_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSymptoms_1.setBounds(170, 140, 67, 14);
		editPanel.add(lblSymptoms_1);
		searchPanel = new JPanel();
		//patientsPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchPanel, null);
		
		
		searchPanel.setBounds(405, 25, 354, 317);
		searchPanel.setLayout(null);
		
		JLabel searchLbl = new JLabel("Search: ");
		searchLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchLbl.setBounds(42, 59, 58, 28);
		searchPanel.add(searchLbl);
		
		searchField = new JTextField();
		searchField.setBounds(110, 54, 270, 33);
		searchPanel.add(searchField);
		searchField.setColumns(10);
		
		JScrollPane resultScrlPn = new JScrollPane();
		resultScrlPn.setBounds(110, 98, 270, 196);
		searchPanel.add(resultScrlPn);
		
		JTextArea searchResultsArea = new JTextArea();
		searchResultsArea.setWrapStyleWord(true);
		searchResultsArea.setLineWrap(true);
		searchResultsArea.setEditable(false);
		resultScrlPn.setViewportView(searchResultsArea);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResults.setBounds(42, 95, 64, 29);
		searchPanel.add(lblResults);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(129, 307, 88, 33);
		searchPanel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchResultsArea.setText("");
				searchField.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.setBounds(258, 307, 88, 33);
		searchPanel.add(btnClear);
		String[] searchOptions = {"Symptom", "Illness"};
		JComboBox searchOptBox = new JComboBox(searchOptions);
		searchOptBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchOptBox.setEditable(false);
		searchOptBox.setBounds(110, 12, 127, 26);
		searchPanel.add(searchOptBox);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//determine if searching for symptom or illness
				Object option = searchOptBox.getSelectedItem();
				String search = searchField.getText();
				if(option.equals("Symptom"))
				{
					ArrayList<String> symptoms = DB.sympSearch(search);
					String results = "";
					for(int i = 0; i < symptoms.size(); i++)
					{
						results += symptoms.get(i) + "\n";
					}
					searchResultsArea.setText(results);
				}
				if(option.equals("Illness"))
				{
					
				}
				//search for it
				String searchFor = searchField.getText();
			}
		});
		
		JLabel lblSearchOptions = new JLabel("Search for");
		lblSearchOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchOptions.setBounds(20, 11, 80, 28);
		searchPanel.add(lblSearchOptions);
		recordPanel.setLayout(null);
		
//		patientsPanel.setLayout(null);
//		JList patientList = new JList();
//		patientList.setBounds(10, 11, 130, 330);
//		patientList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		patientsPanel.add(patientList);
		
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
		clrBtn.setBounds(258, 308, 88, 33);
		recordPanel.add(clrBtn);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(129, 307, 88, 33);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symps = sympTxtArea.getText();
				String name = nameField.getText();
				//pass the symptoms and name to the save method to be added to the database
			}
		});
		recordPanel.add(btnSave);
		
		JScrollPane sympScrlPn = new JScrollPane();
		sympScrlPn.setBounds(110, 52, 270, 245);
		recordPanel.add(sympScrlPn);
		
		sympScrlPn.setViewportView(sympTxtArea);
		recordPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{sympScrlPn, sympTxtArea, lblSymptoms, btnSave, clrBtn, lblName, nameField}));
		
		doctorView();
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
