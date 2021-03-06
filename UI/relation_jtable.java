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
	private static Object[][] tabulateRelation ;
	
    public relation_jtable(ArrayList<ArrayList<String>> relationObject) {
        // Make the frame
    	
    	final String[][] r = new String[relationObject.size()][];
    	int i = 0;
    	for (ArrayList<String> l : relationObject) 
    	  r[i++] = l.toArray(new String[l.size()]);
        JFrame frame = new JFrame("JFrame and JTable example 2");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Action when window closes

        JTable table = new JTable(r, columnNames);              // Make table
        JScrollPane scrollPane = new JScrollPane(table);        // Make scrollpane with table

        // Add scrollpane with table to the frame and show the frame to user
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}

