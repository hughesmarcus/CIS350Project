import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main GUI class for the MediApp.
 * @author silas
 *
 */
public class MedGui {
	/**
	 * the main frame.
	 */
	private static JFrame frame;
	/**
	 * name field.
	 */
	private JTextField nameField;
	/**
	 * tabbed pane.
	 */
	private static JTabbedPane tabbedPane;
	/**
	 * search field.
	 */
	private JTextField searchField;
	/**
	 * record panel.
	 */
	private static JPanel recordPanel;
	/**
	 * search panel.
	 */
	private JPanel searchPanel;
	/**
	 * patients panel.
	 */
	private static JPanel patientsPanel;
	/**
	 * edit panel.
	 */
	private static JPanel editPanel;
	/**
	 * add symptom field.
	 */
	private JTextField addSympField;
	/**
	 * illness list.
	 */
	private DefaultListModel illnessList;
	/**
	 * symptom list.
	 */
	private DefaultListModel symptomList;
	/**
	 * database access.
	 */
	private DBAccess DB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog login = new LoginDialog();
					login.setVisible(true);

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

	/**
	 * Show Frame.
	 */
	public static void showFrame() {
		MedGui window = new MedGui();
		frame.setVisible(true);
	}

	/**
	 * Shows view for doctors.
	 */
	public static void doctorView() {
		tabbedPane.addTab("Record", null, recordPanel, null);
		tabbedPane.addTab("Edit", null, editPanel, null);
		// tabbedPane.addTab("Patients", null, patientsPanel, null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DB = new DBAccess();

		frame = new JFrame("MediApp");
		frame.setResizable(false);
		frame.setBounds(100, 100, 490, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(2, 0, 482, 382);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(false);

		final JTextArea sympTxtArea = new JTextArea();
		sympTxtArea.setLineWrap(true);
		sympTxtArea.setWrapStyleWord(true);

		recordPanel = new JPanel();
		editPanel = new JPanel();
		editPanel.setLayout(null);
		symptomList = new DefaultListModel();
		final JList sympList = new JList(symptomList);
		sympList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		sympList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sympList.setBounds(170, 160, 150, 181);

		illnessList = new DefaultListModel();
		ArrayList<String> ills = DB.getIlls();
		setIllList();
		final JList illList = new JList(illnessList);
		illList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				symptomList.clear();
				String selected = (String) illList.getSelectedValue();
				ArrayList<String> symps = DB.getSymps(selected);
				for (int i = 0; i < symps.size(); i++) {
					symptomList.addElement(symps.get(i));
				}

			}
		});
		illList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		illList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		illList.setBounds(10, 45, 150, 296);
		editPanel.add(illList);

		addSympField = new JTextField();
		addSympField.setBounds(170, 99, 148, 33);
		editPanel.add(addSympField);
		addSympField.setColumns(10);

		JButton btnAddSymptom = new JButton("Add Symptom");
		btnAddSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// add the inputed symptom to the selected illness if it doesn't
				// already have it
				String newSymp = addSympField.getText();
				String illName = (String) illList.getSelectedValue();
				ArrayList<String> symptoms = new ArrayList<String>();
				symptoms.add(newSymp);
				try {
					if (illName.equals("") || illName.equals(null)
							|| newSymp.equals("") || newSymp.equals(null)) {
						JOptionPane.showMessageDialog(frame,
								"Please select an illness and enter a Symptom");
					} else {
						ArrayList<String> result = DB.addIll(illName, symptoms);
						if (result.size() < 1) {
							JOptionPane.showMessageDialog(frame,
									"Symptom successfully added");
							symptomList.addElement(newSymp);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Symptom has already been entered");
						}
					}
				} catch (NullPointerException er) {
					JOptionPane.showMessageDialog(frame,
							"Please select an illness and enter a Symptom");
				}

			}
		});
		btnAddSymptom.setBounds(328, 99, 139, 33);
		editPanel.add(btnAddSymptom);

		JButton btnRemoveSymptom = new JButton("Remove Symptom");
		btnRemoveSymptom.setBounds(307, 55, 160, 33);
		editPanel.add(btnRemoveSymptom);

		JButton btnDelIll = new JButton("Delete Illness");
		btnDelIll.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// delete the selected illness
				String rmIll = (String) illList.getSelectedValue();
				boolean del = DB.delIll(rmIll);
				if (del) {
					JOptionPane.showMessageDialog(frame,
							"Illness successfully removed");
					setIllList();
					symptomList.clear();
				} else {
					JOptionPane.showMessageDialog(frame, "No such illness");
				}
			}
		});
		btnDelIll.setBounds(170, 55, 127, 33);
		editPanel.add(btnDelIll);

		JLabel lblIllnesses = new JLabel("Illnesses");
		lblIllnesses.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIllnesses.setBounds(10, 25, 46, 14);
		editPanel.add(lblIllnesses);
		editPanel.add(sympList);

		btnRemoveSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// remove the selected symptom from the selected illness
				String rmSymp = (String) sympList.getSelectedValue();
				int index = sympList.getSelectedIndex();
				String illName = (String) illList.getSelectedValue();

				boolean success = DB.delSymp(illName, rmSymp);
				if (success) {
					JOptionPane.showMessageDialog(frame,
							"Symptom successfully removed");
					symptomList.remove(index);
				} else {
					JOptionPane.showMessageDialog(frame, "No such symptom");
				}
			}
		});

		JLabel lblSymptoms1 = new JLabel("Symptoms");
		lblSymptoms1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSymptoms1.setBounds(170, 140, 67, 14);
		editPanel.add(lblSymptoms1);
		searchPanel = new JPanel();
		// patientsPanel = new JPanel();
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
		resultScrlPn.setBounds(110, 98, 270, 198);
		searchPanel.add(resultScrlPn);

		final JTextArea searchResultsArea = new JTextArea();
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
		String[] searchOptions = { "Symptom", "Illness" };
		final JComboBox searchOptBox = new JComboBox(searchOptions);
		searchOptBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchOptBox.setEditable(false);
		searchOptBox.setBounds(110, 12, 127, 26);
		searchPanel.add(searchOptBox);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// determine if searching for symptom or illness
				Object option = searchOptBox.getSelectedItem();
				String search = searchField.getText();
				if (option.equals("Symptom")) {
					ArrayList<String> symptoms = DB.sympSearch(search);
					String results = "";
					if (symptoms.size() < 1) {
						searchResultsArea.setText("No Matching Search Results");
					} else {
						for (int i = 0; i < symptoms.size(); i++) {
							results += symptoms.get(i) + "\n";
						}
						searchResultsArea.setText(results);
					}
				}
				if (option.equals("Illness")) {
					ArrayList<String> illnesses = DB.illSearch(search);
					String results = "";
					if (illnesses.size() < 1) {
						searchResultsArea.setText("No Matching Search Results");
					} else {
						for (int i = 0; i < illnesses.size(); i++) {
							results += illnesses.get(i) + "\n";
						}
						searchResultsArea.setText(results);
					}
				}
			}
		});

		JLabel lblSearchOptions = new JLabel("Search for");
		lblSearchOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchOptions.setBounds(20, 11, 80, 28);
		searchPanel.add(lblSearchOptions);
		recordPanel.setLayout(null);

		// patientsPanel.setLayout(null);
		// JList patientList = new JList();
		// patientList.setBounds(10, 11, 130, 330);
		// patientList.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
		// null, null, null));
		// patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// patientsPanel.add(patientList);

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
			public void actionPerformed(final ActionEvent e) {
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
			public void actionPerformed(final ActionEvent e) {
				String symps = sympTxtArea.getText();
				ArrayList<String> symptoms = parseInput(symps);
				String name = nameField.getText();
				ArrayList<String> result = DB.addIll(name, symptoms);

				setIllList();
				if (result.size() < 1) {
					JOptionPane.showMessageDialog(frame,
							"Illness and symptoms successfully added");
				} else {
					if (result.size() == symptoms.size()) {
						JOptionPane.showMessageDialog(frame,
								"All symptoms already entered");
					} else {
						String reject = "";
						if (result.size() == 1) {
							reject += result.get(0) + " ";
						} else {
							for (int i = 0; i < result.size(); i++) {
								if (result.size() == 1) {
									reject += "and " + result.get(i) + " ";
								} else {
									reject += result.get(i) + ", ";
								}

							}
						}
						JOptionPane.showMessageDialog(frame, reject
								+ "already entered, other entries added");
					}
				}
			}

		});
		recordPanel.add(btnSave);

		JScrollPane sympScrlPn = new JScrollPane();
		sympScrlPn.setBounds(110, 52, 270, 245);
		recordPanel.add(sympScrlPn);

		sympScrlPn.setViewportView(sympTxtArea);
		recordPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { sympScrlPn, sympTxtArea, lblSymptoms,
						btnSave, clrBtn, lblName, nameField }));
	}
/**
 * 
 * @param in
 * @return result
 */
	public ArrayList<String> parseInput(String in) {
		ArrayList<String> result = new ArrayList<String>();
		in += ",\n";
		while (in.length() > 1) {
			try {
				String curr = "";
				if (in.indexOf(',') < in.indexOf('\n')) {
					curr = in.substring(0, (in.indexOf(','))).trim();
					if (curr.trim().equals("\n") || curr.trim().equals(",")
							|| curr.trim().equals("")) {
						// don't add it
					} else {
						result.add(curr.trim());
					}
					in = in.substring((in.indexOf(',') + 1));
				} else if (in.indexOf(',') > in.indexOf('\n')) {
					curr = in.substring(0, (in.indexOf('\n'))).trim();
					if (curr.trim().equals("\n") || curr.trim().equals(",")
							|| curr.trim().equals("")) {
						// don't add it
					} else {
						result.add(curr.trim());
					}
					in = in.substring((in.indexOf('\n') + 1));
				}
			} catch (StringIndexOutOfBoundsException e) {
				if (in.trim().length() > 1) {
					result.add(in.trim());
					break;
				}
			}

		}
		return result;
	}
/**
 * 
 */
	public void setIllList() {
		illnessList.clear();
		ArrayList<String> ills = DB.getIlls();
		for (int i = 0; i < ills.size(); i++) {
			illnessList.addElement(ills.get(i));
		}
	}
/**
 * 
 * @return
 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
