package demo;

import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.model.ICProject;
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
		else if(element instanceof IASTDeclSpecifier)
			return Factory.getInstance().createXCSpecifier((IASTDeclSpecifier) element);
		else if(element instanceof IASTStatement)
			return Factory.getInstance().createXCStatement((IASTStatement) element);
		else if(element instanceof IASTPreprocessorIncludeStatement)
			return Factory.getInstance().createXCIncludeStatement((IASTPreprocessorIncludeStatement)element);
		else if(element instanceof IASTExpression)
			return Factory.getInstance().createXCExpression((IASTExpression)element);
		else if(element instanceof IASTComment)
			return Factory.getInstance().createXCComment((IASTComment)element);
		else if(element instanceof IASTDeclarator)
			return Factory.getInstance().createXCDeclaration((IASTDeclarator)element);
		return null;
		
	}
}
