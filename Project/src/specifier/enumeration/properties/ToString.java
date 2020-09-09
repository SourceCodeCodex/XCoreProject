package specifier.enumeration.properties;

import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;
import project.metamodel.entity.XCEnumeration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCEnumeration> {
	
	@Override
	public String compute(XCEnumeration arg0) {	
	
		IASTEnumerationSpecifier m = (IASTEnumerationSpecifier)arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}