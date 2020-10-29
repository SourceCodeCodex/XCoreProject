package comment.properties;

import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;

import project.metamodel.entity.XCComment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineNumber implements IPropertyComputer<Integer, XCComment> {
	
	@Override
	public Integer compute(XCComment arg0) {	
	
		IASTComment node = arg0.getUnderlyingObject();
	    IASTFileLocation l = node.getFileLocation();
		int lineNo = l.getStartingLineNumber();
		
		return lineNo;
		
	}

}

