package compUnit.tmp;

import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTTranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

/**
 * syntax tree
 */

@PropertyComputer
public class Print implements IPropertyComputer<String, XCCompUnit>{
	  
	@Override
	public String compute(XCCompUnit arg0) {
		
		String s = new String();
		IASTTranslationUnit a = null;
		ITranslationUnit m=null;
		try {
			m=(ITranslationUnit)arg0.getUnderlyingObject();
			a= m.getAST();
			}
		catch(CoreException e)
			{
				e.printStackTrace();
			}
		this.printTree(a,1);
		return "a";
	}
		
	private void printTree(IASTNode node, int index) {
			   
		IASTNode[] children = node.getChildren();
		boolean printContents = true;
		
	    if ((node instanceof CPPASTTranslationUnit)) {
			  printContents = false;
			   }
	
	    String offset = "";
	    System.out.println(String.format(new StringBuilder("%1$").append(index * 2).append("s").toString(), new Object[] { "-" }) + node.getClass().getSimpleName() + offset + " -> " + (printContents ? node.getRawSignature().replaceAll("\n", " \\ ") : node.getRawSignature().subSequence(0, 5)));
		

	    for (IASTNode iastNode : children)
		printTree(iastNode, index + 1);
	 
	}
	
	}