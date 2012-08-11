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
				
				/*EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							select_person frame = new select_person();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});*/
			/*	public TableModel queryAsModel(String query) {  
					sqliteDB dbs = sqliteDB.getInstance();   
					TableModel dtm_search_model;  
					   Statement stat = null;
						Connection conn;
						try
						{
							conn = dbs.getConnection();
							if(conn == null){
								throw new Exception("exception thrown");
								
							}
						
							
							stat = conn.createStatement();
							 ResultSet rs = stat.executeQuery(query);
							 dtm_search_model = new DefaultTableModel(  
							         sqliteDB.data(rs),  
							         sqliteDB.headings(rs));  
							   } catch(Exception e) {  
							      throw new RuntimeException(e);  
							   }  
						
						stat.close();
						conn.close();
							   return dtm_search_model;  
				
				}	*/
					
						       
						
						/*catch (Exception e) 
						{
							System.out.println(e);
							System.out.println(Messages.ERR_PERSON_SELECTION);
						}*/
						
							
						
						//return Constants.FALSE_VALUE;
					
				
				
				/*try{
					persist persist_form_object = new persist();
					persist_form_object.selectAllPerson();
				}
				
catch(Exception ex){
					
					//	System.out.println(e.getMessage());
					System.out.println("error in action performed method of selecting persons :");
						ex.printStackTrace();
						
					}*/
				
			}
		});
		btnDeletePerson.setBounds(119, 142, 169, 42);
		contentPane.add(btnDeletePerson);
		
		JButton btnNewButton = new JButton("View all person");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				persist persist_object = new persist();
				persist_object.selectAllPerson();
				 new_jtable new_jtable_object = new new_jtable();
				}
				catch (Exception er) 
				{
					System.out.println(e);
					System.out.println("Error in viewing all person :");
				}
					  
			}
		});
		btnNewButton.setBounds(129, 201, 117, 25);
		contentPane.add(btnNewButton);
	}
}