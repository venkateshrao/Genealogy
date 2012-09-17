package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import db.Person;
import db.persist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Duplicate_Entry extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Duplicate_Entry frame = new Duplicate_Entry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Duplicate_Entry(final Person p) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouAreTrying = new JLabel("You are trying to create a duplicate entry");
		lblYouAreTrying.setBounds(26, 39, 342, 46);
		contentPane.add(lblYouAreTrying);
		
		JLabel lblDoYouWish = new JLabel("Do you wish to continue?");
		lblDoYouWish.setBounds(67, 87, 228, 31);
		contentPane.add(lblDoYouWish);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				persist persist_object = new persist();
				persist_object.createNewPerson(p);
				}
				catch(Exception ee){
					System.out.println("Error in Duplicate entry class: ");
					ee.printStackTrace();
					
				}
			}
		});
		btnYes.setBounds(42, 145, 117, 25);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNo.setBounds(232, 145, 117, 25);
		contentPane.add(btnNo);
	}
}
