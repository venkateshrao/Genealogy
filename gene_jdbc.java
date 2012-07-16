import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JButton;

class Gene extends JFrame implements ActionListener
{
	//Declare the GUI items that we'll be using in this class
	JLabel lbl1;
	JFrame frame1;
	JTextField text1;
	JTextField text2;
	JButton reg;

	//Constructor
	Gene()
	{
		//Create a window layout
		//TODO: move all this to a generic framing function/class
		frame1 = new JFrame();
		frame1.setLayout(new FlowLayout());

		lbl1 = new JLabel("Enter the first name: ");
		frame1.add(lbl1);
		text1 = new JTextField(20);
		frame1.add(text1);

		lbl1 = new JLabel("Enter the last name: ");
		frame1.add(lbl1);
		text2 = new JTextField(20);
		frame1.add(text2);

		reg = new JButton("Submit");
		frame1.add(reg);
		
		//callback function gets data from this class
		reg.addActionListener(this);

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(275,180);
		frame1.setVisible(true);

	}

	//what we do 
	public void actionPerformed(ActionEvent event)
	{
		try
		{	
			String s1 = text1.getText();
			String s2 = text2.getText();
			
			//Confirm the text that we printed
			System.out.println("name is " + s1 + " " + s2);
						
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:ttest.db");
			Statement stat = conn.createStatement();
			
			//see if we have to initialize the database
			//TODO: Move this to an initialize function
			
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (F_name, L_name);");	
					
			PreparedStatement prep = conn.prepareStatement(
					"insert into people values (?, ?);");
			
			prep.setString(1 , s1);
			prep.setString(2 , s2);
			prep.addBatch();

			//execute commands in one go. Atomic operation
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);

			//Print out values we entered into the database
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
} // end gene_demo

//the class used the call main
//TODO: give the main() more responsibility and organize class structure
class gene_jdbc
{
	public static void main(String arg[])
	{
		Gene gene = new Gene();
	}
}