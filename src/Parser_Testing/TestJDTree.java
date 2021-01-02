package Parser_Testing;

import static org.junit.Assert.fail;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;
import tree.JDTree;

public class TestJDTree {
	
		@Test
		public void testNumberofLeefs() {
			IProject project = null;
			IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			if (window != null) {
				project = fileRoot.getProject("Parser-Testing");

			}
			JDTree tree = null;
			IJavaProject javaProject = JavaCore.create(project);
			try {
				 tree = new JDTree(javaProject,null);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(tree!=null) {
				assert(tree.getLeefs().length==2);
			}else {
				fail();
			}
		}
		
		
		
		
}
