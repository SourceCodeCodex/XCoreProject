package statements.properties;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineNumber implements IPropertyComputer<Integer, XCStatement> {
	
	@Override
	public Integer compute(XCStatement arg0) {	
	
		IASTStatement node = arg0.getUnderlyingObject();
	    IASTFileLocation l = node.getFileLocation();
		int lineNo = l.getStartingLineNumber();
		
		return lineNo;
		
	}

}