package statements.continueStatement.properties;

import org.eclipse.cdt.core.dom.ast.IASTContinueStatement;

import project.metamodel.entity.XCContinueStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCContinueStatement>{
	
	@Override
	public String compute(XCContinueStatement arg0) {
		
		IASTContinueStatement c = arg0.getUnderlyingObject();	
        return c.getRawSignature();
        
	}


}
