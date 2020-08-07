package sourceRoot;

import org.eclipse.cdt.core.model.ISourceRoot;
import project.metamodel.entity.XCSourceRoot;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCSourceRoot>{
	
	@Override
	public String compute(XCSourceRoot arg0) {
		ISourceRoot m=(ISourceRoot)arg0.getUnderlyingObject();
		 return m.getElementName();
	}

}
