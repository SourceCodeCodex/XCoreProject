package demo;

import org.eclipse.cdt.core.dom.ast.IASTContinueStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.ISourceRoot;
import org.eclipse.cdt.core.model.ITranslationUnit;

import project.metamodel.factory.Factory;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;

public class MyXEntityConverter implements XEntityConverter {

	@Override
	public XEntity convert(Object element) {
		
		if(element instanceof ICProject)
			return Factory.getInstance().createXCProject((ICProject)element);
		else if(element instanceof ITranslationUnit)
			return Factory.getInstance().createXCCompUnit((ITranslationUnit)element);
		else if(element instanceof IASTContinueStatement)
			return Factory.getInstance().createXCContinueStatement((IASTContinueStatement)element);
		else if(element instanceof IASTStatement)
			return Factory.getInstance().createXCStatement((IASTStatement)element);
		else if(element instanceof IASTFunctionDeclarator)
			return Factory.getInstance().createXCFunction((IASTFunctionDeclarator)element);
		return null;
		
	}
}
