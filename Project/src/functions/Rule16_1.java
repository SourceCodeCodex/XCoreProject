package functions;

import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.IFunction;
import project.metamodel.entity.Function;

@PropertyComputer
public class Rule16_1 implements IPropertyComputer<String, Function> {

	@Override
	public String compute(Function arg0)
	{String s=new String();
	int n;
	IFunction m=(IFunction)arg0.getUnderlyingObject();
	try {
	 s=m.getSignature();
	}catch(CModelException e)
	{
		e.printStackTrace();
	}
	n=s.indexOf("...");
	if(m.getNumberOfParameters()==2 && n>0 && n<s.length()-1)
	  return "Functions "+ m.toString() + " shall not be defined with variable numbers of arguments";
	else
		return "";
	}
}