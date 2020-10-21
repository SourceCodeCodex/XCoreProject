package tests;

import java.util.HashSet;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTNode;

public class TestClass {
	
	public int getLineNo(IASTNode node) {
		
		 IASTFileLocation l = node.getFileLocation();
		 int lineNo = l.getStartingLineNumber();
		 return lineNo;
	}
	
	
	protected HashSet<Integer> getLineNoList(List<IASTNode> nodes) {
		HashSet<Integer> lines = new HashSet<Integer>();
		int n=0;
		for(IASTNode node: nodes) {
			n = getLineNo(node);
			lines.add(n);
		}
		return lines;
	}

}
