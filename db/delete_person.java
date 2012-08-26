package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import db.Person;
import db.persist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class delete_person extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delete_person frame = new delete_person();
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
	public delete_person() {
		super("Delete aperson");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID to be deleted");
		lblEnterTheId.setBounds(104, 12, 213, 52);
		contentPane.add(lblEnterTheId);
		
		textField = new JTextField();
		textField.setBounds(129, 76, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					Person p = new Person();	
					persist persist_object = new persist();
					int num = Integer.parseInt(textField.getText());
					p.setId(num);
					persist_object.deletePerson(p);
				
				}
				
catch(Exception ex){
					
					//	System.out.println(e.getMessage());
					System.out.println("error in action performed method of delete person:");
						ex.printStackTrace();
						
					}
			}
		});
		btnDelete.setBounds(47, 146, 117, 25);
		contentPane.add(btnDelete);
	}
}
