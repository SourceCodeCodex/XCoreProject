package expression.properties;

import org.eclipse.cdt.core.dom.ast.IASTExpression;
import project.metamodel.entity.XCExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCExpression> {
	
	@Override
	public String compute(XCExpression arg0) {	
	
		IASTExpression m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}