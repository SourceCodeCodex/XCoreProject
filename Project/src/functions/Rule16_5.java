package functions;

import org.eclipse.cdt.core.model.IFunction;
import project.metamodel.entity.Function;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class Rule16_5 implements IPropertyComputer<String, Function>{
	@Override
	public String compute(Function arg0) {
	IFunction m=(IFunction)arg0.getUnderlyingObject();
		 
	if( m.getNumberOfParameters()==0)
	return "Function "+ m.toString()+ " with no parameters shall be declared and  defined with the parameter list void";
	else
		return "";
			 
	}

}
