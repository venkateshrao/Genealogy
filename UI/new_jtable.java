package UI;

import java.awt.BorderLayout;
import java.util.ArrayList;

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
	private Object[][] tabulatePerson = new Object[20][20];

	private void createPersonListTabulation(ArrayList<Person> personList1){
		for(int i=0,j=0;i<personList1.size();i++)
		{
			tabulatePerson[i][j] = personList1.get(i).getFirstName();
			System.out.println(tabulatePerson[i][j]);
			tabulatePerson[i][j+1] = personList1.get(i).getLastName();
			System.out.println(tabulatePerson[i][j+1]);
			tabulatePerson[i][j+2] = personList1.get(i).getAge();
			System.out.println(tabulatePerson[i][j+2]);
			tabulatePerson[i][j+3] = personList1.get(i).getId();
			System.out.println(tabulatePerson[i][j+3]);

		}
	}

	public new_jtable(ArrayList<Person> personList) {
		// Make the frame

		createPersonListTabulation(personList);
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
