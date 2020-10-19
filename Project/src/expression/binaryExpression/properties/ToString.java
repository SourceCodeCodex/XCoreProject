package expression.binaryExpression.properties;

import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import project.metamodel.entity.XCBinaryExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCBinaryExpression> {
	
	@Override
	public String compute(XCBinaryExpression arg0) {	
	
		IASTBinaryExpression m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}