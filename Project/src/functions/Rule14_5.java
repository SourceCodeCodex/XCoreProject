package functions;

import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.IFunction;

import project.metamodel.entity.Function;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class Rule14_5 implements IPropertyComputer<String, Function>{

		@Override
		public String compute(Function arg0) {
			String s=new String();
			int n;
			IFunction m=(IFunction)arg0.getUnderlyingObject();
			try {
			 s=m.getSource();
			}catch(CModelException e)
			{
				e.printStackTrace();
			}
			n=s.indexOf("continue");
			if(n>0 && n<s.length()-1)
			  return "The continue statement shall not be used.";
			else
				return "";
			}
				 
}

