package statements.includeStatement.properties;

import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;

import project.metamodel.entity.XCIncludeStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCIncludeStatement>{
	
	@Override
	public String compute(XCIncludeStatement arg0) {
		
		IASTPreprocessorIncludeStatement c = arg0.getUnderlyingObject();	
		String file = c.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
        
	}
}