package specifier.properties;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;

import project.metamodel.entity.XCSpecifier;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCSpecifier> {
	
	@Override
	public String compute(XCSpecifier arg0) {	
	
		IASTDeclSpecifier m = arg0.getUnderlyingObject();
		String file = m.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		
	}

}