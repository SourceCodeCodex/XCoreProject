package specifier.compositeType.union.properties;

import org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier;
import project.metamodel.entity.XCUnion;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCUnion>{
	
	@Override
	public String compute(XCUnion arg0) {
		
		IASTCompositeTypeSpecifier m = (IASTCompositeTypeSpecifier)arg0.getUnderlyingObject();
		return m.getRawSignature();
		 
	}

}
