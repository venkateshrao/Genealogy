package UI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Person;

/**
 * Simple example on JFrame and JTable in Java.
 * @author http://www.gammelsaeter.com/
 */
public class new_jtable {
	
	public String[] columnNames = {"f_name" , "l_name" , "age" , "ID"};
	private static Object[][] tabulatePerson = new Object[20][20];
	static int i=0;
    //private String[] _titles = new String[] {"Name", "Vage"};
    //private String[][] _data = new String[][] {{"Donald Duck", "100"},{"Mickey Mouse", "120"}};
    
    public static void putPersonIntoTable(Person[] pList){
		for(Person p:pList)
			for(int j=0;j<1;j++)
			{
				tabulatePerson[i][j] = p.getFirstName();
				System.out.println(tabulatePerson[i][j]);
				tabulatePerson[i][j+1] = p.getLastName();
				System.out.println(tabulatePerson[i][j+1]);
				tabulatePerson[i][j+2] = p.getAge();
				System.out.println(tabulatePerson[i][j+2]);
				tabulatePerson[i][j+3] = p.getId();
				System.out.println(tabulatePerson[i][j+3]);
			}
		i++;
	}

    public new_jtable() {
        // Make the frame
        JFrame frame = new JFrame("JFrame and JTable example 2");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Action when window closes

        JTable table = new JTable(tabulatePerson, columnNames);              // Make table
        JScrollPane scrollPane = new JScrollPane(table);        // Make scrollpane with table

        // Add scrollpane with table to the frame and show the frame to user
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

   /* public static void main(String args[]) {
        new_jtable = new new_jtable();
    }*/
}
