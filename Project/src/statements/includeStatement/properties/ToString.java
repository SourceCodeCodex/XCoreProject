package statements.includeStatement.properties;

import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;

import project.metamodel.entity.XCIncludeStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCIncludeStatement>{
	
	@Override
	public String compute(XCIncludeStatement arg0) {
		
		IASTPreprocessorIncludeStatement c = arg0.getUnderlyingObject();	
        return c.getRawSignature();
        
	}
}


