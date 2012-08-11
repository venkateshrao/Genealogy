package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


import db.Relationships;


public class relation_form extends JFrame {

	protected static final String Relationships = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					relation_form frame = new relation_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//private String[] relation_names = {"Father" , "Mother" , "Son" , "Daughter" , "Husband" , "Wife" , "Brother" , "Sister"};
	//db.Relationships relation_enum =   ;//, db.Relationships.REL_MOTHER , db.Relationships.REL_SON , db.Relationships.REL_DAUGHTER , db.Relationships.REL_HUSBAND , db.Relationships.REL_WIFE ,db. Relationships.REL_BROTHER , db.Relationships.REL_SISTER};
	private JTextField textField;
	//Relationships rel;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public relation_form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*int i =0;
		for(Relationships rel:Relationships.values()){
			str[i] = rel.get_rel_num();
			i++;
		}*/
		
		final JComboBox<Relationships> comboBox = new JComboBox<Relationships>();
		comboBox.setModel(new DefaultComboBoxModel<Relationships>(db.Relationships.values()));
	comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
				System.out.println(comboBox.getSelectedObjects());
				System.out.println("combobox item listener");
				//db.Relationships rel = db.Relationships.values();
				//db.Relationships.values() = comboBox.getSelectedObjects();
				}
				
			}
		});
		
		
		comboBox.setBounds(176, 83, 128, 31);
		contentPane.add(comboBox);
		
		JLabel lblRelationshipName = new JLabel("Relationship Name");
		lblRelationshipName.setBounds(12, 99, 158, 15);
		contentPane.add(lblRelationshipName);
		
		textField = new JTextField();
		textField.setBounds(176, 26, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(176, 147, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPersonId = new JLabel("person1 ID");
		lblPersonId.setBounds(26, 28, 114, 15);
		contentPane.add(lblPersonId);
		
		JLabel lblPersonId_1 = new JLabel("person2 ID");
		lblPersonId_1.setBounds(42, 149, 98, 15);
		contentPane.add(lblPersonId_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rel_str1 = textField.getText();
				String rel_str2 = textField_1.getText();
				
				
			}
		});
		btnSubmit.setBounds(42, 218, 117, 25);
		contentPane.add(btnSubmit);
	}
}