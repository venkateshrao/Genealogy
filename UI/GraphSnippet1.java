package UI;
import java.util.HashMap;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import db.sqliteDB;

/**
 * This snippet creates a very simple graph where Rock is connected to Paper
 * which is connected to scissors which is connected to rock.
 * 
 * The nodes a layed out using a SpringLayout Algorithm, and they can be moved
 * around.
 * 
 * 
 * @author Ian Bull
 * 
 */
public class GraphSnippet1 {
	/**
	 * @param args
	 */
	public int i;
	public GraphSnippet1(int selectedId) throws Exception {
		i=selectedId;
		System.out.println("GraphSnippet1 constructor");
		Display d = new Display();
		Shell shell = new Shell(d);
		shell.setText("GraphSnippet1");
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);

		Graph g = new Graph(shell, SWT.NONE);
		sqliteDB db = sqliteDB.getInstance();
		GraphNode n = new GraphNode(g , SWT.None , db.getNameFromId(selectedId) );
		ancestry(selectedId , n,g);
		//ancestry2(selectedId , n,g);
		 g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		 shell.open();
			while (!shell.isDisposed()) {
				while (!d.readAndDispatch()) {
					d.sleep();
				}
			}
		// Create the shell
		
		/*Object[][] str1 = {{1,"b","c"},
				{"d","b","f"},
				{"g","b","i"}};
		int i =(int) str1[0][0];
		GraphNode n = null;
		GraphNode n2 = null;
		GraphNode n3 = null;
		GraphNode n4 = null;
				HashMap<String,GraphNode> HM = new HashMap<String,GraphNode>();
				GraphConnection GC = null;
				//for (int i=0;i<str1.length;i++){
					HM.put(str1[0][1] ,n= new GraphNode(g , SWT.None, str1[0][1]));
					HM.put(str1[0][2] , n2=new GraphNode(g , SWT.None, str1[0][2]));
					HM.put(str1[0][2] , n3=new GraphNode(g , SWT.None, str1[0][2]));
					HM.put(str1[0][2] , n2=new GraphNode(g , SWT.None, str1[0][2]));
					new GraphConnection(g,SWT.NONE,HM.get(str1[0][1]),HM.get(str1[0
					                                                              ][2]));
					// GC.setText(str1[i][0]);
				//}*/
				
		/*GraphNode n = new GraphNode(g,  SWT.NONE, "a");
		GraphNode n2 = new GraphNode(g, SWT.NONE, "a");
		GraphNode n3 = new GraphNode(g, SWT.NONE, "Scissors");
		GraphNode n4 = new GraphNode(g , SWT.NONE , "b");
		new GraphConnection(g , SWT.NONE , n , n4);
		new GraphConnection(g, SWT.NONE, n4, n);
		new GraphConnection(g, SWT.NONE, n, n2);
		new GraphConnection(g, SWT.NONE, n2, n3);
		g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		shell.open();
		while (!shell.isDisposed()) {
			while (!d.readAndDispatch()) {
				d.sleep();
			}
		}*/
	}
	
	void ancestry(int selectedId , GraphNode n,Graph g) throws Exception{
		
		/*System.out.println("Ancestry method");
		Display d = new Display();
		Shell shell = new Shell(d);
		shell.setText("GraphSnippet1");
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);
		Graph g = new Graph(shell, SWT.NONE);*/
		sqliteDB db_object = sqliteDB.getInstance();
		System.out.println("HI HI");
		if(db_object.get_fatherOf(selectedId)!=0){
			System.out.println("FatherOf is not 0");
			String f_name = db_object.getNameFromId(db_object.get_fatherOf(selectedId));
			GraphNode n1 = new GraphNode(g , SWT.NONE , f_name);
			ancestry(db_object.getIdFromFullName(f_name),n1,g);
			new GraphConnection(g, SWT.NONE, n, n1);
			//g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

			
		}
	//}
		//void ancestry2(int selectedId , GraphNode n,Graph g) throws Exception{
			//sqliteDB db_object = sqliteDB.getInstance();
			System.out.println("Mother HI HI");
		if(db_object.get_MotherOf(selectedId)!=0){
			System.out.println("MotherOf is not 0");
			String m_name = db_object.getNameFromId(db_object.get_MotherOf(selectedId));
			GraphNode n2 = new GraphNode(g , SWT.NONE , m_name );
			ancestry(db_object.getIdFromFullName(m_name),n2,g);
			new GraphConnection(g,SWT.NONE , n, n2);
			//g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

			
		}
		//g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
	}
}