package expression.unaryExpression.properties;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import project.metamodel.entity.XCUnaryExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCUnaryExpression> {
	
	@Override
	public String compute(XCUnaryExpression arg0) {	
	
		IASTUnaryExpression m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}