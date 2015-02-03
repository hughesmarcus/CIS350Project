import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MedGui {

	private JFrame frame;
	private JTextField textField;
	private JTabbedPane tabbedPane;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedGui window = new MedGui();
					window.frame.setVisible(true);
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("MediApp");
		frame.setBounds(100, 100, 399, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(2, 0, 375, 342);
		frame.getContentPane().add(tabbedPane);
		
		JTextArea textArea_1 = new JTextArea();
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Record", null, panel, null);
		//tabbedPane.addTab("Search", null, panel_1, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(45, 13, 47, 20);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(110, 8, 228, 33);
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Symptoms:");
		label_1.setBounds(12, 53, 81, 20);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(label_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(271, 10, 0, 16);
		panel.add(textArea);
		
		JButton clrBtn = new JButton("Clear");
		clrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					textArea_1.setText("");
					textField.setText("");
			}
		});
		clrBtn.setBounds(224, 261, 72, 33);
		panel.add(clrBtn);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(129, 261, 72, 33);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//read symptoms into an array
				String symps = textArea_1.getText();
				symps += ",";
				ArrayList<String> symptoms = new ArrayList<String>();
				for(int i = 0; i < symps.length(); i++)
				{
					//symptoms are seperated either by a comma or a return
					if(symps.charAt(i) == '\n' || symps.charAt(i) == ',')
					{
						//when there is a comma then a return
						if(symps.length() > 1 && i > symps.length())
						{
							if(symps.charAt(i+1) == '\n' && symps.charAt(i) == ',')
							{
								//read what is from the beginning of the string to the comma
								String next = symps.substring(0, i).trim();
								symptoms.add(next);
								symps = symps.substring(i + 2).trim();
								i = 0;
							}
						}
						else
						{
							//read what is from the beginning of the string to the return or comma.
							String next = symps.substring(0, i).trim();
							symptoms.add(next);
							symps = symps.substring(i + 1).trim();
							i = 0;
						}
						
					}
				}
				for(int f = 0; f < symptoms.size(); f++)
				{
					System.out.println(symptoms.get(f));
				}
				//create new illness
				//call save method
			}
		});
		panel.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 52, 231, 196);
		panel.add(scrollPane);
		
		scrollPane.setViewportView(textArea_1);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{scrollPane, textArea, textArea_1, label_1, btnSave, clrBtn, label, textField}));
		
		
		panel_1.setBounds(405, 25, 354, 317);
		//frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(42, 13, 58, 28);
		panel_1.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 8, 228, 33);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(110, 52, 231, 196);
		panel_1.add(scrollPane_1);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setViewportView(textArea_2);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResults.setBounds(42, 49, 64, 29);
		panel_1.add(lblResults);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(129, 261, 72, 33);
		panel_1.add(btnSearch);
		
		JButton button_2 = new JButton("Clear");
		button_2.setBounds(224, 261, 72, 33);
		panel_1.add(button_2);
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
