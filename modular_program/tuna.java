import java.awt.FlowLayout;
import java.sql.DatabaseMetaData;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class tuna extends JFrame {
	
	private JLabel item1;
	private JTextField item2;
	private JTextField item3;
	private JButton reg;
	
	public tuna(){
		
		super("Genealogy Test Form ");
		setLayout(new FlowLayout());
		
		item1 = new JLabel("Enter the first name: ");
		add(item1);
		item2 = new JTextField(20);
		add(item2);
		
		item1 = new JLabel("Enter the last name: ");
		add(item1);
		item3 = new JTextField(20);
		add(item3);
		
		reg = new JButton("Submit");
		add(reg);
		
		HandlerClass Handler = new HandlerClass();
		reg.addActionListener(Handler);
		
		
	}
	
	private class HandlerClass implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			String str = item2.getText();
			String str2 = item3.getText();
			try{
				 
			        Connection conn = getConnection(); 
			        if(conn == null){
			        	throw new Exception("exception thrown");
			        }
			        
			        Statement stat = conn.createStatement();
			    // ResultSet rs = stat.executeQuery(".");
			     // stat.executeUpdate("drop table if exists people;");
			       // DatabaseMetaData meta = conn.getMetaData();
			        //String[] names = { "people"};
			     // ResultSet rs = meta.getTables(null,null,"%",null);
			        //ResultSet rs = meta.getCatalogs();
			     // ResultSet rs = meta.getTables(null, null, null, 
			        	    /*new String[] {"people"});
			       // boolean boResult = stat.execute("people");
			      while (rs.next()) {
			            System.out.println("t-name = " + rs.getString(1));
			          //  System.out.println("l-name = " + rs.getString("L_name"));
			        }*/
			        
			       if(!isTableExists("people") ){
			    	   
			        	//stat.executeUpdate("drop table if exists people;");
			       stat.executeUpdate("create table people (F_name, L_name);");
			       }
			       /*rs.moveToInsertRow();
			       rs.updateString(1, str);
			       rs.updateString(2, str2);
			       rs.insertRow();
			       rs.moveToCurrentRow();*/
			        PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

			        prep.setString(1 , str);
			        prep.setString(2 , str2);
			        prep.addBatch();

			        conn.setAutoCommit(false);
			        prep.executeBatch();
			        conn.setAutoCommit(true);

			       ResultSet rs = stat.executeQuery("select * from people;");
			        while (rs.next()) {
			            System.out.println("f-name = " + rs.getString("F_name"));
			            System.out.println("l-name = " + rs.getString("L_name"));
			        }
			        rs.close();

			        conn.close();
				
			}
				catch(Exception e){
					
				//	System.out.println(e.getMessage());
					e.printStackTrace();
					
				}
		  }

		
			
		}
	private Connection getConnection() {
		// TODO Auto-generated method stub
		try{
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:jsoftsol.db");
		}
		catch(Exception e){ System.out.println("Error Connection ");}
		return null;
	}

	private boolean isTableExists(String Table_Name) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		 Statement stat = null;
		 ResultSet rs = null;
		try{
		 conn = getConnection();
		  stat = conn.createStatement();
		//return false;
	 rs = stat.executeQuery("select name from sqlite_master where type = 'table' and name = '"+Table_Name+"';");
	return rs.next();
		}
		catch (Exception e){
			System.out.println("error checking table exists");
			
		}
		finally{
			rs.close();
			stat.close();
			conn.close();
		}
		return false;
	}

	}            
