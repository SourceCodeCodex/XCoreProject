package declaration.properties;

import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import project.metamodel.entity.XCDeclaration;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCDeclaration> {
	
	@Override
	public String compute(XCDeclaration arg0) {	
	
		IASTDeclarator m = arg0.getUnderlyingObject();
		String file = m.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		
	}

}
