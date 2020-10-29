package specifier.properties;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCSpecifier> {
	
	@Override
	public String compute(XCSpecifier arg0) {	
	
		IASTDeclSpecifier m = arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}