package comment.properties;

import org.eclipse.cdt.core.dom.ast.IASTComment;

import project.metamodel.entity.XCComment;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCComment> {
	
	@Override
	public String compute(XCComment arg0) {	
		IASTComment m = arg0.getUnderlyingObject();
		String file = m.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		   
	}

}

