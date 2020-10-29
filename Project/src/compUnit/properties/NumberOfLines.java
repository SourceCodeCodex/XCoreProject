package compUnit.properties;

import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class NumberOfLines implements IPropertyComputer<Integer, XCCompUnit>{
	
	@Override
	public Integer compute(XCCompUnit arg0) {
		
		ITranslationUnit m = arg0.getUnderlyingObject();
		IASTFileLocation l = null;
		try {
			IASTTranslationUnit a = m.getAST();
			l = a.getFileLocation();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return l.getEndingLineNumber();
	}

}