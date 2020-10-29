package function.properties;

import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;

import project.metamodel.entity.XCFunction;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCFunction> {
	
	@Override
	public String compute(XCFunction arg0) {	
	
		IASTFunctionDeclarator m = arg0.getUnderlyingObject();
		String file = m.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		
	}

}
