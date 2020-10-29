package expression.properties;

import org.eclipse.cdt.core.dom.ast.IASTExpression;

import project.metamodel.entity.XCExpression;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class FileName implements IPropertyComputer<String, XCExpression> {
	
	@Override
	public String compute(XCExpression arg0) {	
	
		IASTExpression m = arg0.getUnderlyingObject();
		String file = m.getTranslationUnit().getContainingFilename();
		int index = file.lastIndexOf('\\');
		file = file.substring(index+1);
	    return file;
		
	}

}
