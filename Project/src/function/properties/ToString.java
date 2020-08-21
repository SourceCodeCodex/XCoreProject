package function.properties;


import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import project.metamodel.entity.XCFunction;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCFunction> {
	
	@Override
	public String compute(XCFunction arg0) {	
	
		IASTFunctionDeclarator m = (IASTFunctionDeclarator)arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}