import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
public class gene_jdbc extends JFrame implements ActionListener {
	
	

	public static void main(String[] args) throws Exception{
		
		
		gene_jdbc gj = new gene_jdbc();
	 
		JLabel item1;
		 JTextField item2;
		 JTextField item3;
		 JButton reg;
		 
				gj.setLayout(new FlowLayout());
				
				item1 = new JLabel("Enter the first name: ");
				gj.add(item1);
				item2 = new JTextField(20);
				gj.add(item2);
				
				item1 = new JLabel("Enter the last name: ");
				gj.add(item1);
				item3 = new JTextField(20);
				gj.add(item3);
				
				reg = new JButton("Submit");
				gj.add(reg);
				gene_jdbc Handler = new gene_jdbc();
				reg.addActionListener(Handler);
				
				String str = item2.getText();
				String str2 = item3.getText();
				
				public void actionPerformed(ActionEvent event) {
					
					 Class.forName("org.sqlite.JDBC");
				        Connection conn = DriverManager.getConnection("jdbc:sqlite:ttest.db");
				        Statement stat = conn.createStatement();
				        stat.executeUpdate("drop table if exists people;");
				        stat.executeUpdate("create table people (F_name, L_name);");
				        PreparedStatement prep = conn.prepareStatement(
				            "insert into people values (?, ?);");

				        prep.setString(1 , "str");
				        prep.setString(2 , "str2");
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
				
			 
			
			gj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gj.setSize(275,180);
			gj.setVisible(true);
		}
}

		
		
		

