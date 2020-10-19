package projects.properties;

import org.eclipse.cdt.core.model.ICProject;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCProject>{
	
	@Override
	public String compute(XCProject arg0) {
		
		ICProject m = arg0.getUnderlyingObject();
		 return m.getElementName();
		 
	}
	
}