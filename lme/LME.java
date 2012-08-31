package lme;

import java.awt.EventQueue;

import UI.form;

public class LME {
	
	public static void main(String[] args)throws Exception{
		
		logical_entities le_object = logical_entities.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form frame = new form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
