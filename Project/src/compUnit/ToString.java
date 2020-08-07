package compUnit;

import org.eclipse.cdt.core.model.ITranslationUnit;

import project.metamodel.entity.XCCompUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCCompUnit>{
	
	@Override
	public String compute(XCCompUnit arg0) {
		ITranslationUnit m=(ITranslationUnit)arg0.getUnderlyingObject();
		 return m.getElementName();
	}

}