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
	
    public relation_jtable(String[][] relationObject) {
        // Make the frame
        JFrame frame = new JFrame("JFrame and JTable example 2");
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Action when window closes

        JTable table = new JTable(relationObject, columnNames);              // Make table
        JScrollPane scrollPane = new JScrollPane(table);        // Make scrollpane with table

        // Add scrollpane with table to the frame and show the frame to user
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}

