package UI;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import db.sqliteDB;
import db.Person;
import db.persist;

import UI.Duplicate_Entry;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Iterator;

import javax.swing.JComboBox;

import lme.logical_entities;

public class text_form extends JFrame {
	
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String Gender_str = new String();
	private String str2 = new String();
	private JTextField textField_4;
	int selectedDay;
    int selectedMonth;
	int selectedYear;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					text_form frame = new text_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public text_form() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 650, 3000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First - Name");
		lblNewLabel.setBounds(34, 12, 93, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(165, 10, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last - Name");
		lblLastName.setBounds(34, 73, 93, 15);
		contentPane.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 71, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(45, 129, 70, 15);
		contentPane.add(lblAge);
		
		textField_2 = new JTextField();
		textField_2.setBounds(165, 127, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(45, 176, 70, 15);
		contentPane.add(lblId);
		
		textField_3 = new JTextField();
		textField_3.setBounds(165, 174, 114, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				try{
					final Person p = new Person();
					persist persist_object = new persist();
				sqliteDB db = sqliteDB.getInstance();
				
				
				String str1 = textField.getText();
				String str2 = textField_1.getText();
				//String str3 = textField_2.getText();
				selectedYear = Integer.parseInt(textField_4.getText());
				String BirthDate = selectedDay+"-"+selectedMonth+"-"+selectedYear;
				
				int num1 = Integer.parseInt(textField_2.getText());
				int num2 =  Integer.parseInt(textField_3.getText());
				
				p.setFirstName(str1);
				p.setLastName(str2);
				p.setAge(num1);
				p.setId(num2);
				p.setGender(Gender_str);
				p.setBirthDate(BirthDate);
				logical_entities LE = logical_entities.getInstance();
				Iterator iterator = LE.Person_HM.keySet().iterator();  
				   
				while (iterator.hasNext()) {  
				   int key = Integer.parseInt(iterator.next().toString());  
				   Person value = LE.Person_HM.get(key); 
				//HashMap<Key, Value> map = new HashMap<Key, Value>();
				//for (Integer key : LE.Person_HM.keySet()) {
				  //  Person value = LE.Person_HM.get(key);
				    System.out.println("g "+p.getGender());
				    if(value.getFirstName().equalsIgnoreCase(p.getFirstName()) && value.getLastName().equalsIgnoreCase(p.getLastName()) && value.getAge() == p.getAge() && value.getGender().equalsIgnoreCase(p.getGender())){
				    	EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									Duplicate_Entry frame = new Duplicate_Entry(p);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
				    	break;
				}
				    else{
				    	persist_object.createNewPerson(p);
				    	break;
				    }
				    	
				    
				}
				
				//persist_object.createNewPerson(p);
				
				
				//db.insertIntoTablePerson(str1, str2, num1, num2);
				
				}
				catch(Exception ex){
					
					//	System.out.println(e.getMessage());
					System.out.println("error in action performed method of person form :");
						ex.printStackTrace();
						
					}
				
				
			}
		});
		btnSubmit.setBounds(45, 380, 117, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(238, 380, 117, 25);
		contentPane.add(btnCancel);
		
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				Gender_str = "M";
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(108, 202, 149, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				Gender_str = "F";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(262, 201, 123, 23);
		contentPane.add(rdbtnFemale);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(45, 251, 93, 15);
		contentPane.add(lblDateOfBirth);
		
		 final JComboBox<Integer> comboBox = new JComboBox<Integer>();
		for(int i=1;i<=31;i++){
			comboBox.addItem(i);
			}
		/*Initialization*/
		comboBox.setSelectedIndex(0);
		selectedDay = Integer.parseInt(comboBox.getSelectedItem().toString());
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					selectedDay = Integer.parseInt(comboBox.getSelectedItem().toString());
				}
			}
		});
		
		comboBox.setBounds(54, 291, 48, 24);
		contentPane.add(comboBox);
		
		final JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		for(int i=1;i<=12;i++){
			comboBox_1.addItem(i);
		}
		/*Initialization*/
		comboBox_1.setSelectedIndex(0);
		selectedMonth = Integer.parseInt(comboBox_1.getSelectedItem().toString());
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedMonth = Integer.parseInt(comboBox_1.getSelectedItem().toString());
			}
		});
		
		comboBox_1.setBounds(180, 291, 39, 24);
		contentPane.add(comboBox_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(241, 294, 114, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}