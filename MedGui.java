import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;

import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTree;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollBar;

/**
 * Main GUI class for the MediApp.
 * @author silas
 *
 */
public class MedGui {
	/**
	 * the main frame.
	 */
	public static JFrame frame;
	/**
	 * tabbed pane.
	 */
	private static JTabbedPane tabbedPane;
	/**
	 * illness list.
	 */
	private static DefaultListModel illnessList;
	/**
	 * symptom list.
	 */
	private DefaultComboBoxModel symptomList;
	/**
	 * database access.
	 */
	private static DBAccess DB;
	private JTextField textField;
	static JList userList;
	private JPanel profilePatPanel;
	private JPanel docProfilePanel;
	private JPanel messagePanel;
	private JPanel dbEditPanel;
	private JPanel userEditPanel;
	private static LoginDialog login;
	private JComboBox sympBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					login = new LoginDialog();
//					login.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\mediicon.jpg"));
//					login.setVisible(true);
					showFrame();
					
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DB = new DBAccess();
		
		DefaultListModel userlistModel = new DefaultListModel();
		//get users from database and add them to the list model.
		ArrayList<String> usernames = DB.getUsers();
		for(int i = 0; i < usernames.size(); i++)
		{
			userlistModel.addElement(usernames.get(i));
		}
		
		illnessList = new DefaultListModel();
		ArrayList<String> illNames = DB.getIlls();
		for(int i = 0; i < illNames.size(); i++)
		{
			illnessList.addElement(illNames.get(i));
		}
		
		symptomList = new DefaultComboBoxModel();
		ArrayList<String> sympNames = DB.getSymps();
		for(int i = 0; i < sympNames.size(); i++)
		{
			symptomList.addElement(sympNames.get(i));
		}
		
		//array list of all the symptoms
		ArrayList<String> symptoms = DB.getSymps();

		frame = new JFrame("MediApp");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\mediicon.jpg"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 440, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(2, 0, 482, 385);
		frame.getContentPane().add(tabbedPane);
		
		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search", null, searchPanel, null);
		tabbedPane.setEnabledAt(0, true);
		searchPanel.setLayout(null);
		
		JList illList = new JList();
		illList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		illList.setToolTipText("List of all illnesses in the database");
		illList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		illList.setBounds(10, 11, 100, 330);
		illList.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, null, null, null));
		searchPanel.add(illList);
		
		textField = new JTextField();
		textField.setToolTipText("search parameter");
		textField.setBounds(122, 43, 170, 20);
		searchPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\search_button_blue.png"));
		btnSearch.setBounds(304, 40, 100, 25);
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchPanel.add(btnSearch);
		
		JLabel lblSearchTheDatabase = new JLabel("Search the database of illnesses");
		lblSearchTheDatabase.setForeground(Color.WHITE);
		lblSearchTheDatabase.setBounds(122, 15, 244, 14);
		lblSearchTheDatabase.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		searchPanel.add(lblSearchTheDatabase);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, SystemColor.desktop));
		scrollPane.setBounds(122, 75, 270, 267);
		searchPanel.add(scrollPane);
		
		JTextArea txtrILikeTo = new JTextArea();
		txtrILikeTo.setToolTipText("search results");
		txtrILikeTo.setLineWrap(true);
		txtrILikeTo.setWrapStyleWord(true);
		scrollPane.setViewportView(txtrILikeTo);
		
		JLabel searchBackLbl = new JLabel("");
		searchBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		searchBackLbl.setBounds(0, 0, 432, 357);
		searchPanel.add(searchBackLbl);
		
		profilePatPanel = new JPanel();
		profilePatPanel.setLayout(null);
		
		JLabel patNameLbl = new JLabel("Patient Name");
		patNameLbl.setForeground(Color.WHITE);
		patNameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		patNameLbl.setBounds(10, 15, 200, 20);
		profilePatPanel.add(patNameLbl);
		
		JLabel heightLbl = new JLabel("Height: ");
		heightLbl.setForeground(Color.WHITE);
		heightLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		heightLbl.setBounds(10, 40, 150, 20);
		profilePatPanel.add(heightLbl);
		
		JLabel weightLbl = new JLabel("Weight: ");
		weightLbl.setForeground(Color.WHITE);
		weightLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		weightLbl.setBounds(10, 65, 200, 20);
		profilePatPanel.add(weightLbl);
		
		JLabel idLbl = new JLabel("ID: ");
		idLbl.setForeground(Color.WHITE);
		idLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		idLbl.setBounds(210, 15, 100, 20);
		profilePatPanel.add(idLbl);
		
		JLabel insurLbl = new JLabel("Insurance: ");
		insurLbl.setForeground(Color.WHITE);
		insurLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		insurLbl.setBounds(10, 90, 200, 20);
		profilePatPanel.add(insurLbl);
		
		JScrollPane docsPane = new JScrollPane();
		docsPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.textHighlight, null, null));
		docsPane.setBounds(10, 160, 100, 180);
		profilePatPanel.add(docsPane);
		
		JTextArea docsArea = new JTextArea();
		docsArea.setToolTipText("your doctors");
		docsPane.setViewportView(docsArea);
		
		JLabel docsLbl = new JLabel("Doctor(s):");
		docsLbl.setForeground(Color.WHITE);
		docsLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docsLbl.setBounds(10, 135, 80, 20);
		profilePatPanel.add(docsLbl);
		
		JScrollPane patSympsPane = new JScrollPane();
		patSympsPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.textHighlight, null, null));
		patSympsPane.setBounds(150, 160, 100, 180);
		profilePatPanel.add(patSympsPane);
		
		JTextArea patSympsArea = new JTextArea();
		patSympsArea.setToolTipText("your symptoms");
		patSympsPane.setViewportView(patSympsArea);
		
		JLabel patSympsLbl = new JLabel("Symptoms:");
		patSympsLbl.setForeground(Color.WHITE);
		patSympsLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		patSympsLbl.setBounds(150, 135, 80, 20);
		profilePatPanel.add(patSympsLbl);
		
		JLabel patProfBackLbl = new JLabel("");
		patProfBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		patProfBackLbl.setBounds(0, 0, 430, 357);
		profilePatPanel.add(patProfBackLbl);
		
		docProfilePanel = new JPanel();
		docProfilePanel.setLayout(null);
		
		JList patList = new JList();
		patList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patList.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 225), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(51, 153, 255), null, null, null)));
		patList.setBounds(10, 80, 125, 210);
		docProfilePanel.add(patList);
		
		JLabel docPatsLbl = new JLabel("Patients:");
		docPatsLbl.setForeground(Color.WHITE);
		docPatsLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docPatsLbl.setBounds(10, 55, 80, 20);
		docProfilePanel.add(docPatsLbl);
		
		JScrollPane patInfoPane = new JScrollPane();
		patInfoPane.setViewportBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.textHighlight, SystemColor.inactiveCaptionText));
		patInfoPane.setBounds(145, 80, 270, 210);
		docProfilePanel.add(patInfoPane);
		
		JTextArea patInfoArea = new JTextArea();
		patInfoPane.setViewportView(patInfoArea);
		patInfoArea.setWrapStyleWord(true);
		patInfoArea.setLineWrap(true);
		
		JLabel patInfoLbl = new JLabel("Information:");
		patInfoLbl.setForeground(Color.WHITE);
		patInfoLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		patInfoLbl.setBounds(145, 55, 80, 20);
		docProfilePanel.add(patInfoLbl);
		
		JButton btnRemoveSymptom = new JButton("Edit Symptoms");
		btnRemoveSymptom.setToolTipText("Add or remove symptoms from the selected patient");
		btnRemoveSymptom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnRemoveSymptom.setBounds(155, 295, 150, 25);
		docProfilePanel.add(btnRemoveSymptom);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setToolTipText("Add a patient from the list of available patients");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddPatient.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddPatient.setBounds(10, 295, 100, 25);
		docProfilePanel.add(btnAddPatient);
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.setToolTipText("Remove the selected patient");
		btnRemovePatient.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnRemovePatient.setBounds(10, 325, 125, 23);
		docProfilePanel.add(btnRemovePatient);
		
		JLabel docNameLbl = new JLabel("Doctor Name");
		docNameLbl.setForeground(Color.WHITE);
		docNameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docNameLbl.setBounds(10, 10, 225, 20);
		docProfilePanel.add(docNameLbl);
		
		JLabel docIDLbl = new JLabel("ID: ");
		docIDLbl.setForeground(Color.WHITE);
		docIDLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docIDLbl.setBounds(240, 10, 100, 20);
		docProfilePanel.add(docIDLbl);
		
		JLabel docSpecLbl = new JLabel("Specialization: ");
		docSpecLbl.setForeground(Color.WHITE);
		docSpecLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docSpecLbl.setBounds(10, 30, 300, 20);
		docProfilePanel.add(docSpecLbl);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		lblNewLabel_1.setBounds(0, 0, 429, 357);
		docProfilePanel.add(lblNewLabel_1);
		
		messagePanel = new JPanel();
		messagePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Messages from:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 100, 20);
		messagePanel.add(lblNewLabel);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setToolTipText("List of your messages");
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.textHighlight, SystemColor.textText));
		list.setBounds(10, 35, 125, 200);
		messagePanel.add(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.info, SystemColor.textText, null));
		scrollPane_1.setBounds(145, 35, 270, 199);
		messagePanel.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Message contents");
		scrollPane_1.setViewportView(textArea);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setToolTipText("Compose and send a new message");
		btnSendMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSendMessage.setBounds(10, 246, 150, 25);
		messagePanel.add(btnSendMessage);
		
		JButton btnDeleteMessage = new JButton("Delete Message");
		btnDeleteMessage.setToolTipText("Delete the selected message");
		btnDeleteMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDeleteMessage.setBounds(10, 280, 150, 25);
		messagePanel.add(btnDeleteMessage);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		lblNewLabel_2.setBounds(0, 0, 430, 357);
		messagePanel.add(lblNewLabel_2);
		
		dbEditPanel = new JPanel();
		dbEditPanel.setLayout(null);
		
		JScrollPane illnessPane = new JScrollPane();
		illnessPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, SystemColor.info, SystemColor.inactiveCaptionText, SystemColor.activeCaptionText));
		illnessPane.setBounds(12, 40, 125, 305);
		dbEditPanel.add(illnessPane);
		
		JList sympsList = new JList();
		sympsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JList illsList = new JList(illnessList);
		illsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		illsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				//update the symptoms list
				DefaultListModel sList = new DefaultListModel();
				String illness = (String) illsList.getSelectedValue();
				ArrayList<String> symptoms = DB.getIllSymps(illness);
				for(int i = 0; i < symptoms.size(); i++)
				{
					sList.addElement(symptoms.get(i));
				}
				sympsList.setModel(sList);
				frame.validate();
			}
		});
		illnessPane.setViewportView(illsList);
		illsList.setBorder(null);
		
		JScrollPane symptomsPane = new JScrollPane();
		symptomsPane.setViewportBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.textHighlight, SystemColor.activeCaptionText));
		symptomsPane.setBounds(145, 40, 120, 150);
		dbEditPanel.add(symptomsPane);
		
		symptomsPane.setViewportView(sympsList);
		sympsList.setBorder(null);
		
		JLabel lblIllnesses = new JLabel("Illnesses:");
		lblIllnesses.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblIllnesses.setForeground(Color.WHITE);
		lblIllnesses.setBounds(12, 15, 70, 20);
		dbEditPanel.add(lblIllnesses);
		
		JLabel lblSymptoms = new JLabel("Symptoms:");
		lblSymptoms.setForeground(Color.WHITE);
		lblSymptoms.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSymptoms.setBounds(145, 15, 80, 20);
		dbEditPanel.add(lblSymptoms);
		
		JButton addIllBtn = new JButton("Add New Illness");
		addIllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addIllnessDialog dia = new addIllnessDialog();
				dia.setVisible(true);
			}
		});
		addIllBtn.setToolTipText("Add a new illness to the database");
		addIllBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addIllBtn.setBounds(144, 200, 140, 25);
		dbEditPanel.add(addIllBtn);
		
		JButton delIllBtn = new JButton("Delete Illness");
		delIllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String illness = (String) illsList.getSelectedValue();
				boolean deleted = DB.delIll(illness);
				if(deleted)
				{
					JOptionPane.showMessageDialog(frame,
						    "Illness successfully deleted.",
						    "Deleted",
						    JOptionPane.PLAIN_MESSAGE);
					updateIllList();
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Symptom could not be deleted.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		delIllBtn.setToolTipText("Remove an illness from the database");
		delIllBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		delIllBtn.setBounds(145, 235, 140, 25);
		dbEditPanel.add(delIllBtn);
		
		JButton addSympBtn = new JButton("Add Symptom");
		addSympBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String symptom = (String) sympBox.getSelectedItem();
				String illness = (String) illsList.getSelectedValue();
				ArrayList<String> s = new ArrayList<String>();
				s.add(symptom);
				ArrayList<String> rej = DB.addIll(illness, s);
				if(rej.size() > 0)
				{
					JOptionPane.showMessageDialog(frame,
						    "Symptom could not be added.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Symptom successfully added.",
						    "Entry Made",
						    JOptionPane.PLAIN_MESSAGE);
					//update symptoms list
					DefaultListModel syList = new DefaultListModel();
					ArrayList<String> symptoms = DB.getIllSymps(illness);
					for(int i = 0; i < symptoms.size(); i++)
					{
						syList.addElement(symptoms.get(i));
					}
					sympsList.setModel(syList);
					frame.validate();
				}
			}
		});
		addSympBtn.setToolTipText("Add the selected symptom from the box to the selected illness");
		addSympBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addSympBtn.setBounds(280, 75, 140, 25);
		dbEditPanel.add(addSympBtn);
		
		JButton delSympBtn = new JButton("Delete Symptom");
		delSympBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//illsList, sympsList
				String illness = (String) illsList.getSelectedValue();
				String symptom = (String) sympsList.getSelectedValue();
				boolean deleted = DB.delIllSymp(illness, symptom);
				if(deleted)
				{
					JOptionPane.showMessageDialog(frame,
						    "Symptom successfully deleted.",
						    "Deleted",
						    JOptionPane.PLAIN_MESSAGE);
					DefaultListModel symList = new DefaultListModel();
					ArrayList<String> symptoms = DB.getIllSymps(illness);
					for(int i = 0; i < symptoms.size(); i++)
					{
						symList.addElement(symptoms.get(i));
					}
					sympsList.setModel(symList);
					frame.validate();
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Symptom could not be deleted.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		delSympBtn.setToolTipText("Delete the selected symptom from the selected illness");
		delSympBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		delSympBtn.setBounds(280, 110, 140, 25);
		dbEditPanel.add(delSympBtn);
		
		JButton newSympBtn = new JButton("New Symptom");
		newSympBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newSymp = (String)JOptionPane.showInputDialog("Enter new Symptom: ");
				try
				{
					if(!newSymp.equals(""))
					{
						boolean successful = DB.newSymptom(newSymp);
						if(successful)
						{
							JOptionPane.showMessageDialog(frame,
								    "Symptom succesfully entered.",
								    "Entered",
								    JOptionPane.PLAIN_MESSAGE);
							updateSympList();
//							ArrayList<String> sympts = DB.getSymps();
//							for(int i = 0; i < symptoms.size(); i++)
//							{
//								sympBox.addItem(sympts.get(i));	
//							}
//							frame.validate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame,
								    "Symptom could not be added.",
								    "Error",
								    JOptionPane.PLAIN_MESSAGE);
						}
					}
				}catch(NullPointerException np)
				{
					
				}
			}
		});
		newSympBtn.setToolTipText("Add a symptom to the list of available symptoms");
		newSympBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		newSympBtn.setBounds(280, 145, 140, 25);
		dbEditPanel.add(newSympBtn);
		
		//get all symptoms from db to populate sympBox
		sympBox = new JComboBox();
		sympBox.setModel(symptomList);
		sympBox.setToolTipText("list of all available symptoms");
		sympBox.setBounds(280, 40, 140, 25);
		dbEditPanel.add(sympBox);
		
		JLabel dataBackLbl = new JLabel("");
		dataBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		dataBackLbl.setBounds(0, 0, 428, 357);
		dbEditPanel.add(dataBackLbl);
		
		userEditPanel = new JPanel();
		userEditPanel.setLayout(null);
		
		JTextArea userInfoArea = new JTextArea();
		userInfoArea.setWrapStyleWord(true);
		userInfoArea.setLineWrap(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 40, 125, 235);
		userEditPanel.add(scrollPane_2);
		
		userList = new JList(userlistModel);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(userList);
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String selUser = (String) userList.getSelectedValue();//the user's username
				//retrieve the selected user's info from the db
				//determine user type
				String type = DB.getType(selUser);
				if(type.equals("A"))
				{
					type = "Admin";
					//if user is an admin get the admin info(type, id, fname, lname, pass)
					int id = DB.getID(selUser);
					String name = DB.getName(id, type);
					String password = DB.getPass(id);
					userInfoArea.setText("User Type: " + type + "\n Username: " + selUser + "\n Password: " + password + "\n ID: " + id + " \n Name: " + name);
				}
				else if(type.equals("D"))
				{
					//if user is a doctor get doctor info(type, id, fname, lname, pass, spec, patients)
					type = "Doctor";
					int id = DB.getID(selUser);
					String name = DB.getName(id, type);
					String password = DB.getPass(id);
					String spec = DB.getSpec(id);
					ArrayList<String> patients = DB.getPatients(id);
					String pNames = "";
					for(int i = 0; i < patients.size(); i++)
					{
						if(i < patients.size() - 1 && patients.size() > 1)
						{
							pNames += patients.get(i) + ", ";
						}
						else
						{
							pNames += patients.get(i);
						}
					}
					userInfoArea.setText("User Type: " + type + "\n Username: " + selUser + "\n Password: " + password + "\n ID: " + id + " \n Name: " + name 
							+ "\n Specialization: " + spec + "\n Patients: " + pNames);
				}
				else if(type.equals("P"))
				{
					//if user is a patient get patient info(type, id, fname, lname, height, weight, insur, symptoms, doctors)
					type = "Patient";
					int id = DB.getID(selUser);
					String name = DB.getName(id, type);
					String password = DB.getPass(id);
					String height = Integer.toString(DB.getHeight(id));
					try
					{
						if(height.equals("") || height.equals(null))
						{
							height = "N/A";
						}
					}catch(NullPointerException e)
					{
						height = "N/A";
					}
					String weight = Integer.toString(DB.getWeight(id));
					try
					{
						if(weight.equals("") || weight.equals(null))
						{
							weight = "N/A";
						}
					}catch(NullPointerException e)
					{
						weight = "N/A";
					}
					String insur = DB.getInsurance(id);
					try
					{
						if(insur.equals("") || insur.equals(null))
						{
							insur = "N/A";
						}
					}catch(NullPointerException e)
					{
						insur = "N/A";
					}
					userInfoArea.setText("User Type: " + type + "\n Username: " + selUser + "\n Password: " + password + "\n ID: " + id + " \n Name: " + name
							+ "\n Height: " + height + " In. \n Weight: " + weight + " lbs. \n Insurance: " + insur);
				}
				
			}
		});
		userList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, SystemColor.inactiveCaptionText));
		
		JLabel lblNewLabel_3 = new JLabel("Users:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 15, 70, 16);
		userEditPanel.add(lblNewLabel_3);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDialog dia = new UserDialog();
				dia.setVisible(true);
				DefaultListModel newList = new DefaultListModel();
				ArrayList<String> usernames = DB.getUsers();
				for(int i = 0; i < usernames.size(); i++)
				{
					newList.addElement(usernames.get(i));
				}
				userList.setModel(newList);
				System.out.println(userList.getModel());
				frame.validate();
			}
		});
		btnAddUser.setToolTipText("Add a new user");
		btnAddUser.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddUser.setBounds(10, 280, 125, 25);
		userEditPanel.add(btnAddUser);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selUser = (String)userList.getSelectedValue();
				DB.delUser(selUser);
				updateUserList();
				userInfoArea.setText("");
			}
		});
		btnDeleteUser.setToolTipText("Delete the selected user");
		btnDeleteUser.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDeleteUser.setBounds(10, 310, 125, 25);
		userEditPanel.add(btnDeleteUser);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = (String) userList.getSelectedValue();
				try
				{
					if(user.equals(null))
					{
						userInfoArea.setText("No selected user, to edit a user please select a username from the list.");
					}
					else
					{
						//get the selected user's info
						String type = DB.getType(user);
						
						int id = DB.getID(user);
						String name = DB.getName(id, type);
						String fName = name.substring(0, name.indexOf(" "));
						String lName = name.substring(name.indexOf(" ") + 1, name.length());
						String password = DB.getPass(id);
						String specialization = "";
						int height = 0;
						int weight = 0;
						String insurance = "";
						if(type.equals("D"))
						{
							//get spec for docs
							specialization = DB.getSpec(id);
						}
						else if(type.equals("P"))
						{
							//height, weight, insurance for patients
							height = DB.getHeight(id);
							weight = DB.getWeight(id);
							insurance = DB.getInsurance(id);
						}
						
						UserDialog dia = new UserDialog(type, id, fName, lName, height, weight, insurance, specialization, user, password);
						dia.setVisible(true);
					}
				}catch(NullPointerException err)
				{
					userInfoArea.setText("No selected user, to edit a user please select a username from the list.");
				}
				
			}
		});
		btnEdit.setToolTipText("Edit the selected user's info");
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnEdit.setBounds(145, 280, 75, 25);
		userEditPanel.add(btnEdit);
		
		JLabel lblUserInfo = new JLabel("User info:");
		lblUserInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUserInfo.setForeground(Color.WHITE);
		lblUserInfo.setBounds(145, 15, 70, 20);
		userEditPanel.add(lblUserInfo);
		
		JScrollPane userInfoPane = new JScrollPane();
		userInfoPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(51, 153, 255), new Color(153, 180, 209), new Color(0, 0, 0), new Color(180, 180, 180)));
		userInfoPane.setBounds(145, 40, 200, 235);
		userEditPanel.add(userInfoPane);
		
		userInfoPane.setViewportView(userInfoArea);
		userInfoArea.setToolTipText("User info\r\n");
		
		JLabel userBackLbl = new JLabel("");
		userBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Dropbox\\Silas\\Java\\MediApp2\\Medical Symbol.jpg"));
		userBackLbl.setBounds(0, 0, 429, 357);
		userEditPanel.add(userBackLbl);
		
		//add the user specific tabs top the frame
//		if(login.getUserType().equals("A"))
//		{
//			tabbedPane.addTab("Database", null, this.dbEditPanel, null);
//			tabbedPane.addTab("Users", null, this.userEditPanel, null);
//		}
//		else if(login.getUserType().equals("D"))
//		{
//			tabbedPane.addTab("Messaging", null, this.messagePanel, null);
//			tabbedPane.addTab("Profile", null, this.docProfilePanel, null);
//		}
//		else if(login.getUserType().equals("P"))
//		{
//			tabbedPane.addTab("Profile", null, this.profilePatPanel, null);
//			tabbedPane.addTab("Messaging", null, this.messagePanel, null);
//		}
		tabbedPane.addTab("Database", null, this.dbEditPanel, null);
		tabbedPane.addTab("Users", null, this.userEditPanel, null);
		tabbedPane.addTab("Messaging", null, this.messagePanel, null);
		tabbedPane.addTab("Profile", null, this.docProfilePanel, null);
		tabbedPane.addTab("Profile", null, this.profilePatPanel, null);
		tabbedPane.addTab("Messaging", null, this.messagePanel, null);
		frame.setVisible(false);
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
	public static void updateIllList() {
		illnessList.clear();
		ArrayList<String> ills = DB.getIlls();
		for (int i = 0; i < ills.size(); i++) {
			illnessList.addElement(ills.get(i));
		}
		frame.validate();
	}
	
	public void updateSympList()
	{
		symptomList.removeAllElements();
		ArrayList<String> sympNames = DB.getSymps();
		for(int i = 0; i < sympNames.size(); i++)
		{
			symptomList.addElement(sympNames.get(i));
		}
		frame.validate();
	}
	
	public static void updateUserList()
	{
		DefaultListModel newList = new DefaultListModel();
		ArrayList<String> usernames = DB.getUsers();
		for(int i = 0; i < usernames.size(); i++)
		{
			newList.addElement(usernames.get(i));
		}
		userList.setModel(newList);
		frame.validate();
	}
/**
 * 
 * @return
 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public void updateFrame()
	{
		frame.repaint();
		frame.validate();
	}
}
