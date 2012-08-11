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



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class text_form extends JFrame {
	
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	

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
		setBounds(100, 100, 450, 300);
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
					Person p = new Person();
					persist persist_object = new persist();
				sqliteDB db = sqliteDB.getInstance();
				
				
				String str1 = textField.getText();
				String str2 = textField_1.getText();
				//String str3 = textField_2.getText();
				//String str4 = textField_3.getText();
				
				int num1 = Integer.parseInt(textField_2.getText());
				int num2 =  Integer.parseInt(textField_3.getText());
				
				p.setFirstName(str1);
				p.setLastName(str2);
				p.setAge(num1);
				p.setId(num2);
				
				persist_object.createNewPerson(p);
				
				
				//db.insertIntoTablePerson(str1, str2, num1, num2);
				
				}
				catch(Exception ex){
					
					//	System.out.println(e.getMessage());
					System.out.println("error in action performed method :");
						ex.printStackTrace();
						
					}
				
				
			}
		});
		btnSubmit.setBounds(87, 233, 117, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(253, 233, 117, 25);
		contentPane.add(btnCancel);
	}
}