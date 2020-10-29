package function.properties;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import project.metamodel.entity.XCFunction;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class LineNumber implements IPropertyComputer<Integer, XCFunction> {
	
	@Override
	public Integer compute(XCFunction arg0) {	
	
		IASTFunctionDeclarator node = arg0.getUnderlyingObject();
	    IASTFileLocation l = node.getFileLocation();
		int lineNo = l.getStartingLineNumber();
		
		return lineNo;
		
	}

}