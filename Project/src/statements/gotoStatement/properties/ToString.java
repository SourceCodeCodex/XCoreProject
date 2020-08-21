package statements.gotoStatement.properties;


import org.eclipse.cdt.core.dom.ast.IASTGotoStatement;

import project.metamodel.entity.XCGotoStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCGotoStatement>{
	
	@Override
	public String compute(XCGotoStatement arg0) {
		
		
		IASTGotoStatement c = (IASTGotoStatement)arg0.getUnderlyingObject();	
        return c.getRawSignature();
		
        
	}


}
