package expression.expressionList.properties;

import org.eclipse.cdt.core.dom.ast.IASTExpressionList;

import project.metamodel.entity.XCExpressionList;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCExpressionList> {
	
	@Override
	public String compute(XCExpressionList arg0) {	
	
		IASTExpressionList m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}