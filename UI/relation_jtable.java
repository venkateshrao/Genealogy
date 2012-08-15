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
public class relation_jtable {
	
	public String[] columnNames = {"Relation name" , "Person1" , "Person2"};
	private static Object[][] tabulateRelation = new Object[200][200];
	static int i=0 , j=0;
    //private String[] _titles = new String[] {"Name", "Vage"};
    //private String[][] _data = new String[][] {{"Donald Duck", "100"},{"Mickey Mouse", "120"}};
    
    public static void putPersonNameIntoJTable(Object[][] relationList1){
    	tabulateRelation = relationList1;
		/*for(i = 0;i<relationList1.length;i++)
			{
			tabulateRelation[i][j] = relationList1[i][j];
				System.out.println(tabulateRelation[i][j]);
				tabulateRelation[i][j+1] = relationList1[i][j+1];
				System.out.println(tabulateRelation[i][j+1]);
				//tabulateRelation[i][j+2] = relationList1.get(i).getAge();
				//System.out.println(tabulateRelation[i][j+2]);
				tabulateRelation[i][j+3] = relationList1[i][j+2];
				System.out.println(tabulateRelation[i][j+3]);
				
			}
		i++;*/
		
	}

    public relation_jtable() {
        // Make the frame
        JFrame frame = new JFrame("JFrame and JTable example 2");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Action when window closes

        JTable table = new JTable(tabulateRelation, columnNames);              // Make table
        JScrollPane scrollPane = new JScrollPane(table);        // Make scrollpane with table

        // Add scrollpane with table to the frame and show the frame to user
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

   /* public static void main(String args[]) {
        new_jtable = new new_jtable();
    }*/
}

