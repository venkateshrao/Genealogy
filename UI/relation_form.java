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


import db.Person;
import db.Relationships;
import db.persist;

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
	
	private static String[] tabulatePerson1 = new String[20];
	private static String[] tabulatePerson2 = new String[20];
	private static String[] tabulatePerson3 = new String[20];
	public Object selectedRel = new Object();
	Object selectedPerson1 = new Object();
	Person p1 = new Person();
	Object selectedPerson2 = new Object();
	Person p2 = new Person();
	static int i=0 , j=0;
	static int i1,i2;
	public int i3;
	public static ArrayList<Person> pList =  new ArrayList<Person>();
	
	public static void putPersonNameIntoTable(ArrayList<Person> personList1){
		pList = personList1;
		//for(j = 0 ; j<personList1.size() ; j++)
			//pList.get(j).setId(personList1.get(j).getId());
		for(i = 0;i<personList1.size();i++)
			{
				tabulatePerson1[i] = personList1.get(i).getFirstName();
				System.out.println(tabulatePerson1[i]);
				tabulatePerson2[i] = personList1.get(i).getLastName();
				System.out.println(tabulatePerson2[i]);
				tabulatePerson3[i] = tabulatePerson1[i]+ " " +tabulatePerson2[i];
				//tabulatePerson[i][j+2] = personList1.get(i).getAge();
				//System.out.println(tabulatePerson[i][j+2]);
				//tabulatePerson[i][j+3] = personList1.get(i).getId();
				//System.out.println(tabulatePerson[i][j+3]);
				
			}
		
		
	}

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
		/*Initialization*/
		comboBox.setSelectedIndex(0);
		selectedRel = comboBox.getSelectedItem();
	comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
				System.out.println(comboBox.getSelectedItem());
				System.out.println("combobox item listener");
				
				selectedRel = comboBox.getSelectedItem();
				
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
				persist_object.createNewRelationship(selectedRel, p1, p2);
				
				}
				
				catch(Exception ex){
					
					//	System.out.println(e.getMessage());
					System.out.println("error in action performed method of Relation form :");
						ex.printStackTrace();
						
					}
				
			}
		});
		btnSubmit.setBounds(42, 218, 117, 25);
		contentPane.add(btnSubmit);
		
		final JComboBox comboBox_1 = new JComboBox(tabulatePerson3);
		/*Initialization*/
		comboBox_1.setSelectedItem(tabulatePerson3[0]);
		p1 = pList.get(0);
		comboBox_1.addItemListener(new ItemListener()  {
			public void itemStateChanged(ItemEvent e) {
				//String a[] = new String[20];
				//a = tabulatePerson3;
				//System.out.println("a is " +a[1]);
				
				
				if(e.getStateChange() == ItemEvent.SELECTED){
					System.out.println(comboBox_1.getSelectedItem());
					System.out.println("combobox item listener");
					
					//p1 = (Person) comboBox.getSelectedItem();
					selectedPerson1 = comboBox_1.getSelectedItem();
					System.out.println("selected item is "+selectedPerson1);
					for( i3 = 0 ; i3 < tabulatePerson3.length ; i3++){
						System.out.println(tabulatePerson3.length);
						System.out.println(tabulatePerson3[i3]);
						if(selectedPerson1==tabulatePerson3[i3]){
							//System.out.println(tabulatePerson3[i3]);
							//p1.setId(pList.get(i).getId());
							System.out.println("pList id " +pList.get(i3).getId());
							p1 = pList.get(i3);
							i1 = pList.get(i3).getId();
							break;
							
							
						}
					}
					//System.out.println("pList id " +pList.get(i3).getId());
					//System.out.println("tab per"+tabulatePerson3[i3]);
					//System.out.println("pList id " +pList.get(i).getId());
					//System.out.println("id is"+p1.getId());
					
					//System.out.println("the id is"+i1);
					//db.Relationships rel = db.Relationships.values();
					//db.Relationships.values() = comboBox.getSelectedObjects();
					}
			}
		});
		comboBox_1.setBounds(176, 12, 128, 35);
		contentPane.add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox(tabulatePerson3);
		/*Initialization*/
		comboBox_1.setSelectedItem(tabulatePerson3[0]);
		p2 = pList.get(0);
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED){
					System.out.println(comboBox_2.getSelectedItem());
					System.out.println("combobox item listener");
					
					//p2 =  (Person) comboBox.getSelectedItem();
					selectedPerson2 = comboBox_2.getSelectedItem();
					for(i3 = 0 ; i3 < tabulatePerson3.length ; i3++){
						System.out.println("for loop");
						if(selectedPerson2==tabulatePerson3[i3]){
							//p2.setId(pList.get(i3).getId());
							System.out.println("pList id " +pList.get(i3).getId());
							p2 = pList.get(i3);
							i2 = pList.get(i3).getId();
							break;
							
							
						}
					}
					
					//System.out.println(p2.getId());
					
					//System.out.println(i2);
					//db.Relationships rel = db.Relationships.values();
					//db.Relationships.values() = comboBox.getSelectedObjects();
					}
			}
		});
		comboBox_2.setBounds(176, 144, 128, 31);
		contentPane.add(comboBox_2);
	}
}