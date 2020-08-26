package expression.functionCallExpression.properties;


import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;
import project.metamodel.entity.XCFunctionCallExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCFunctionCallExpression> {
	
	@Override
	public String compute(XCFunctionCallExpression arg0) {	
	
		IASTFunctionCallExpression m = (IASTFunctionCallExpression)arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}