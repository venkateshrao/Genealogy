package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import UI.new_jtable;

//import db.Constants;
//import db.Messages;
//import db.Messages;
import db.sqliteDB;
import db.Person;
import db.persist;

public class form extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form frame = new form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPeopleForm = new JButton("People Form");
		btnPeopleForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		btnPeopleForm.setBounds(119, 12, 169, 42);
		contentPane.add(btnPeopleForm);
		
		JButton btnRelationshipForm = new JButton("Relationship Form");
		btnRelationshipForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					persist persist_object = new persist();
					persist_object.selectPersonName();
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
					catch (Exception er) 
					{
						System.out.println(e);
						System.out.println("Error in viewing all person :");
					}					
			}
		});
		
		btnRelationshipForm.setBounds(119, 75, 169, 42);
		contentPane.add(btnRelationshipForm);
		
		JButton btnDeletePerson = new JButton("Delete Person");
		btnDeletePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
			}
		});
		btnDeletePerson.setBounds(119, 142, 169, 42);
		contentPane.add(btnDeletePerson);
		
		JButton btnNewButton = new JButton("View all person");
		
		btnNewButton.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				try{
				persist persist_object = new persist();
				ArrayList<Person> personList = new ArrayList<Person>();
				persist_object.selectAllPerson(personList);
				new_jtable new_jtable_object = new new_jtable(personList);
				}
				catch (Exception er) 
				{
					System.out.println(e);
					System.out.println("Error in viewing all person :");
				}
					  
			}
		}
		);
		btnNewButton.setBounds(129, 201, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnViewAllRelations = new JButton("View all relations");
		btnViewAllRelations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					persist persist_object = new persist();
					persist_object.selectAllRelation();
					 relation_jtable rel_table = new relation_jtable();
					}
					catch (Exception er) 
					{
						System.out.println(e);
						System.out.println("Error in viewing all person :");
					}
			}
		});
		btnViewAllRelations.setBounds(139, 233, 117, 25);
		contentPane.add(btnViewAllRelations);
	}
}