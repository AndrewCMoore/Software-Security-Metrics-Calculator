package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.LineComment;

/**
 * @deprecated
 * @author AndrewCMoore
 *
 */
public class CommentVisitor extends ASTVisitor {

	private CompilationUnit compilationUnit;
	private int lineCount;
	
	public CommentVisitor(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.lineCount = 0;
	}
	
	public int getLineCount() {
		return this.lineCount;
	}
	
	/**
	 * For a block comment (/ * to  * /) or (/** to **  /)
	 */
	public boolean visit(BlockComment node) {
		int startLine = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
		int endLine = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength()) - 1;
		
		this.lineCount += endLine - startLine;
		return true;
	}
	
	/**
	 * For a single line comment (//)
	 */
	public boolean visit(LineComment node) {
		System.out.println("We are in the single line comment node");
		// Add 1
		this.lineCount += 1;
		return true;
	}
}
