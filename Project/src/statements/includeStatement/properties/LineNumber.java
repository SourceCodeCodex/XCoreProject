package statements.includeStatement.properties;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import project.metamodel.entity.XCIncludeStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineNumber implements IPropertyComputer<Integer, XCIncludeStatement> {
	
	@Override
	public Integer compute(XCIncludeStatement arg0) {	
	
		IASTPreprocessorIncludeStatement node = arg0.getUnderlyingObject();
	    IASTFileLocation l = node.getFileLocation();
		int lineNo = l.getStartingLineNumber();
		
		return lineNo;
		
	}

}
