package specifier.properties;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineNumber implements IPropertyComputer<Integer, XCSpecifier> {
	
	@Override
	public Integer compute(XCSpecifier arg0) {	
	
		IASTDeclSpecifier node = arg0.getUnderlyingObject();
	    IASTFileLocation l = node.getFileLocation();
		int lineNo = l.getStartingLineNumber();
		
		return lineNo;
		
	}

}