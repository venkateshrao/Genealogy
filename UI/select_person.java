package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import db.sqliteDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class select_person extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					select_person frame = new select_person();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public select_person() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(queryAsModel("select * from people;"));
		table.setBounds(28, 22, 355, 248);
		contentPane.add(table);
	}
	
	public static TableModel queryAsModel(String query) throws Exception {  
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
	
	}

	
}
