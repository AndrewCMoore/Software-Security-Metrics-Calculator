package Parser_Testing;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import tree.NodeType;

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
				assertEquals(tree.getLeefs().length,10);
			}else {
				fail();
			}
		}
		
		@Test
		public void testNumberofLeefs2() {
			IProject project = null;
			IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			if (window != null) {
				project = fileRoot.getProject("Single");

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
				assertEquals(tree.getLeefs().length,1);
			}else {
				fail();
			}
		}
		
		@Test
		public void testNumberofLeefs0() {
			IProject project = null;
			IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			if (window != null) {
				project = fileRoot.getProject("None");

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
				assertEquals(0,tree.getLeefs().length);
			}else {
				fail();
			}
		}
		
		@Test
		public void testLeefType() {
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
				JDTree [] leafs = tree.getLeefs();
				for(JDTree leaf : leafs) {
					assertEquals(NodeType.CLASS,leaf.getType());
				}
			}else {
				fail();
			}
		}
		
		@Test
		public void testLeefType2() {
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
				JDTree [] leafs = tree.getLeefs();
				for(JDTree leaf : leafs) {
					assertEquals(ssmc.Class.class,leaf.getNode().getClass());
				}
			}else {
				fail();
			}
		}
		
		
		
		
}
