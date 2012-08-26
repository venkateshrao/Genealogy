package zesttest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.dot.DotGraph;


public class SampleUsage {

  public static void main(String[] args) {
	  String GF = "Pranesh";
	  Shell shell = new Shell();
	     DotGraph graph = new DotGraph("digraph{Rohith R->Ravindra}", shell, SWT.NONE);
	     graph.add("Ravindra->Rohith").add(GF+"->Ravindra");
	     graph.add("node[label=zested]; edge[style=dashed]; Rohith->5; Rachana->6");
	     open(shell);
	     System.out.println(graph.toDot());
  }

  private static void open(final Shell shell) {
    shell.setText(DotGraph.class.getSimpleName());
    shell.setLayout(new FillLayout());
    shell.setSize(600, 300);
    shell.open();
    Display display = shell.getDisplay();
    while (!shell.isDisposed())
      if (!display.readAndDispatch())
        display.sleep();
    display.dispose();
  }
  
}