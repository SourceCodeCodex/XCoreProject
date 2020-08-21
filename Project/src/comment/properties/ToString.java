package comment.properties;

import org.eclipse.cdt.core.dom.ast.IASTComment;

import project.metamodel.entity.XCComment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCComment> {
	
	@Override
	public String compute(XCComment arg0) {	
	
		IASTComment m = (IASTComment)arg0.getUnderlyingObject();
	    return m.getRawSignature();
		
	}

}
