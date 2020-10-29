package compUnit.properties;

import org.eclipse.cdt.core.model.ITranslationUnit;

import project.metamodel.entity.XCCompUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FolderName implements IPropertyComputer<String, XCCompUnit> {
	
	@Override
	public String compute(XCCompUnit arg0) {	
		ITranslationUnit m = arg0.getUnderlyingObject();
		String folder = m.getPath().toString();
		int index1 = folder.lastIndexOf('/');
		folder = folder.substring(0, index1);
		int index2 = folder.lastIndexOf('/');
		folder = folder.substring(index2+1);
	    return null;
		   
	}

}
