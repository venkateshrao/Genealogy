import java.awt.FlowLayout;
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
		
		//reg.addActionListener(new HandlerClass(item2.getText() , item3.getText()));
		
		
	}
	
	private class HandlerClass implements ActionListener{
		
		/*private String s;
		private String a;
		
		public HandlerClass(String str , String str2){
			s = str;
			a = str2;
			System.out.println("hi" +s);
		}*/
		
		
		public void actionPerformed(ActionEvent event){
			try{
				String str = item2.getText();
				String str2 = item3.getText();
			Class.forName("org.sqlite.JDBC");
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:done.db");
	        Statement stat = conn.createStatement();
	        stat.executeUpdate("drop table if exists people;");
	        stat.executeUpdate("create table people (F_name, L_name);");
	        PreparedStatement prep = conn.prepareStatement(
	            "insert into people values (?, ?);");

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
		catch(Exception e){System.out.println("exe");}
 
				
			
			
		}
	}            
}

