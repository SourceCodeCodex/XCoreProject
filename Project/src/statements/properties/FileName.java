package statements.properties;

import org.eclipse.cdt.core.dom.ast.IASTStatement;

import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCStatement>{
	
	@Override
	public String compute(XCStatement arg0) {
		
		IASTStatement c = arg0.getUnderlyingObject();	
		String file = c.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		
	}

}
