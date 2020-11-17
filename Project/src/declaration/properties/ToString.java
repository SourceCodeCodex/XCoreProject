package declaration.properties;

import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import project.metamodel.entity.XCDeclaration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCDeclaration> {
	
	@Override
	public String compute(XCDeclaration arg0) {	
	
		IASTDeclarator m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}
