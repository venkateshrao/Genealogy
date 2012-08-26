package zesttest;


import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import db.Relationships;

/**
 * This snippet shows how to use the IGraphContentProvider to create a graph with Zest.
 * In this example, getElements returns 3 edges:
 * 	* Rock2Paper
 *  * Paper2Scissors
 *  * Scissors2Rock
 * 
 * And for each of these, the source and destination are returned in getSource and getDestination.
 * 
 * A label provider is also used to create the text and icons for the graph.
 * 
 * @author Ian Bull
 * 
 */
public class GraphJFaceSnippet2 {

	static class MyContentProvider implements IGraphContentProvider {
		
		public String[][] relObject;
		public void setRelationObject(String[][] relationObject){
			relObject = relationObject;
		}
		
		String[][] str1 = {{"a","b","c"},
				{"d","b","f"},
				{"g","b","i"}};

		public Object getSource(Object rel) {
			for(int i=0;i<relObject.length;i++){
				if("Son of".equals(rel.toString())){
					return relObject[i][2];
				}
				else if("Daughter of".equals(rel.toString())){
					return relObject[i][2];
				}
				else if(Relationships.BROTHER.equals(rel.toString())){
					return relObject[i][1];
				}
				else if(Relationships.SISTER.equals(rel.toString())){
					return relObject[i][1];
				}
				
				else if(Relationships.HUSBAND.equals(rel.toString())){
					return relObject[i][1];
				}
				else if(Relationships.WIFE.equals(rel.toString())){
					return relObject[i][1];
				}
				else if(Relationships.SON.equals(rel.toString())){
					return relObject[i][1];
				}
				else if(Relationships.DAUGHTER.equals(rel.toString())){
					return relObject[i][1];
				}
			}
			/*if ("Rock2Paper".equals(rel)) {
				return "Rock";
			} else if ("Paper2Scissors".equals(rel)) {
				return "Paper";
			} else if ("Scissors2Rock".equals(rel)) {
				return "Scissors";
			}*/
			return null;
		}

		public Object[] getElements(Object input) {
			//return new Object[] { "a", "d", "g" };
			return Relationships.values();
		}

		public Object getDestination(Object rel) {
			
			for(int i=0;i<relObject.length;i++){
				if("Son of".equals(rel.toString())){
					return relObject[i][1];
				}
				else if("Daughter of".equals(rel.toString())){
					return relObject[i][1];
				}
				else if(Relationships.BROTHER.equals(rel.toString())){
					return relObject[i][2];
				}
				else if(Relationships.SISTER.equals(rel.toString())){
					return relObject[i][2];
				}
				
				else if(Relationships.HUSBAND.equals(rel.toString())){
					return relObject[i][2];
				}
				else if(Relationships.WIFE.equals(rel.toString())){
					return relObject[i][2];
				}
				else if(Relationships.SON.equals(rel.toString())){
					return relObject[i][2];
				}
				else if(Relationships.DAUGHTER.equals(rel.toString())){
					return relObject[i][2];
				}
			}
			/*if ("Rock2Paper".equals(rel)) {
				return "Paper";
			} else if ("Paper2Scissors".equals(rel)) {
				return "Rock";
			} else if ("Scissors2Rock".equals(rel)) {
				return "Rock";
			}*/
			return null;
		}

		public double getWeight(Object connection) {
			return 0;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	/*static class MyLabelProvider extends LabelProvider {
		final Image image = Display.getDefault().getSystemImage(SWT.ICON_WARNING);

		public Image getImage(Object element) {
			if (element.equals("Rock") || element.equals("Paper") || element.equals("Scissors")) {
				return image;
			}
			return null;
		}

		public String getText(Object element) {
			return element.toString();
		}

	}*/

	static GraphViewer viewer = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display d = new Display();
		Shell shell = new Shell(d);
		shell.setText("GraphJFaceSnippet2");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		shell.setSize(400, 400);
		viewer = new GraphViewer(shell, SWT.NONE);
		viewer.setContentProvider(new MyContentProvider());
		//viewer.setLabelProvider(new MyLabelProvider());
		viewer.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
		viewer.setInput(new Object());
		shell.open();
		while (!shell.isDisposed()) {
			while (!d.readAndDispatch()) {
				d.sleep();
			}
		}

	}
}