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
	static JList userList;
	private JPanel profilePatPanel;
	private JPanel docProfilePanel;
	private JPanel messagePanel;
	private JPanel dbEditPanel;
	private JPanel userEditPanel;
	private static LoginDialog login;
	private JComboBox sympBox;
	private Doctor doc;
	private Patient pat;
	private Admin ad;
	private JTextArea resultArea;
	private JComboBox searchSympBox;
	private JList patList;
	private JTextArea patInfoArea;
	private JList messageList;
	DefaultListModel messageModel;
	private JTextArea messageArea;
	private int userID;
	private String sendMessageTo;
	private String userType;
	private String userName;
	private DefaultListModel allMedsModel;
	private DefaultListModel patMedsModel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login = new LoginDialog();
					login.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\mediicon.jpg"));//comment this out
					//login.setIconImage(Toolkit.getDefaultToolkit().getImage(""));//your path to mediicon
					login.setVisible(true);
					//showFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MedGui(String type, String userN) {
		userType = type;
		userName = userN;
		initialize();
	}

	/**
	 * Show Frame.
	 */
	public static void showFrame() {
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
		symptomList.addElement("(Clear Search)");
		for(int i = 0; i < sympNames.size(); i++)
		{
			symptomList.addElement(sympNames.get(i));
		}
		
		//get list of all medications
		allMedsModel = new DefaultListModel();
		ArrayList<String> meds = DB.getAllMeds();
		for(int i = 0; i < meds.size(); i++)
		{
			allMedsModel.addElement(meds.get(i));
		}
		
		//array list of all the symptoms
		ArrayList<String> symptoms = DB.getSymps();

		frame = new JFrame("MediApp");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\mediicon.jpg"));//comment this out
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("")); //your path to mediicon
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
		
		JScrollPane illListPane = new JScrollPane();
		illListPane.setBounds(10, 11, 130, 330);
		searchPanel.add(illListPane);
		
		JList illList = new JList();
		illList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String selIll = (String) illList.getSelectedValue();
				ArrayList<String> assoSymp = DB.illSearch(selIll);
				resultArea.setText("Name: " + selIll + "\n" + "Symptoms: ");
				for(int i = 0; i < assoSymp.size(); i++)
				{
					resultArea.append("\n" + assoSymp.get(i));
				}
			}
		});
		illList.setModel(illnessList);
		illListPane.setViewportView(illList);
		illList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		illList.setToolTipText("List of all illnesses in the database");
		illList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		illList.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, null, null, null));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selSymp = (String) searchSympBox.getSelectedItem();
				if(selSymp.equals("(Clear Search)"))
				{
					illList.setModel(illnessList);
				}
				else
				{
					ArrayList<String> assoIlls = DB.sympSearch(selSymp);
					DefaultListModel illMod = new DefaultListModel();
					for(int i = 0; i < assoIlls.size(); i++)
					{
						illMod.addElement(assoIlls.get(i));
					}
					illList.setModel(illMod);	
				}
			}
		});
		btnSearch.setToolTipText("Search for illnesses with selected symptom");
		btnSearch.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\search_button_blue.png"));//comment this out
		//btnSearch.setIcon(new ImageIcon(""));//your path to the search_button_blue
		btnSearch.setBounds(320, 40, 100, 25);
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchPanel.add(btnSearch);
		
		JLabel lblSearchTheDatabase = new JLabel("Search the database of illnesses");
		lblSearchTheDatabase.setForeground(Color.WHITE);
		lblSearchTheDatabase.setBounds(150, 15, 244, 14);
		lblSearchTheDatabase.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		searchPanel.add(lblSearchTheDatabase);
		
		searchSympBox = new JComboBox();
		searchSympBox.setModel(symptomList);
		searchSympBox.setToolTipText("Select a symptom to search for");
		searchSympBox.setBounds(150, 40, 160, 25);
		searchPanel.add(searchSympBox);
		
		JScrollPane resultPane = new JScrollPane();
		resultPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.textHighlight, SystemColor.desktop));
		resultPane.setBounds(150, 75, 260, 267);
		searchPanel.add(resultPane);
		
		resultArea = new JTextArea();
		resultArea.setToolTipText("illness info");
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		resultPane.setViewportView(resultArea);
		
		JLabel searchBackLbl = new JLabel("");
		searchBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));//comment this out
		//searchBackLbl.setIcon(new ImageIcon(""));//your path to Medical Symbol
		searchBackLbl.setBounds(0, 0, 432, 357);
		searchPanel.add(searchBackLbl);
		
		docProfilePanel = new JPanel();
		docProfilePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 125, 210);
		docProfilePanel.add(scrollPane);

		patList = new JList();
		patList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String name = (String) patList.getSelectedValue();
				//get the patients info and display it on the patInfoArea
				try
				{
					String fName = name.substring(0, name.indexOf(" "));
					String lName = name.substring(name.indexOf(" "), name.length()).trim();
					int pID = DB.getID(fName, lName);
					patInfoArea.setText("");
					patInfoArea.append("ID: " + pID + "\n");
					patInfoArea.append("Name: " + name + "\n");
					patInfoArea.append(DB.getPatientInfo(pID));
					patInfoArea.append("Medications: ");
					ArrayList<String> patMeds = DB.getPatMeds(pID);
					for(int i = 0; i < patMeds.size(); i++)
					{
						if(i < patMeds.size() - 1)
						{
							patInfoArea.append(patMeds.get(i) + ", ");
						}
						else
						{
							patInfoArea.append(patMeds.get(i));
						}
					}
				}catch(NullPointerException e)
				{
					patInfoArea.setText("No Patient Selected");
				}
			}
		});
		scrollPane.setViewportView(patList);
		patList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patList.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 225), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(51, 153, 255), null, null, null)));
		
		JLabel docPatsLbl = new JLabel("Patients:");
		docPatsLbl.setForeground(Color.WHITE);
		docPatsLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		docPatsLbl.setBounds(10, 55, 80, 20);
		docProfilePanel.add(docPatsLbl);
		
		JScrollPane patInfoPane = new JScrollPane();
		patInfoPane.setViewportBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.textHighlight, SystemColor.inactiveCaptionText));
		patInfoPane.setBounds(145, 80, 270, 210);
		docProfilePanel.add(patInfoPane);
		
		patInfoArea = new JTextArea();
		patInfoPane.setViewportView(patInfoArea);
		patInfoArea.setWrapStyleWord(true);
		patInfoArea.setLineWrap(true);
		
		JLabel patInfoLbl = new JLabel("Information:");
		patInfoLbl.setForeground(Color.WHITE);
		patInfoLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		patInfoLbl.setBounds(145, 55, 80, 20);
		docProfilePanel.add(patInfoLbl);
		
		JButton btnEditSymptom = new JButton("Edit Symptoms");
		btnEditSymptom.setToolTipText("Add or remove symptoms from the selected patient");
		btnEditSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String selPat = (String) patList.getSelectedValue();
					EditSymptomsDialog dia = new EditSymptomsDialog(DB.getID(selPat.substring(0, selPat.indexOf(" ")), selPat.substring(selPat.indexOf(" ") + 1, selPat.length())));
				}catch(NullPointerException e)
				{
					patInfoArea.setText("No Patient Selected");
				}
			}
		});
		btnEditSymptom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEditSymptom.setBounds(155, 295, 160, 25);
		docProfilePanel.add(btnEditSymptom);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.setToolTipText("Add a patient from the list of available patients");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Patient> patients = DB.getPatientsLess(doc.getdID());
				String[] pats = new String[patients.size()];
				for(int i = 0; i < patients.size(); i++)
				{
					pats[i] = patients.get(i).getfName() + " " + patients.get(i).getlName();
				}
				String selPat = (String)JOptionPane.showInputDialog(null, "Select a patient", "Add Patient", JOptionPane.QUESTION_MESSAGE, null, pats, pats[0]);
					int dID = doc.getdID();
					int pID = 0;
				try
				{
					pID = DB.getID(selPat.substring(0, selPat.indexOf(" ")), selPat.substring(selPat.indexOf(" ") + 1, selPat.length()));
				}catch(NullPointerException err)
				{
					
				}
				boolean add = DB.addPatient(dID, pID);
				if(add)
				{
					ArrayList<Patient> allPats = DB.getPatients(dID);
					DefaultListModel pattsMod = new DefaultListModel();
					for(int i = 0; i < allPats.size(); i++)
					{
						pattsMod.addElement(allPats.get(i).getfName() + " " + allPats.get(i).getlName());
					}
					patList.setModel(pattsMod);
				}	
			}
		});
		btnAddPatient.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddPatient.setBounds(10, 295, 125, 25);
		docProfilePanel.add(btnAddPatient);
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.setToolTipText("Remove the selected patient");
		btnRemovePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selPat = (String)patList.getSelectedValue();
				int dId = doc.getdID();
				int pId = DB.getID(selPat.substring(0, selPat.indexOf(" ")), selPat.substring(selPat.indexOf(" ") + 1, selPat.length()));
				boolean remove = DB.removePatient(dId, pId);
				if(remove)
				{
					//update patList
					ArrayList<Patient> patients = DB.getPatients(dId);
					DefaultListModel patientsMod = new DefaultListModel();
					for(int i = 0; i < patients.size(); i++)
					{
						patientsMod.addElement(patients.get(i).getfName() + " " + patients.get(i).getlName());
					}
					patList.setModel(patientsMod);
				}
			}
		});
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
		
		JButton btnPrescribeMedication = new JButton("Prescribe Medication");
		btnPrescribeMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//prescribe medication
				String selPat = (String)patList.getSelectedValue();
				int patientID = DB.getID(selPat.substring(0, selPat.indexOf(" ")), selPat.substring(selPat.indexOf(" ") + 1, selPat.length()));
				ArrayList<String> alreadyPrescribed = DB.getPatMeds(patientID);
				ArrayList<String> medications = DB.getAllMeds();
				//remove from medications any meds that are already prescribed to the selected patient
				for(int i = 0; i < medications.size(); i++)
				{
					for(int j = 0; j < alreadyPrescribed.size(); j++)
					{
						if(medications.get(i).equals(alreadyPrescribed.get(j)))
						{
							medications.remove(i);
						}
					}
				}
				String[] medis = new String[medications.size()];
				for(int i = 0; i < medis.length; i++)
				{
					medis[i] = medications.get(i);
				}
				String prescribe = (String)JOptionPane.showInputDialog(null, "Select a medication", "Prescribe", JOptionPane.QUESTION_MESSAGE, null, medis, medis[0]);
				boolean prescribed = DB.prescribeMed(prescribe, patientID);
				if(prescribed)
				{
					ArrayList<String> newMeds = DB.getPatMeds(patientID);
					patInfoArea.setText("");
					patInfoArea.append("ID: " + patientID + "\n");
					patInfoArea.append("Name: " + selPat + "\n");
					patInfoArea.append(DB.getPatientInfo(patientID));
					patInfoArea.append("Medications: ");
					for(int i = 0; i < newMeds.size(); i++)
					{
						if(i < newMeds.size() - 1)
						{
							patInfoArea.append(newMeds.get(i) + ", ");
						}
						else
						{
							patInfoArea.append(newMeds.get(i));
						}
					}
				}
				else
				{
					System.out.println("failure");
				}
			}
		});
		btnPrescribeMedication.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnPrescribeMedication.setBounds(155, 323, 160, 25);
		docProfilePanel.add(btnPrescribeMedication);
		
		JLabel patientBackLbl = new JLabel("");
		patientBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));
		patientBackLbl.setBounds(0, 0, 429, 357);
		docProfilePanel.add(patientBackLbl);
		
		messagePanel = new JPanel();
		messagePanel.setLayout(null);
		
		JLabel messageLbl = new JLabel("Messages from:");
		messageLbl.setForeground(Color.WHITE);
		messageLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		messageLbl.setBounds(10, 10, 100, 20);
		messagePanel.add(messageLbl);
		
		JScrollPane messagePane = new JScrollPane();
		messagePane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.info, SystemColor.textText, null));
		messagePane.setBounds(145, 35, 270, 220);
		messagePanel.add(messagePane);
		
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		messageArea.setWrapStyleWord(true);
		messageArea.setLineWrap(true);
		messageArea.setToolTipText("Message contents");
		messagePane.setViewportView(messageArea);
		
		JButton sendBtn = new JButton("Send");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get text from the area and send it as a message to the predetermined patient
				String message = messageArea.getText();
				int from = userID;
				String to = sendMessageTo;
				boolean sent = DB.sendMessage(from, to, message, userType);
				if(sent)
				{
					messageArea.setText("Message sent");
					messageArea.setEditable(false);
					sendBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Message was not sent.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		sendBtn.setEnabled(false);
		sendBtn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		sendBtn.setBounds(340, 265, 75, 25);
		
		JButton btnSendMessage = new JButton("New Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//popup dialog to pick who to send the message to
				//clear the text area and make it editable
				if(userType.equals("D"))
				{
					ArrayList<Patient> patients = DB.getPatients(userID);
					String[] pats = new String[patients.size()];
					for(int i = 0; i < patients.size(); i++)
					{
						pats[i] = patients.get(i).getfName() + " " + patients.get(i).getlName();
					}
					sendMessageTo = (String)JOptionPane.showInputDialog(null, "Select a patient", "Add Patient", JOptionPane.QUESTION_MESSAGE, null, pats, pats[0]);
					try
					{
						if(!sendMessageTo.equals(null))
						{
							messageArea.setText("");
							sendBtn.setEnabled(true);
							messageArea.setEditable(true);
						}
					}
					catch(NullPointerException e)
					{
						
					}
				}
				else
				{
					ArrayList<String> doctor = DB.getPatDocs(userID);
					String[] docs = new String[doctor.size()];
					for(int i = 0; i < doctor.size(); i++)
					{
						docs[i] = doctor.get(i);
					}
					sendMessageTo = (String)JOptionPane.showInputDialog(null, "Select a patient", "Add Patient", JOptionPane.QUESTION_MESSAGE, null, docs, docs[0]);
					try
					{
						if(!sendMessageTo.equals(null))
						{
							messageArea.setText("");
							sendBtn.setEnabled(true);
							messageArea.setEditable(true);
						}
					}
					catch(NullPointerException e)
					{
						
					}
				}
			}
		});
		btnSendMessage.setToolTipText("Compose and send a new message");
		btnSendMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSendMessage.setBounds(10, 265, 150, 25);
		messagePanel.add(btnSendMessage);
		
		messageList = new JList();
		messageList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				//display message from the selected person
				String selMes = (String) messageList.getSelectedValue();
				messageArea.setEditable(false);
				sendBtn.setEnabled(false);
				try
				{
					String text = "";
					if(userType.equals("P"))
					{
						text = DB.getText(selMes, userType, pat.getpID());
					}
					else if(userType.equals("D"))
					{
						text = DB.getText(selMes, userType, doc.getdID());
					}
					messageArea.setText(text);
				}catch(NullPointerException e)
				{
					messageArea.setText("No message selected.");
				}
			}
		});
		messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		messageList.setToolTipText("List of your messages");
		messageList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		messageList.setBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.textHighlight, SystemColor.textText));
		messageList.setBounds(10, 35, 125, 220);
		messagePanel.add(messageList);
		
		JButton btnDeleteMessage = new JButton("Delete Message");
		btnDeleteMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//delete the selected message and update message list
				String selMes = (String) messageList.getSelectedValue();
				boolean deleted = DB.delMessage(selMes, userType, userID);
				if(deleted)
				{
					updateMessages(userID , userType);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Message could not be deleted.",
						    "Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnDeleteMessage.setToolTipText("Delete the selected message");
		btnDeleteMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDeleteMessage.setBounds(10, 300, 150, 25);
		messagePanel.add(btnDeleteMessage);
		messagePanel.add(sendBtn);
		
		JLabel messageBackLbl = new JLabel("");
		messageBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));
		messageBackLbl.setBounds(0, 0, 430, 357);
		messagePanel.add(messageBackLbl);
		
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
		symptomsPane.setBounds(145, 40, 127, 150);
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
					updateIllnessList();
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
		dataBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));
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
					ArrayList<Patient> patients = DB.getPatients(id);
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
		userBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));
		userBackLbl.setBounds(0, 0, 429, 357);
		userEditPanel.add(userBackLbl);
	
		profilePatPanel = new JPanel();
		profilePatPanel.setEnabled(true);
		profilePatPanel.setLayout(null);
		
		JLabel patNameLbl = new JLabel();
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
		docsArea.setEditable(false);
		
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
		patSympsArea.setEditable(false);
		
		JLabel patSympsLbl = new JLabel("Symptoms:");
		patSympsLbl.setForeground(Color.WHITE);
		patSympsLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		patSympsLbl.setBounds(150, 135, 80, 20);
		profilePatPanel.add(patSympsLbl);
		
		JLabel lblMedications = new JLabel("Medications:");
		lblMedications.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMedications.setForeground(Color.WHITE);
		lblMedications.setBounds(290, 137, 85, 16);
		profilePatPanel.add(lblMedications);
		
		JScrollPane medsPane = new JScrollPane();
		medsPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.textHighlight, SystemColor.inactiveCaptionText, null, null));
		medsPane.setBounds(290, 160, 100, 180);
		profilePatPanel.add(medsPane);
		
		JTextArea medsArea = new JTextArea();
		medsPane.setViewportView(medsArea);
		
		JLabel patProfBackLbl = new JLabel("");
		patProfBackLbl.setIcon(new ImageIcon("C:\\Users\\silas\\Desktop\\Java\\MediApp2\\Medical Symbol.jpg"));
		patProfBackLbl.setBounds(0, 0, 430, 357);
		profilePatPanel.add(patProfBackLbl);
	
//		tabbedPane.addTab("Database", null, this.dbEditPanel, null);
//		tabbedPane.addTab("Users", null, this.userEditPanel, null);
//		tabbedPane.addTab("Messaging", null, this.messagePanel, null);
//		tabbedPane.addTab("Profile", null, this.profilePatPanel, null);
//		tabbedPane.addTab("Patients", null, this.docProfilePanel, null);
//		tabbedPane.addTab("Messaging", null, this.messagePanel, null);
		
		//add the user specific tabs to the frame
		if(userType.equals("A"))
		{
			userID = DB.getID(userName);
			String name = DB.getName(userID, "A");
			String fName = name.substring(0, name.indexOf(" "));
			String lName = name.substring(name.indexOf(" "), name.length() - 1);
			ad = new Admin(userID, fName, lName);
			tabbedPane.addTab("Database", null, this.dbEditPanel, null);
			tabbedPane.addTab("Users", null, this.userEditPanel, null);
		}
		else if(userType.equals("D"))
		{
			doc = DB.getDocOb(userName);
			userID = doc.getdID();
			tabbedPane.addTab("Messaging", null, this.messagePanel, null);
			tabbedPane.addTab("Patients", null, this.docProfilePanel, null);
			docNameLbl.setText(doc.getdfName() + " " + doc.getdlName());
			docIDLbl.setText("ID: " + doc.getdID());
			docSpecLbl.setText("Specialization: " + doc.getSpecialization());
			
			doc.setPatients(DB.getPatients(doc.getdID()));
			ArrayList<Patient> patients = doc.getPatients();
			DefaultListModel patientsMod = new DefaultListModel();
			for(int i = 0; i < patients.size(); i++)
			{
				patientsMod.addElement(patients.get(i).getfName() + " " + patients.get(i).getlName());
			}
			patList.setModel(patientsMod);
			
			updateMessages(doc.getdID(), "D");
		}
		else if(userType.equals("P"))
		{
			pat = DB.getPatOb(userName);
			userID = pat.getpID();
			tabbedPane.addTab("Profile", null, this.profilePatPanel, null);
			tabbedPane.addTab("Messaging", null, this.messagePanel, null);
			
			patNameLbl.setText(pat.getfName() + " " + pat.getlName());
			heightLbl.setText("Height: " + pat.getHeight() + "''");
			weightLbl.setText("Weight: " + pat.getWeight() + " lbs.");
			insurLbl.setText("Insurance: " + pat.getInsurance());
			idLbl.setText("ID: " + pat.getpID());
			
			//doctors area
			ArrayList<String> patSymps = DB.getPatSymps(userID);
			for(int i = 0; i < patSymps.size(); i++)
			{
				patSympsArea.append(patSymps.get(i) + "\n");
			}
			ArrayList<String> docs = DB.getPatDocs(userID);
			for(int i = 0; i <docs.size(); i++)
			{
				docsArea.append(docs.get(i) + "\n");
			}
			//get medications and add them to the medsArea
			ArrayList<String> patMeds = DB.getPatMeds(pat.getpID());
			medsArea.setText("");
			for(int i = 0; i < patMeds.size(); i++)
			{
				medsArea.append(patMeds.get(i) + "\n");
			}
			
			updateMessages(userID, "P");
		}
		
		frame.setVisible(false);
	}

	/**
	 * updates the list of illnesses
	 */
	public static void updateIllnessList() {
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
	
	public void updateMessages(int id, String type)
	{
		ArrayList<String> messages = DB.getMessages(id, type);
		messageModel = new DefaultListModel();
		for(int i = 0; i < messages.size(); i++)
		{
			messageModel.addElement(messages.get(i));
		}
		messageList.setModel(messageModel);
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
}
