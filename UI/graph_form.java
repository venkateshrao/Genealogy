package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import db.persist;
import db.sqliteDB;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class graph_form extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graph_form frame = new graph_form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	int p_id;
	String selectedPerson = new String();
	String[] str = new String[20];
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton rdbtnAll;
	JRadioButton rdbtnAncestry;

	/**
	 * Create the frame.
	 */
	public graph_form() throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<String> listOfAllPeopleNames = new ArrayList<String>();
		sqliteDB db = sqliteDB.getInstance();
		db.getAllNamesFromPeopleTable(listOfAllPeopleNames);
		String[] namesList = listOfAllPeopleNames.toArray(new String[listOfAllPeopleNames.size()]);
		final JComboBox comboBox = new JComboBox(namesList);
		/*Initialization*/
		comboBox.setSelectedItem(namesList[0]);
		selectedPerson = comboBox.getSelectedItem().toString();
		sqliteDB Db = sqliteDB.getInstance();
		p_id = Db.getIdFromFullName(selectedPerson);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try{
					sqliteDB db = sqliteDB.getInstance();
					if(e.getStateChange() == ItemEvent.SELECTED){
						System.out.println(comboBox.getSelectedItem());
						System.out.println("combobox item listener");
						selectedPerson = comboBox.getSelectedItem().toString();
						
						p_id = db.getIdFromFullName(selectedPerson);
					}
					}
					catch(Exception E){
						E.printStackTrace();
						System.out.println(E);
						System.out.println("Error in selecting person1 :");
					}
			}
		});
		comboBox.setBounds(33, 22, 163, 59);
		contentPane.add(comboBox);
		
		JButton btnShowTheGraph = new JButton("Show the Graph");
		btnShowTheGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(rdbtnAll.isSelected()){
					GraphJFaceSnippet2 gef_object = new GraphJFaceSnippet2();
					}
					
					if(rdbtnAncestry.isSelected()){
						GraphSnippet1 gef1_object = new GraphSnippet1(p_id);
					}
					
					
					
				}
				catch(Exception ex){
					ex.printStackTrace();
					
					
				}
			}
		});
		btnShowTheGraph.setBounds(50, 139, 117, 25);
		contentPane.add(btnShowTheGraph);
		
		 rdbtnAll = new JRadioButton("All");
		rdbtnAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try{
					persist persist_object = new persist();
					ArrayList<ArrayList<String>> relationObject = new ArrayList<ArrayList<String>>();
					persist_object.selectAllRelationForGraph(relationObject, p_id);
					//GraphJFaceSnippet2 gef_object = new GraphJFaceSnippet2();
				}
				
					
					
				
				catch(Exception ex){
					ex.printStackTrace();
					
				}
				
				
			}
		});
		buttonGroup.add(rdbtnAll);
		rdbtnAll.setBounds(33, 100, 149, 23);
		contentPane.add(rdbtnAll);
		
		rdbtnAncestry = new JRadioButton("Ancestry");
		rdbtnAncestry.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try{
					persist persist_object = new persist();
					ArrayList<ArrayList<Object>> relationObject = new ArrayList<ArrayList<Object>>();
					persist_object.selectAllPersonforGraphSnippet1(relationObject, p_id);
				//GraphSnippet1 gef1_object = new GraphSnippet1(p_id);
				}
				catch(Exception ep){
					System.out.println("Error in ancestry radio button : ");
					ep.printStackTrace();
					
				}
			}
		});
		buttonGroup.add(rdbtnAncestry);
		rdbtnAncestry.setBounds(190, 100, 149, 23);
		contentPane.add(rdbtnAncestry);
	}
}
