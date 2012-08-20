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
import java.util.ArrayList;

import javax.swing.*;


//import db.Messages;
import db.Person;
import db.Relationships;
import db.persist;
import db.sqliteDB;

import java.io.*;


public class relation_form extends JFrame {

	protected static final String Relationships = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	public Object selectedRel = new Object();
	int p1_id;
	int p2_id;
	String selectedPerson1 = new String();
	String selectedPerson2 = new String();
	
	/**
	 * Create the frame.
	 */
	public relation_form() throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(db.Relationships.values()));
		/*Initialization*/
		comboBox.setSelectedIndex(0);
		selectedRel = comboBox.getSelectedItem();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					System.out.println(comboBox.getSelectedItem());
					System.out.println("combobox item listener");

					selectedRel = comboBox.getSelectedItem();

				}

			}
		});


		comboBox.setBounds(176, 83, 128, 31);
		contentPane.add(comboBox);

		JLabel lblRelationshipName = new JLabel("Relationship Name");
		lblRelationshipName.setBounds(12, 99, 158, 15);
		contentPane.add(lblRelationshipName);

		JLabel lblPersonId = new JLabel("person1 ID");
		lblPersonId.setBounds(26, 28, 114, 15);
		contentPane.add(lblPersonId);

		JLabel lblPersonId_1 = new JLabel("person2 ID");
		lblPersonId_1.setBounds(42, 149, 98, 15);
		contentPane.add(lblPersonId_1);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					persist persist_object = new persist();
					persist_object.createNewRelationship(selectedRel, p1_id , p2_id);

				}

				catch(Exception ex){

					System.out.println("error in action performed method of Relation form :");
					ex.printStackTrace();

				}

			}
		});
		btnSubmit.setBounds(42, 218, 117, 25);
		contentPane.add(btnSubmit);

		ArrayList<String> listOfAllPeopleNames = new ArrayList<String>();
		sqliteDB db = sqliteDB.getInstance();
		db.getAllNamesFromPeopleTable(listOfAllPeopleNames);
		String[] namesList = listOfAllPeopleNames.toArray(new String[listOfAllPeopleNames.size()]);
		final JComboBox comboBox_1 = new JComboBox(namesList);
		
		/*Initialization*/
		comboBox_1.setSelectedItem(namesList[0]);
		comboBox_1.addItemListener(new ItemListener()  {
			public void itemStateChanged(ItemEvent e) {
		
				try{
				
				if(e.getStateChange() == ItemEvent.SELECTED){
					System.out.println(comboBox_1.getSelectedItem());
					System.out.println("combobox item listener");
					selectedPerson1 = comboBox_1.getSelectedItem().toString();
					sqliteDB db = sqliteDB.getInstance();
					p1_id = db.getIdFromFullName(selectedPerson1);
				}
				}
				catch(Exception E){
					E.printStackTrace();
					System.out.println(E);
					System.out.println("Error in selecting person1 :");
				}
			
			}
		});
		comboBox_1.setBounds(176, 12, 128, 35);
		contentPane.add(comboBox_1);

		final JComboBox comboBox_2 = new JComboBox(namesList);
		/*Initialization*/
		comboBox_1.setSelectedItem(namesList[0]);
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				try{
					
					if(e.getStateChange() == ItemEvent.SELECTED){
						System.out.println(comboBox_2.getSelectedItem());
						System.out.println("combobox item listener");
						selectedPerson1 = comboBox_2.getSelectedItem().toString();
						sqliteDB db = sqliteDB.getInstance();
						p2_id = db.getIdFromFullName(selectedPerson1);
					}
					}
					catch(Exception E){
						E.printStackTrace();
						System.out.println(E);
						System.out.println("Error in selecting person2 :");
					}

			}
		});
		comboBox_2.setBounds(176, 144, 128, 31);
		contentPane.add(comboBox_2);
	}
}