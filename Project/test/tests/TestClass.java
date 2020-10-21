package tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTNode;

public class TestClass {
	
	public int getLineNo(IASTNode node) {
		
		 IASTFileLocation l = node.getFileLocation();
		 int lineNo = l.getStartingLineNumber();
		 return lineNo;
	}
	
	
	protected List<Integer> getLineNoList(List<IASTNode> nodes) {
		List<Integer> lines = new ArrayList<Integer>();
		int n=0;
		for(IASTNode node: nodes) {
			n = getLineNo(node);
			lines.add(n);
		}
		return lines;
	}

}
