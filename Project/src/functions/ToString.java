package functions;

import org.eclipse.cdt.core.model.IFunction;


import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import project.metamodel.entity.Function;


@PropertyComputer
public class ToString implements IPropertyComputer<String, Function> {
	
	@Override
	public String compute(Function arg0) {
		IFunction m=(IFunction)arg0.getUnderlyingObject();
		 return m.getElementName();
	}

}