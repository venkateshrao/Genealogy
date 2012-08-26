package zesttest;
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
	public static void main(String[] args) {
		// Create the shell
		Display d = new Display();
		Shell shell = new Shell(d);
		shell.setText("GraphSnippet1");
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);

		Graph g = new Graph(shell, SWT.NONE);
		String[][] str1 = {{"a","b","c"},
				{"d","b","f"},
				{"g","b","i"}};
		GraphNode n = null;
		GraphNode n2 = null;
		GraphNode n3 = null;
		GraphNode n4 = null;
				HashMap<String,GraphNode> HM = new HashMap<String,GraphNode>();
				GraphConnection GC = null;
				for (int i=0;i<str1.length;i++){
					HM.put(str1[i][1] ,n= new GraphNode(g , SWT.None, str1[i][1]));
					HM.put(str1[i][2] , n2=new GraphNode(g , SWT.None, str1[i][2]));
					HM.put(str1[i][2] , n3=new GraphNode(g , SWT.None, str1[i][2]));
					HM.put(str1[i][2] , n2=new GraphNode(g , SWT.None, str1[i][2]));
					new GraphConnection(g,SWT.NONE,HM.get(str1[i][1]),HM.get(str1[i][2]));
					// GC.setText(str1[i][0]);
				}
				
		/*GraphNode n = new GraphNode(g,  SWT.NONE, "a");
		GraphNode n2 = new GraphNode(g, SWT.NONE, "a");
		GraphNode n3 = new GraphNode(g, SWT.NONE, "Scissors");
		GraphNode n4 = new GraphNode(g , SWT.NONE , "b");
		new GraphConnection(g , SWT.NONE , n , n4);
		new GraphConnection(g, SWT.NONE, n4, n);
		new GraphConnection(g, SWT.NONE, n, n2);
		new GraphConnection(g, SWT.NONE, n2, n3);*/
		g.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		shell.open();
		while (!shell.isDisposed()) {
			while (!d.readAndDispatch()) {
				d.sleep();
			}
		}
	}
}